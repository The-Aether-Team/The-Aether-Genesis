package com.aetherteam.genesis.entity.monster.dungeon.boss;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.entity.ai.goal.MostDamageTargetGoal;
import com.aetherteam.aether.entity.monster.dungeon.boss.BossNameGenerator;
import com.aetherteam.aether.event.AetherEventDispatch;
import com.aetherteam.aether.network.packet.clientbound.BossInfoPacket;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.genesis.entity.ai.goal.AvoidEnemyGoal;
import com.aetherteam.genesis.entity.projectile.CogProjectile;
import com.aetherteam.nitrogen.entity.BossRoomTracker;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
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
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import net.neoforged.neoforge.network.PacketDistributor;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

public class LabyrinthEye extends PathfinderMob implements AetherBossMob<LabyrinthEye>, Enemy, IEntityWithComplexSpawn {
    public static final EntityDataAccessor<Boolean> DATA_AWAKE_ID = SynchedEntityData.defineId(LabyrinthEye.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Component> DATA_BOSS_NAME_ID = SynchedEntityData.defineId(LabyrinthEye.class, EntityDataSerializers.COMPONENT);
    public static final EntityDataAccessor<Integer> DATA_BOSS_STAGE = SynchedEntityData.defineId(LabyrinthEye.class, EntityDataSerializers.INT);
    private static final Music MINIBOSS_MUSIC = new Music(GenesisSoundEvents.MUSIC_MINIBOSS, 0, 0, true);
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
    private BossRoomTracker<LabyrinthEye> bronzeDungeon;

    private final boolean[] stageDone = new boolean[13];
    public int chatCooldown;

    public LabyrinthEye(EntityType<? extends LabyrinthEye> entityType, Level level) {
        super(entityType, level);
        this.bossFight = new ServerBossEvent(this.getBossName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);
        this.setBossFight(false);
        this.bossFight.setVisible(false);
        this.xpReward = XP_REWARD_BOSS;
        this.setPersistenceRequired();
        for (int i = 0; i < 12; i++) {
            this.stageDone[i] = false;
        }
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData) {
        this.setBossName(BossNameGenerator.generateBossName(this.getRandom()).append(Component.translatable("gui.aether_genesis.labyrinth_eye.title")));
        this.moveTo(Mth.floor(this.getX()), this.getY(), Mth.floor(this.getZ()));
        return spawnData;
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0)
                .add(Attributes.MOVEMENT_SPEED, 0.27)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75)
                .add(Attributes.FOLLOW_RANGE, 64.0)
                .add(Attributes.STEP_HEIGHT, 1.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new LabyrinthEye.InactiveGoal(this));
        this.goalSelector.addGoal(1, new LabyrinthEye.CogAttackGoal(this, 1.25F, 25, 8.0F));
        this.goalSelector.addGoal(2, new LabyrinthEye.AvoidGoal(this));
//        this.goalSelector.addGoal(1, new LabyrinthEye.TrackPlayerGoal(this));
//        this.goalSelector.addGoal(2, new LabyrinthEye.LookAroundGoal(this));
//        this.goalSelector.addGoal(3, new LabyrinthEye.StrollGoal(this, 1.0));
//        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));

