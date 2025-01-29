package com.aetherteam.genesis.entity.monster.dungeon.boss;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.entity.ai.goal.MostDamageTargetGoal;
import com.aetherteam.aether.entity.monster.dungeon.boss.BossNameGenerator;
import com.aetherteam.aether.event.AetherEventDispatch;
import com.aetherteam.aether.network.packet.clientbound.BossInfoPacket;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.entity.projectile.HostEyeProjectile;
import com.aetherteam.nitrogen.entity.BossRoomTracker;
import com.aetherteam.nitrogen.network.PacketRelay;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ToolActions;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class SliderHostMimic extends PathfinderMob implements AetherBossMob<SliderHostMimic>, Enemy, IEntityWithComplexSpawn {
    public static final EntityDataAccessor<Boolean> DATA_AWAKE_ID = SynchedEntityData.defineId(SliderHostMimic.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Component> DATA_BOSS_NAME_ID = SynchedEntityData.defineId(SliderHostMimic.class, EntityDataSerializers.COMPONENT);
    private static final Music SLIDER_HOST_MIMIC_MUSIC = new Music(GenesisSoundEvents.MUSIC_MINIBOSS, 0, 0, true);
    public static final Map<Block, Function<BlockState, BlockState>> DUNGEON_BLOCK_CONVERSIONS = new HashMap<>(Map.ofEntries(
            Map.entry(AetherBlocks.LOCKED_CARVED_STONE.get(), (blockState) -> AetherBlocks.CARVED_STONE.get().defaultBlockState()),
            Map.entry(AetherBlocks.LOCKED_SENTRY_STONE.get(), (blockState) -> AetherBlocks.SENTRY_STONE.get().defaultBlockState()),
            Map.entry(AetherBlocks.BOSS_DOORWAY_CARVED_STONE.get(), (blockState) -> Blocks.AIR.defaultBlockState()),
            Map.entry(AetherBlocks.TREASURE_DOORWAY_CARVED_STONE.get(), (blockState) -> AetherBlocks.SKYROOT_TRAPDOOR.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, blockState.getValue(HorizontalDirectionalBlock.FACING)))
    ));

    /**
     * Goal for targeting in groups of entities
     */
    private MostDamageTargetGoal mostDamageTargetGoal;

    private final ServerBossEvent bossFight;
    private BossRoomTracker<SliderHostMimic> bronzeDungeon;
    private final List<HostEyeProjectile> eyeProjectiles = new ArrayList<>();

    private int spawnEyeCooldown;
    private int chatCooldown;

    public SliderHostMimic(EntityType<? extends SliderHostMimic> entityType, Level level) {
        super(entityType, level);
        this.bossFight = new ServerBossEvent(this.getBossName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS);
        this.setBossFight(false);
        this.xpReward = XP_REWARD_BOSS;
        this.setPersistenceRequired();
        this.setMaxUpStep(1.0F);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level,  DifficultyInstance difficulty,  MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        this.setBossName(BossNameGenerator.generateBossName(this.getRandom()).append(Component.translatable("gui.aether_genesis.host.title")));
        this.moveTo(Mth.floor(this.getX()), this.getY(), Mth.floor(this.getZ()));
        return spawnData;
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 400.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75)
                .add(Attributes.FOLLOW_RANGE, 64.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SliderHostMimic.HostAvoidPlayerGoal(this));
        this.goalSelector.addGoal(1, new SliderHostMimic.ShootHostEyeGoal(this));
        this.goalSelector.addGoal(2, new SliderHostMimic.HostRandomMovementGoal(this));
        this.goalSelector.addGoal(3, new SliderHostMimic.InactiveGoal(this));

        this.mostDamageTargetGoal = new MostDamageTargetGoal(this);
        this.targetSelector.addGoal(1, this.mostDamageTargetGoal);
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, false));
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_AWAKE_ID, false);
        this.entityData.define(DATA_BOSS_NAME_ID, Component.literal("Slider Host Mimic"));
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.isAwake() || (this.getTarget() instanceof Player player && (player.isCreative() || player.isSpectator()))) {
            this.setTarget(null);
        }
        this.evaporate();
        if (this.getChatCooldown() > 0) {
            this.chatCooldown--;
        }
        if (this.spawnEyeCooldown > 0) {
            this.spawnEyeCooldown--;
        }
    }

    private void evaporate() {
        Pair<BlockPos, BlockPos> minMax = this.getDefaultBounds(this);
        AetherBossMob.super.evaporate(this, minMax.getLeft(), minMax.getRight(), (blockState) -> true);
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossFight.setProgress(this.getHealth() / this.getMaxHealth());
        this.trackDungeon();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Optional<LivingEntity> damageResult = this.canDamageSliderHostMimic(source);
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            super.hurt(source, amount);
            if (!this.level().isClientSide() && source.getEntity() instanceof LivingEntity living) {
                this.mostDamageTargetGoal.addAggro(living, amount); // AI goal for being hurt.
                if (this.spawnEyeCooldown <= 0 && this.getEyeProjectiles().size() == 4) {
                    this.getEyeProjectiles().remove(0).discard();
                    this.spawnEyeCooldown = 200;
                }
            }
        } else if (damageResult.isPresent()) {
            if (super.hurt(source, amount) && this.getHealth() > 0) {
                if (!this.isBossFight()) {
                    this.start();
                }
                if (!this.level().isClientSide() && source.getEntity() instanceof LivingEntity living) {
                    this.mostDamageTargetGoal.addAggro(living, amount); // AI goal for being hurt.
                    if (this.spawnEyeCooldown <= 0 && this.getEyeProjectiles().size() == 4) {
                        this.getEyeProjectiles().remove(0).discard();
                        this.spawnEyeCooldown = 200;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private Optional<LivingEntity> canDamageSliderHostMimic(DamageSource source) {
        if (this.level().getDifficulty() != Difficulty.PEACEFUL) {
            if (source.getDirectEntity() instanceof LivingEntity attacker) {
                if (this.getDungeon() == null || this.getDungeon().isPlayerWithinRoomInterior(attacker)) { // Only allow damage within the boss room.
                    return Optional.of(attacker);
                } else {
                    this.sendTooFarMessage(attacker);
                }
            } else if (source.getDirectEntity() instanceof Projectile projectile) {
                if (projectile.getOwner() instanceof LivingEntity attacker) {
                    if (this.getDungeon() == null || this.getDungeon().isPlayerWithinRoomInterior(attacker)) { // Only allow damage within the boss room.
                        return Optional.of(attacker);
                    } else {
                        return this.sendTooFarMessage(attacker);
                    }
                }
            }
        }
        return Optional.empty();
    }

    private Optional<LivingEntity> sendInvalidToolMessage(LivingEntity attacker) {
        if (!this.level().isClientSide() && attacker instanceof Player player) {
            if (this.getChatCooldown() <= 0) {
                if (AetherConfig.COMMON.reposition_slider_message.get()) {
                    player.displayClientMessage(Component.translatable("gui.aether.slider.message.attack.invalid"), true); // Invalid tool.
                } else {
                    player.sendSystemMessage(Component.translatable("gui.aether.slider.message.attack.invalid")); // Invalid tool.
                }
                this.setChatCooldown(15);
            }
        }
        return Optional.empty();
    }

    private Optional<LivingEntity> sendTooFarMessage(LivingEntity attacker) {
        if (!this.level().isClientSide() && attacker instanceof Player player) {
            if (this.getChatCooldown() <= 0) {
                this.displayTooFarMessage(player); // Too far from Slider
                this.setChatCooldown(15);
            }
        }
        return Optional.empty();
    }

    private void start() {
        if (this.getAwakenSound() != null) {
            this.playSound(this.getAwakenSound(), 2.5F, 1.0F / (this.getRandom().nextFloat() * 0.2F + 0.9F));
        }
        this.setHealth(this.getMaxHealth());
        this.setAwake(true);
        this.setBossFight(true);
        if (this.getDungeon() != null) {
            this.closeRoom();
        }
        AetherEventDispatch.onBossFightStart(this, this.getDungeon());
    }

    public void reset() {
        this.setDeltaMovement(Vec3.ZERO);
        this.setAwake(false);
        this.setBossFight(false);
        this.setTarget(null);
        if (this.getDungeon() != null) {
            this.setPos(this.getDungeon().originCoordinates());
            this.openRoom();
        }
        AetherEventDispatch.onBossFightStop(this, this.getDungeon());
    }

    @Override
    public void die(DamageSource source) {
        this.setDeltaMovement(Vec3.ZERO);
        this.explode();
        if (this.level() instanceof ServerLevel) {
            this.bossFight.setProgress(this.getHealth() / this.getMaxHealth()); // Forces an update to the boss health meter.
            if (this.getDungeon() != null) {
                this.getDungeon().grantAdvancements(source);
                this.tearDownRoom();
            }
        }
        super.die(source);
    }

    private void explode() {
        for (int i = 0; i < (this.getHealth() <= 0 ? 16 : 48); i++) {
            double x = this.position().x() + (double) (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 1.5;
            double y = this.getBoundingBox().minY + 1.75 + (double) (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 1.5;
            double z = this.position().z() + (double) (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 1.5;
            this.level().addParticle(ParticleTypes.POOF, x, y, z, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void knockback(double strength, double x, double z) {
        if (this.isAwake()) {
            super.knockback(strength, x, z);
        }
    }

    @Override
    public void push(double x, double y, double z) {
        if (this.isAwake()) {
            super.push(x, y, z);
        }
    }

    @Override
    public void checkDespawn() { }

    /**
     * Called on every block in the boss room when the boss is defeated.
     *
     * @param state The {@link BlockState} to try to convert.
     * @return The converted {@link BlockState}.
     */
    @Nullable
    @Override
    public BlockState convertBlock(BlockState state) {
        return DUNGEON_BLOCK_CONVERSIONS.getOrDefault(state.getBlock(), (blockState) -> null).apply(state);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        PacketRelay.sendToPlayer(new BossInfoPacket.Display(this.bossFight.getId(), this.getId()), player);
        if (this.getDungeon() == null || this.getDungeon().isPlayerTracked(player)) {
            this.bossFight.addPlayer(player);
            AetherEventDispatch.onBossFightPlayerAdd(this, this.getDungeon(), player);
        }
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        PacketRelay.sendToPlayer(new BossInfoPacket.Remove(this.bossFight.getId(), this.getId()), player);
        this.bossFight.removePlayer(player);
        AetherEventDispatch.onBossFightPlayerRemove(this, this.getDungeon(), player);
    }

    @Override
    public void onDungeonPlayerAdded(@Nullable Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.bossFight.addPlayer(serverPlayer);
            AetherEventDispatch.onBossFightPlayerAdd(this, this.getDungeon(), serverPlayer);
        }
    }

    @Override
    public void onDungeonPlayerRemoved(@Nullable Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.bossFight.removePlayer(serverPlayer);
            AetherEventDispatch.onBossFightPlayerRemove(this, this.getDungeon(), serverPlayer);
        }
    }

    public boolean isAwake() {
        return this.entityData.get(DATA_AWAKE_ID);
    }

    public void setAwake(boolean ready) {
        this.entityData.set(DATA_AWAKE_ID, ready);
    }

    @Override
    public Component getBossName() {
        return this.entityData.get(DATA_BOSS_NAME_ID);
    }

    @Override
    public void setBossName(Component component) {
        this.entityData.set(DATA_BOSS_NAME_ID, component);
        this.bossFight.setName(component);
    }

    @Nullable
    @Override
    public BossRoomTracker<SliderHostMimic> getDungeon() {
        return this.bronzeDungeon;
    }

    @Override
    public void setDungeon(@Nullable BossRoomTracker<SliderHostMimic> dungeon) {
        this.bronzeDungeon = dungeon;
    }

    @Override
    public boolean isBossFight() {
        return this.bossFight.isVisible();
    }

    @Override
    public void setBossFight(boolean isFighting) {
        this.bossFight.setVisible(isFighting);
    }

    /**
     * @return The {@link ResourceLocation} for this boss's health bar.
     */
    @Nullable
    @Override
    public ResourceLocation getBossBarTexture() {
        return new ResourceLocation(Aether.MODID, "boss_bar/slider");
    }

    /**
     * @return The {@link ResourceLocation} for this boss's health bar background.
     */
    @Nullable
    @Override
    public ResourceLocation getBossBarBackgroundTexture() {
        return new ResourceLocation(Aether.MODID, "boss_bar/slider_background");
    }

    /**
     * @return The {@link Music} for this boss's fight.
     */
    @Nullable
    @Override
    public Music getBossMusic() {
        return SLIDER_HOST_MIMIC_MUSIC;
    }

    public List<HostEyeProjectile> getEyeProjectiles() {
        return this.eyeProjectiles;
    }

    /**
     * @return The {@link Integer} for the cooldown until another chat message can display.
     */
    public int getChatCooldown() {
        return this.chatCooldown;
    }

    /**
     * Sets the cooldown for when another chat message can display.
     *
     * @param cooldown The {@link Integer} cooldown.
     */
    public void setChatCooldown(int cooldown) {
        this.chatCooldown = cooldown;
    }

    @Override
    public int getDeathScore() {
        return this.deathScore;
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.setBossName(name);
    }

    protected SoundEvent getScareSound() {
        return GenesisSoundEvents.ENTITY_SLIDER_HOST_MIMIC_SCARE.get();
    }

    protected SoundEvent getShootSound() {
        return GenesisSoundEvents.ENTITY_SLIDER_HOST_MIMIC_SHOOT.get();
    }

    protected SoundEvent getAwakenSound() {
        return GenesisSoundEvents.ENTITY_SLIDER_HOST_MIMIC_AWAKEN.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return this.isAwake() ? (random.nextInt(5) == 0 ? GenesisSoundEvents.ENTITY_SLIDER_HOST_MIMIC_SCARE.get() : null) : GenesisSoundEvents.ENTITY_SLIDER_HOST_MIMIC_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return GenesisSoundEvents.ENTITY_SLIDER_HOST_MIMIC_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return GenesisSoundEvents.ENTITY_SLIDER_HOST_MIMIC_DEATH.get();
    }
    
    @Override
    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return target.canBeSeenAsEnemy();
    }

    @Override
    public boolean ignoreExplosion(Explosion explosion) {
        return !this.isAwake();
    }

    @Override
    public float getYRot() {
        return !this.isAwake() ? 0 : super.getYRot();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return this.isAwake() ? super.getDimensions(pose) : EntityDimensions.fixed(2.0F, 2.0F);
    }

    @Override
    protected boolean canRide(Entity vehicle) {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return !this.isAwake();
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return !this.isAwake();
    }

    @Override
    public boolean shouldDiscardFriction() {
        return !this.isAwake();
    }
    @Override
    protected boolean isAffectedByFluids() {
        return !this.isAwake();
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    @Override
    public boolean isFullyFrozen() {
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        this.addBossSaveData(tag);
        tag.putBoolean("Awake", this.isAwake());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.readBossSaveData(tag);
        if (tag.contains("Awake")) {
            this.setAwake(tag.getBoolean("Awake"));
        }
    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        CompoundTag tag = new CompoundTag();
        this.addBossSaveData(tag);
        buffer.writeNbt(tag);
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
        CompoundTag tag = additionalData.readNbt();
        if (tag != null) {
            this.readBossSaveData(tag);
        }
    }

    public static class HostAvoidPlayerGoal extends Goal {
        private final SliderHostMimic sliderHostMimic;
        protected double posX;
        protected double posY;
        protected double posZ;

        public HostAvoidPlayerGoal(SliderHostMimic sliderHostMimic) {
            super();
            this.sliderHostMimic = sliderHostMimic;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (this.sliderHostMimic.isAwake()) {
                if (this.sliderHostMimic.getDungeon() != null) {
                    for (UUID id : this.sliderHostMimic.getDungeon().dungeonPlayers()) {
                        Player player = this.sliderHostMimic.level().getPlayerByUUID(id);
                        if (player != null && player.distanceToSqr(this.sliderHostMimic) < 81) {
                            Vec3 vec3 = this.findRandomPosition(player);
                            if (vec3 != null) {
                                if (player.distanceToSqr(vec3.x, vec3.y, vec3.z) < player.distanceToSqr(this.sliderHostMimic)) {
                                    return false;
                                } else {
                                    this.posX = vec3.x;
                                    this.posY = vec3.y;
                                    this.posZ = vec3.z;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }

        protected Vec3 findRandomPosition(LivingEntity entity) {
            Vec3 vec3 = DefaultRandomPos.getPosAway(this.sliderHostMimic, 16, 2, entity.position());
            if (vec3 == null || (this.sliderHostMimic.getDungeon() != null && !this.sliderHostMimic.getDungeon().roomBounds().contains(vec3))) {
                return null;
            }
            return vec3;
        }

        @Override
        public boolean canContinueToUse() {
            return !this.sliderHostMimic.getBoundingBox().contains(new Vec3(this.posX, this.posY, this.posZ))
                    && this.sliderHostMimic.isAwake()
                    && !this.sliderHostMimic.getNavigation().isDone()
                    && !this.sliderHostMimic.getNavigation().isStuck()
                    && this.sliderHostMimic.getTarget() != null;
        }

        @Override
        public void start() {
            this.sliderHostMimic.playSound(this.sliderHostMimic.getScareSound(), 2.5F, 1.0F / (this.sliderHostMimic.getRandom().nextFloat() * 0.2F + 0.9F));
            this.sliderHostMimic.getNavigation().moveTo(this.posX, this.posY, this.posZ, 1.5);
        }

        @Override
        public void stop() {
            this.sliderHostMimic.getNavigation().stop();
        }

        @Override
        public void tick() {
            super.tick();
            LivingEntity avoid = null;
            if (this.sliderHostMimic.getDungeon() != null) {
                for (UUID id : this.sliderHostMimic.getDungeon().dungeonPlayers()) {
                    Player player = this.sliderHostMimic.level().getPlayerByUUID(id);
                    if (player != null && player.distanceToSqr(this.sliderHostMimic) < 81) {
                        avoid = player;
                    }
                }
            } else if (this.sliderHostMimic.getTarget() != null && this.sliderHostMimic.getTarget().distanceToSqr(this.sliderHostMimic) < 81) {
                avoid = this.sliderHostMimic.getTarget();
            }
            if (avoid != null) {
                Vec3 vec3 = this.findRandomPosition(avoid);
                if (vec3 != null) {
                    if (!(avoid.distanceToSqr(vec3.x, vec3.y, vec3.z) < avoid.distanceToSqr(this.sliderHostMimic))) {
                        this.posX = vec3.x;
                        this.posY = vec3.y;
                        this.posZ = vec3.z;
                        this.sliderHostMimic.getNavigation().moveTo(this.posX, this.posY, this.posZ, 1.5);
                    }
                }
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }

    public static class HostRandomMovementGoal extends Goal {
        private final SliderHostMimic sliderHostMimic;
        protected double posX;
        protected double posY;
        protected double posZ;

        public HostRandomMovementGoal(SliderHostMimic sliderHostMimic) {
            super();
            this.sliderHostMimic = sliderHostMimic;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (this.sliderHostMimic.isAwake()) {
                if (this.sliderHostMimic.getDeltaMovement().x() == 0 && this.sliderHostMimic.getDeltaMovement().z() == 0) {
                    Vec3 vec3 = this.findRandomPosition();
                    if (vec3 != null) {
                        this.posX = vec3.x;
                        this.posY = vec3.y;
                        this.posZ = vec3.z;
                    }
                    return true;
                }
            }
            return false;
        }

        protected Vec3 findRandomPosition() {
            if (this.sliderHostMimic.getDungeon() != null) {
                List<Vec3> positions = BlockPos.betweenClosedStream(this.sliderHostMimic.getDungeon().roomBounds()).map((pos) -> Vec3.atLowerCornerOf(pos.immutable())).toList();
                return positions.get(this.sliderHostMimic.getRandom().nextInt(positions.size()));
            } else {
                Vec3 vec3 = DefaultRandomPos.getPos(this.sliderHostMimic, 4, 2);
                if (vec3 == null || (this.sliderHostMimic.getDungeon() != null && !this.sliderHostMimic.getDungeon().roomBounds().contains(vec3))) {
                    return null;
                }
                return vec3;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !this.sliderHostMimic.getBoundingBox().contains(new Vec3(this.posX, this.posY, this.posZ))
                    && this.sliderHostMimic.isAwake()
                    && !this.sliderHostMimic.getNavigation().isDone()
                    && !this.sliderHostMimic.getNavigation().isStuck()
                    && this.sliderHostMimic.getTarget() != null;
        }

        @Override
        public void start() {
            this.sliderHostMimic.playSound(this.sliderHostMimic.getScareSound(), 2.5F, 1.0F / (this.sliderHostMimic.getRandom().nextFloat() * 0.2F + 0.9F));
            this.sliderHostMimic.getMoveControl().setWantedPosition(this.posX, this.posY, this.posZ, 1.5);
            this.sliderHostMimic.getNavigation().moveTo(this.posX, this.posY, this.posZ, 1.5);
        }

        @Override
        public void stop() {
            this.sliderHostMimic.getNavigation().stop();
        }
    }

    public static class ShootHostEyeGoal extends Goal {
        private final SliderHostMimic sliderHostMimic;
        private int attackTime;

        public ShootHostEyeGoal(SliderHostMimic sliderHostMimic) {
            this.sliderHostMimic = sliderHostMimic;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.sliderHostMimic.getEyeProjectiles().size() < 4) {
                LivingEntity livingentity = this.sliderHostMimic.getTarget();
                if (livingentity != null && livingentity.isAlive()) {
                    return this.sliderHostMimic.level().getDifficulty() != Difficulty.PEACEFUL;
                }
            }
            return false;
        }

        @Override
        public void start() {
            this.attackTime = 30;
        }

        @Override
        public void tick() {
            LivingEntity livingentity = this.sliderHostMimic.getTarget();
            if (livingentity != null) {
                if (this.sliderHostMimic.eyeProjectiles.size() < 4) {
                    if (this.attackTime <= 0) {
                        HostEyeProjectile hostEyeProjectile = new HostEyeProjectile(this.sliderHostMimic.level(), this.sliderHostMimic, this.sliderHostMimic.getDirection());
                        this.sliderHostMimic.level().addFreshEntity(hostEyeProjectile);
                        this.sliderHostMimic.playSound(this.sliderHostMimic.getShootSound(), 1.0F, 1.0F / (this.sliderHostMimic.getRandom().nextFloat() * 0.2F + 0.9F));
                        hostEyeProjectile.setPos(this.sliderHostMimic.position().add(0.0F, (this.sliderHostMimic.getBbHeight() / 2.0F) + 0.2F, 0.0F));
                        this.sliderHostMimic.eyeProjectiles.add(hostEyeProjectile);
                        this.attackTime = 30;
                    }
                }
            }
            --this.attackTime;
        }
    }

    public static class InactiveGoal extends Goal {
        private final SliderHostMimic sliderHostMimic;

        public InactiveGoal(SliderHostMimic sliderHostMimic) {
            this.sliderHostMimic = sliderHostMimic;
            this.sliderHostMimic.setRot(0, 0);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
        }

        @Override
        public boolean canUse() {
            return !this.sliderHostMimic.isAwake();
        }

        @Override
        public void start() {
            this.sliderHostMimic.setDeltaMovement(Vec3.ZERO);
            this.sliderHostMimic.setPos(this.sliderHostMimic.position().x(), this.sliderHostMimic.position().y(), this.sliderHostMimic.position().z());
        }
    }
}