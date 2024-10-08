package com.aetherteam.genesis.entity.monster.dungeon.boss;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.client.AetherSoundEvents;
import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.entity.monster.dungeon.boss.BossNameGenerator;
import com.aetherteam.aether.event.AetherEventDispatch;
import com.aetherteam.aether.network.packet.clientbound.BossInfoPacket;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.entity.projectile.HostEyeProjectile;
import com.aetherteam.nitrogen.entity.BossRoomTracker;
import com.aetherteam.nitrogen.network.PacketRelay;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SliderHostMimic extends PathfinderMob implements AetherBossMob<SliderHostMimic>, Enemy, IEntityWithComplexSpawn {
    public static final EntityDataAccessor<Boolean> DATA_AWAKE_ID = SynchedEntityData.defineId(SliderHostMimic.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Component> DATA_BOSS_NAME_ID = SynchedEntityData.defineId(SliderHostMimic.class, EntityDataSerializers.COMPONENT);
    private static final Music MINIBOSS_MUSIC = new Music(GenesisSoundEvents.MUSIC_MINIBOSS, 0, 0, true);
    public static final Map<Block, Function<BlockState, BlockState>> DUNGEON_BLOCK_CONVERSIONS = Map.ofEntries(
            Map.entry(AetherBlocks.LOCKED_CARVED_STONE.get(), (blockState) -> AetherBlocks.CARVED_STONE.get().defaultBlockState()),
            Map.entry(AetherBlocks.LOCKED_SENTRY_STONE.get(), (blockState) -> AetherBlocks.SENTRY_STONE.get().defaultBlockState()),
            Map.entry(AetherBlocks.BOSS_DOORWAY_CARVED_STONE.get(), (blockState) -> Blocks.AIR.defaultBlockState()),
            Map.entry(AetherBlocks.TREASURE_DOORWAY_CARVED_STONE.get(), (blockState) -> AetherBlocks.SKYROOT_TRAPDOOR.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, blockState.getValue(HorizontalDirectionalBlock.FACING)))
    );

    private BossRoomTracker<SliderHostMimic> bronzeDungeon;
    private final ServerBossEvent bossFight;

    private int chatCooldown;
    public int sendDelay = 15;
    public int sendRespawnDelay = 10;
    public int scareTime = 0;

    public List<HostEyeProjectile> eyes = new ArrayList<>();

    public SliderHostMimic(EntityType<? extends SliderHostMimic> entityType, Level level) {
        super(entityType, level);
        this.bossFight = new ServerBossEvent(this.getBossName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS);
        this.bossFight.setVisible(false);
        this.xpReward = XP_REWARD_BOSS;
        this.setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new DoNothingGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 3.0F, 1.25F, 2.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, SliderHostMimic.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, livingEntity -> this.isBossFight()));
    }

    public SliderHostMimic self(){
        return this;
    }

    /**
     * Generates a name for the boss and adjusts its position.
     */
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level,  DifficultyInstance difficulty,  MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        this.alignSpawnPos();
        SpawnGroupData data = super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
        this.setBossName(this.generateHostName());
        return data;
    }

    public void sendEye() {
        while (this.eyes.size() > 4) (this.eyes.remove(0)).kill();
        HostEyeProjectile eye = new HostEyeProjectile(GenesisEntityTypes.HOST_EYE.get(), level());
        this.level().addFreshEntity(new HostEyeProjectile(this.level(), this, livingentity, Shulker.this.getAttachFace().getAxis()));
        eye.setPos(this.position());
        this.level().playSound(this, this.blockPosition(), AetherSoundEvents.ENTITY_SLIDER_AWAKEN.get(), SoundSource.HOSTILE, 2.5F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.eyes.add(eye);
        this.sendDelay = 30;
        if (isDeadOrDying())
            killEyes();
    }

    public void killEyes() {
        while (this.eyes.size() != 0)
            this.eyes.remove(0).discard();
    }

    public MutableComponent generateHostName() {
        MutableComponent result = BossNameGenerator.generateBossName(this.getRandom());
        return result.append(Component.translatable("gui.aether_genesis.host.title"));
    }

    protected void alignSpawnPos() {
        this.moveTo(Mth.floor(this.getX()), this.getY(), Mth.floor(this.getZ()));
    }

    public static AttributeSupplier.Builder createHostAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75)
                .add(Attributes.FOLLOW_RANGE, 64.0);
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
        else this.evaporate();

        if (this.getChatCooldown() > 0) {
            this.chatCooldown--;
        }
    }

    @Override
    public void checkDespawn() {}

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossFight.setProgress(this.getHealth() / this.getMaxHealth());
        this.trackDungeon();
    }

    public boolean hurt(DamageSource source, float damage) {
        Entity entity = source.getDirectEntity();
        Entity attacker = source.getEntity();
        if (entity != null && source.is(DamageTypeTags.IS_PROJECTILE)) {
            if (!this.level().isClientSide && attacker instanceof Player && ((Player)attacker).getMainHandItem() != Items.AIR.getDefaultInstance()) {
                this.chatCooldown = 60;
                attacker.sendSystemMessage(Component.translatable("gui.aether_genesis.boss.message.projectile"));
            }
            return false;
        }
        if (!this.isBossFight()) {
            if (this.getAwakenSound() != null) {
                this.playSound(this.getAwakenSound(), 2.5F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
            }
            this.setHealth(this.getMaxHealth());
            this.setAwake(true);
            this.setBossFight(true);
            if (this.getDungeon() != null) {
                this.closeRoom();
            }
            AetherEventDispatch.onBossFightStart(this, this.getDungeon());
        }
        return super.hurt(source, damage);
    }

    @Override
    public void die(DamageSource source) {
        this.setDeltaMovement(Vec3.ZERO);
        killEyes();
        this.explode();
        if (this.level() instanceof ServerLevel) {
            this.bossFight.setProgress(this.getHealth() / this.getMaxHealth());
            if (this.getDungeon() != null) {
                this.getDungeon().grantAdvancements(source);
                this.tearDownRoom();
            }
        }
        super.die(source);
    }

    private void evaporate() {
        Player player = this.level().getNearestPlayer(this, 8.5D);
        if (this.getTarget() == null)
            if (player != null && canAttack(player) && !player.isDeadOrDying() && !player.isCreative() && !player.isSpectator()) {
                this.setTarget(player);
            }
        if (this.getTarget() != null && canAttack(this.getTarget()) && !this.getTarget().isDeadOrDying()) {
            if (this.eyes.size() < 4) {
                    if (this.sendDelay <= 0)
                        if (!this.level().isClientSide)
                            sendEye();
                } else if (this.sendRespawnDelay <= 0) {
                    if (!this.level().isClientSide) {
                        sendEye();
                        this.sendRespawnDelay = 100;
                    }
                }
        }
        if (!this.isAwake())
            killEyes();
        if (this.eyes.size() > 4)
            this.eyes.remove(0).discard();
        if (this.scareTime > 0)
            this.scareTime--;
        if (this.sendDelay > 0)
            this.sendDelay--;
        if (this.sendRespawnDelay > 0)
            this.sendRespawnDelay--;
    }

    private void stop() {
        this.setDeltaMovement(Vec3.ZERO);
    }

    public void reset() {
        this.stop();
        this.setAwake(false);
        this.setBossFight(false);
        this.setTarget(null);
        this.setHealth(this.getMaxHealth());
        if (this.getDungeon() != null) {
            this.setPos(this.getDungeon().originCoordinates());
            this.openRoom();
        }
        AetherEventDispatch.onBossFightStop(this, this.getDungeon());
    }

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

    private void explode() {
        for (int i = 0; i < (this.getHealth() <= 0 ? 16 : 48); i++) {
            double x = this.position().x() + (double) (this.random.nextFloat() - this.random.nextFloat()) * 1.5;
            double y = this.getBoundingBox().minY + 1.75 + (double) (this.random.nextFloat() - this.random.nextFloat()) * 1.5;
            double z = this.position().z() + (double) (this.random.nextFloat() - this.random.nextFloat()) * 1.5;
            this.level().addParticle(ParticleTypes.POOF, x, y, z, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        PacketRelay.sendToPlayer(new BossInfoPacket.Display(this.bossFight.getId(), this.getId()), player);
        if (this.getDungeon() == null || this.getDungeon().isPlayerTracked(player)) {
            this.bossFight.addPlayer(player);
        }
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        PacketRelay.sendToPlayer(new BossInfoPacket.Remove(this.bossFight.getId(), this.getId()), player);
        this.bossFight.removePlayer(player);
    }

    @Override
    public void onDungeonPlayerAdded(@Nullable Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.bossFight.addPlayer(serverPlayer);
        }
    }

    @Override
    public void onDungeonPlayerRemoved(@Nullable Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.bossFight.removePlayer(serverPlayer);
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

    @Override
    public BossRoomTracker<SliderHostMimic> getDungeon() {
        return this.bronzeDungeon;
    }

    @Override
    public void setDungeon(BossRoomTracker<SliderHostMimic> dungeon) {
        this.bronzeDungeon = dungeon;
    }

    @Override
    public int getDeathScore() {
        return this.deathScore;
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
        return MINIBOSS_MUSIC;
    }

    protected SoundEvent getAwakenSound() {
        return AetherSoundEvents.ENTITY_SLIDER_AWAKEN.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return random.nextInt(5) == 0 ? GenesisSoundEvents.ENTITY_TRACKING_GOLEM_CREEPY_SEEN.get() : null;
    }

    @Override
    protected SoundEvent getHurtSound( DamageSource damageSource) {
        return AetherSoundEvents.ENTITY_SLIDER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AetherSoundEvents.ENTITY_SLIDER_DEATH.get();
    }

    
    @Override
    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        this.setBossName(name);
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return target.canBeSeenAsEnemy();
    }

    @Override
    public float getYRot() {
        return !isAwake() ? 0 : super.getYRot();
    }

    @Override
    protected boolean canRide( Entity vehicle) {
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
        return !isAwake();
    }

    @Override
    public boolean shouldDiscardFriction() {
        return !isAwake();
    }

    @Override
    public void addAdditionalSaveData( CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        this.addBossSaveData(tag);
        tag.putBoolean("Awake", this.isAwake());
    }

    @Override
    public void readAdditionalSaveData( CompoundTag tag) {
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
 

    public int getChatCooldown() {
        return this.chatCooldown;
    }

    public static class DoNothingGoal extends Goal {
        private final SliderHostMimic sliderHostMimic;
        public DoNothingGoal(SliderHostMimic sliderHostMimic) {
            this.sliderHostMimic = sliderHostMimic;
            this.sliderHostMimic.setRot(0, 0);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
        }

        @Override
        public boolean canUse() {
            return !this.sliderHostMimic.isBossFight();
        }

        @Override
        public void start() {
            this.sliderHostMimic.setDeltaMovement(Vec3.ZERO);
            this.sliderHostMimic.setPos(this.sliderHostMimic.position().x,
                    this.sliderHostMimic.position().y,
                    this.sliderHostMimic.position().z);
        }
    }
}