        this.mostDamageTargetGoal = new MostDamageTargetGoal(this);
        this.targetSelector.addGoal(1, this.mostDamageTargetGoal);
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, livingEntity -> this.isBossFight()));
    }

    @Override
    public void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_AWAKE_ID, false);
        builder.define(DATA_BOSS_NAME_ID, Component.literal("Labyrinth's Eye"));
        builder.define(DATA_BOSS_STAGE, 1);
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
        this.setXRot(0);
        AttributeInstance gravity = this.getAttribute(Attributes.GRAVITY);
        if (gravity != null) {
            double fallSpeed = Math.max(gravity.getValue() * -1.25, -0.1); // Entity isn't allowed to fall too slowly from gravity.
            if (this.getDeltaMovement().y() < fallSpeed) {
                this.setDeltaMovement(this.getDeltaMovement().x(), fallSpeed, this.getDeltaMovement().z());
                this.hasImpulse = true;
            }
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
    public void travel(Vec3 travelVector) {
        if (this.isAwake()) {
            super.travel(travelVector);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Optional<LivingEntity> damageResult = this.canDamageLabyrinthEye(source);
        if (damageResult.isPresent()) {
            if (super.hurt(source, amount) && this.getHealth() > 0) {
                if (!this.isBossFight()) {
                    this.start();
                }
                if (!this.level().isClientSide() && source.getEntity() instanceof LivingEntity living) {
                    this.mostDamageTargetGoal.addAggro(living, amount); // AI goal for being hurt.
                    for (int stage = 0; stage < 13; stage++) {
                        if (this.isBossStage(stage) && !this.stageDone[stage]) {
                            this.setStage(stage);
                            this.spawnLargeCog(stage);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private Optional<LivingEntity> canDamageLabyrinthEye(DamageSource source) {
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

    private Optional<LivingEntity> sendTooFarMessage(LivingEntity attacker) {
        if (!this.level().isClientSide() && attacker instanceof Player player) {
            if (this.getChatCooldown() <= 0) {
                this.displayTooFarMessage(player); // Too far from Slider
                this.setChatCooldown(15);
            }
        }
        return Optional.empty();
    }

    private boolean isBossStage(int stage) {
        float interval = this.getMaxHealth() / 13.0F;
        return this.getHealth() <= (this.getMaxHealth() - ((stage - 1) * interval))
                && this.getHealth() >= (this.getMaxHealth() - (stage * interval));
    }

    private void spawnLargeCog(int stage) {
        if (!this.stageDone[stage]) {
            LivingEntity target = this.getTarget();
            if (target != null) {
                CogProjectile cog = new CogProjectile(this.level(), this, true);
                cog.setYRot(this.getYRot());
                cog.setXRot(this.getXRot());
                cog.setPos(this.getCogPosition());
                double x = target.position().x() - cog.getX();
                double y = target.position().y() - cog.getY();
                double z = target.position().z() - cog.getZ();
                float dist = (float) Math.sqrt(x * x + z * z);
                if (!this.level().isClientSide()) {
                    float distance = dist * 0.075F;
                    cog.shoot(x, y + (dist * 0.2F), z, distance, 1.0F);
                    this.playSound(GenesisSoundEvents.ENTITY_LABYRINTH_EYE_COG_LOSS.get(), 2.0F, 1.0F);
                    this.playSound(GenesisSoundEvents.ENTITY_LABYRINTH_EYE_BREAK.get(), 0.8F, 0.8F + this.level().getRandom().nextFloat() * 0.4F);
                    this.level().addFreshEntity(cog);
                }
                this.stageDone[stage] = true;
            }
        }
    }

    public Vec3 getCogPosition() {
        float randomRot = this.getRandom().nextInt(360) * Mth.DEG_TO_RAD;
        Vec3 lookVec = this.getLookAngle();
        Vec3 upVec = new Vec3(0, 1, 0);
        Vec3 sideVec = lookVec.cross(upVec);
        Vec3 point = upVec.scale(Mth.cos(randomRot)).add(sideVec.scale(Mth.sin(randomRot))).scale(0.75);
        return this.position().add(point).add(0, this.getEyeHeight(), 0);
    }

    private void start() {
        this.setHealth(this.getMaxHealth());
        this.setAwake(true);
        this.setBossFight(true);
        if (this.getDungeon() != null) {
            this.closeRoom();
        }
        AetherEventDispatch.onBossFightStart(this, this.getDungeon());
    }

    public void reset() {
        this.setAwake(false);
        this.setBossFight(false);
        this.setTarget(null);
        if (this.getDungeon() != null) {
//            this.setDeltaMovement(Vec3.ZERO);
            this.setPos(this.getDungeon().originCoordinates());
            this.openRoom();
        }
        this.setStage(1);
        for (int i = 0; i < 12; i++) {
            this.stageDone[i] = false;
        }
        AetherEventDispatch.onBossFightStop(this, this.getDungeon());
    }

    @Override
    public void die(DamageSource source) {
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

    private void explode() {
        for (int i = 0; i < (this.getHealth() <= 0 ? 16 : 48); i++) {
            double x = this.position().x() + (double) (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 1.5;
            double y = this.getBoundingBox().minY + 1.75 + (double) (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 1.5;
            double z = this.position().z() + (double) (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 1.5;
            this.level().addParticle(ParticleTypes.POOF, x, y, z, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void checkDespawn() { }

    @Nullable
    @Override
    public BlockState convertBlock(BlockState state) {
        return DUNGEON_BLOCK_CONVERSIONS.getOrDefault(state.getBlock(), (blockState) -> null).apply(state);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        PacketDistributor.sendToPlayer(player, new BossInfoPacket.Display(this.bossFight.getId(), this.getId()));
        if (this.getDungeon() == null || this.getDungeon().isPlayerTracked(player)) {
            this.bossFight.addPlayer(player);
            AetherEventDispatch.onBossFightPlayerAdd(this, this.getDungeon(), player);
        }
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        PacketDistributor.sendToPlayer(player, new BossInfoPacket.Remove(this.bossFight.getId(), this.getId()));
        this.bossFight.removePlayer(player);
        AetherEventDispatch.onBossFightPlayerRemove(this, this.getDungeon(), player);
    }

    @Override
    public void onDungeonPlayerAdded(@javax.annotation.Nullable Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.bossFight.addPlayer(serverPlayer);
            AetherEventDispatch.onBossFightPlayerAdd(this, this.getDungeon(), serverPlayer);
        }
    }

    @Override
    public void onDungeonPlayerRemoved(@javax.annotation.Nullable Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.bossFight.removePlayer(serverPlayer);
            AetherEventDispatch.onBossFightPlayerRemove(this, this.getDungeon(), serverPlayer);
        }
    }

    public int getStage() {
        return this.entityData.get(DATA_BOSS_STAGE);
    }

    private void setStage(int stage) {
        this.entityData.set(DATA_BOSS_STAGE, stage);
    }

    public boolean isAwake() {
        return this.entityData.get(DATA_AWAKE_ID);
    }

    public void setAwake(boolean awake) {
        this.entityData.set(DATA_AWAKE_ID, awake);
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
    public BossRoomTracker<LabyrinthEye> getDungeon() {
        return this.bronzeDungeon;
    }

    @Override
    public void setDungeon(BossRoomTracker<LabyrinthEye> bossRoomTracker) {
        this.bronzeDungeon = bossRoomTracker;
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
        return ResourceLocation.fromNamespaceAndPath(Aether.MODID, "boss_bar/slider");
    }

    /**
     * @return The {@link ResourceLocation} for this boss's health bar background.
     */
    @Nullable
    @Override
    public ResourceLocation getBossBarBackgroundTexture() {
        return ResourceLocation.fromNamespaceAndPath(Aether.MODID, "boss_bar/slider_background");
    }

    /**
     * @return The {@link Music} for this boss's fight.
     */
    @Nullable
    @Override
    public Music getBossMusic() {
        return MINIBOSS_MUSIC;
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

    @Override
    protected SoundEvent getAmbientSound() {
        return GenesisSoundEvents.ENTITY_LABYRINTH_EYE_MOVE.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return GenesisSoundEvents.ENTITY_LABYRINTH_EYE_DEATH.get();
    }

    @Override
    protected float getJumpPower() {
        return 0.0F;
    }

    @Override
    public float getYRot() {
        return !this.isAwake() ? 0 : super.getYRot();
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        this.addBossSaveData(tag, this.registryAccess());
        tag.putBoolean("Awake", this.isAwake());
        tag.putIntArray("Stage", IntStream.range(0, this.stageDone.length).mapToObj(i -> this.stageDone[i]).map(bool -> bool.compareTo(false)).toList());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.readBossSaveData(tag, this.registryAccess());
        if (tag.contains("Awake")) {
            this.setAwake(tag.getBoolean("Awake"));
        }
        if (tag.contains("Stage")) {
            int[] stages = tag.getIntArray("Stage");
            for (int i : stages) {
                this.stageDone[i] = stages[i] != 0;
            }
        }
    }

    @Override
    public void writeSpawnData(RegistryFriendlyByteBuf buffer) {
        CompoundTag tag = new CompoundTag();
        this.addBossSaveData(tag, this.registryAccess());
        buffer.writeNbt(tag);
    }

    @Override
    public void readSpawnData(RegistryFriendlyByteBuf additionalData) {
        CompoundTag tag = additionalData.readNbt();
        if (tag != null) {
            this.readBossSaveData(tag, this.registryAccess());
        }
    }

    public static class CogAttackGoal extends Goal {
        private final LabyrinthEye labyrinthEye;
        private final double speedModifier;
        private final int attackIntervalMin;
        private final float attackRadiusSqr;
        private int attackTime;
        private int seeTime;
        private boolean strafingClockwise;
        private boolean strafingBackwards;
        private int strafingTime;

        public CogAttackGoal(LabyrinthEye labyrinthEye, double speedModifier, int attackInterval, float attackRadius) {
            this.attackTime = -1;
            this.strafingTime = -1;
            this.labyrinthEye = labyrinthEye;
            this.speedModifier = speedModifier;
            this.attackIntervalMin = attackInterval;
            this.attackRadiusSqr = attackRadius * attackRadius;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            return this.labyrinthEye.isAwake() && this.labyrinthEye.getTarget() != null;
        }

        @Override
        public boolean canContinueToUse() {
            return (this.canUse() || !this.labyrinthEye.getNavigation().isDone()) && this.labyrinthEye.isAwake();
        }

        public void start() {
            super.start();
            this.labyrinthEye.setAggressive(true);
        }

        @Override
        public void stop() {
            super.stop();
            this.labyrinthEye.setAggressive(false);
            this.seeTime = 0;
            this.attackTime = -1;
        }

        @Override
        public void tick() {
            LivingEntity target = this.labyrinthEye.getTarget();
            if (target != null) {
                double distance = this.labyrinthEye.distanceToSqr(target.getX(), target.getY(), target.getZ());
                boolean canSee = this.labyrinthEye.getSensing().hasLineOfSight(target);
                boolean hasSeen = this.seeTime > 0;

                if (canSee != hasSeen) {
                    this.seeTime = 0;
                }
                if (canSee) {
                    ++this.seeTime;
                } else {
                    --this.seeTime;
                }
                if (distance > this.attackRadiusSqr || this.seeTime < 20) {
                    this.labyrinthEye.getNavigation().moveTo(target, this.speedModifier);
                    this.strafingTime = -1;
                } else {
                    this.labyrinthEye.getNavigation().stop();
                    ++this.strafingTime;
                }
                if (this.strafingTime >= 20) {
                    if (this.labyrinthEye.getRandom().nextFloat() < 0.3F) {
                        this.strafingClockwise = !this.strafingClockwise;
                    }
                    if (this.labyrinthEye.getRandom().nextFloat() < 0.3F) {
                        this.strafingBackwards = !this.strafingBackwards;
                    }
                    this.strafingTime = 0;
                }
                if (this.strafingTime > -1) {
                    if (distance > this.attackRadiusSqr * 0.75F) {
                        this.strafingBackwards = false;
                    } else if (distance < this.attackRadiusSqr * 0.25F) {
                        this.strafingBackwards = true;
                    }
                    this.labyrinthEye.getMoveControl().strafe(this.strafingBackwards ? -0.75F : 0.75F, this.strafingClockwise ? 0.75F : -0.75F);
                    this.labyrinthEye.lookAt(target, 30.0F, 30.0F);
                } else {
                    this.labyrinthEye.getLookControl().setLookAt(target, 30.0F, 30.0F);
                }
                if (--this.attackTime <= 0 && this.seeTime >= -60) {
                    this.shootCog(target);
                    this.attackTime = this.attackIntervalMin;
                }
            }
        }

        private void shootCog(LivingEntity target) {
            CogProjectile cog = new CogProjectile(this.labyrinthEye.level(), this.labyrinthEye, false);
            cog.setYRot(this.labyrinthEye.getYRot());
            cog.setXRot(this.labyrinthEye.getXRot());
            cog.setPos(this.labyrinthEye.getCogPosition());
            double x = target.position().x() - cog.getX();
            double y = target.position().y() - cog.getY();
            double z = target.position().z() - cog.getZ();
            float dist = (float) Math.sqrt(x * x + z * z);
            float distance = dist * 0.075F;
            cog.shoot(x, y + (dist * 0.2F), z, distance, 20.0F);
            this.labyrinthEye.playSound(GenesisSoundEvents.ENTITY_LABYRINTH_EYE_COG_LOSS.get(), 2.0F, 1.0F);
            this.labyrinthEye.playSound(GenesisSoundEvents.ENTITY_LABYRINTH_EYE_BREAK.get(), 0.8F, 0.8F + this.labyrinthEye.level().getRandom().nextFloat() * 0.4F);
            this.labyrinthEye.level().addFreshEntity(cog);
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }

    public static class AvoidGoal extends AvoidEnemyGoal {
        private final LabyrinthEye labyrinthEye;

        public AvoidGoal(LabyrinthEye labyrinthEye) {
            super(labyrinthEye, 1.5);
            this.labyrinthEye = labyrinthEye;
        }

        @Override
        public boolean canUse() {
            return this.labyrinthEye.isAwake() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.labyrinthEye.isAwake() && super.canContinueToUse();
        }
    }

    public static class InactiveGoal extends Goal {
        private final LabyrinthEye labyrinthEye;

        public InactiveGoal(LabyrinthEye labyrinthEye) {
            this.labyrinthEye = labyrinthEye;
            this.labyrinthEye.setRot(0, 0);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
        }

        @Override
        public boolean canUse() {
            return !this.labyrinthEye.isAwake();
        }

        @Override
        public void start() {
            this.labyrinthEye.setDeltaMovement(Vec3.ZERO);
            this.labyrinthEye.setPos(this.labyrinthEye.position().x(), this.labyrinthEye.position().y(), this.labyrinthEye.position().z());
            this.labyrinthEye.getNavigation().stop();
        }
    }
}