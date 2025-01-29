package com.aetherteam.genesis.entity.monster.dungeon.boss;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.entity.ai.goal.ContinuousMeleeAttackGoal;
import com.aetherteam.aether.entity.ai.goal.MostDamageTargetGoal;
import com.aetherteam.aether.entity.monster.dungeon.Sentry;
import com.aetherteam.aether.entity.monster.dungeon.boss.BossNameGenerator;
import com.aetherteam.aether.event.AetherEventDispatch;
import com.aetherteam.aether.network.packet.clientbound.BossInfoPacket;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import com.aetherteam.nitrogen.entity.BossRoomTracker;
import com.aetherteam.nitrogen.network.PacketRelay;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
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
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class SentryGuardian extends PathfinderMob implements AetherBossMob<SentryGuardian>, Enemy, IEntityWithComplexSpawn {
    public static final EntityDataAccessor<Boolean> DATA_AWAKE_ID = SynchedEntityData.defineId(SentryGuardian.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Component> DATA_BOSS_NAME_ID = SynchedEntityData.defineId(SentryGuardian.class, EntityDataSerializers.COMPONENT);
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
    private BossRoomTracker<SentryGuardian> bronzeDungeon;

    public int chatCooldown;
    private int attackAnimationTick = 0;

    public SentryGuardian(EntityType<? extends SentryGuardian> entityType, Level level) {
        super(entityType, level);
        this.bossFight = new ServerBossEvent(this.getBossName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);
        this.bossFight.setVisible(false);
        this.xpReward = XP_REWARD_BOSS;
        this.setPersistenceRequired();
        this.setMaxUpStep(1.0F);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
        this.setBossName(BossNameGenerator.generateBossName(this.getRandom()).append(Component.translatable("gui.aether_genesis.sentry_guardian.title")));
        this.moveTo(Mth.floor(this.getX()), this.getY(), Mth.floor(this.getZ()));
        return spawnData;
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 350.0)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(Attributes.FOLLOW_RANGE, 64.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SentryGuardian.AttackPlayerGoal(this));
        this.goalSelector.addGoal(1, new SentryGuardian.LookAroundGoal(this));
        this.goalSelector.addGoal(2, new SentryGuardian.StrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new SentryGuardian.SummonSentryGoal(this));
        this.goalSelector.addGoal(4, new SentryGuardian.InactiveGoal(this));

        this.mostDamageTargetGoal = new MostDamageTargetGoal(this);
        this.targetSelector.addGoal(1, this.mostDamageTargetGoal);
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, livingEntity -> this.isBossFight()));
    }

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_AWAKE_ID, false);
        this.entityData.define(DATA_BOSS_NAME_ID, Component.literal("Sentry Guardian"));
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 4) {
            this.attackAnimationTick = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else {
            super.handleEntityEvent(pId);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.isAwake() || (this.getTarget() instanceof Player player && (player.isCreative() || player.isSpectator()))) {
            this.setTarget(null);
        }
        this.evaporate();
        this.spawnParticles();
        if (this.getChatCooldown() > 0) {
            this.chatCooldown--;
        }
        AttributeInstance gravity = this.getAttribute(NeoForgeMod.ENTITY_GRAVITY.value());
        if (gravity != null) {
            double fallSpeed = Math.max(gravity.getValue() * -3.0, -0.5); // Entity isn't allowed to fall too slowly from gravity.
            if (this.getDeltaMovement().y() < fallSpeed) {
                this.setDeltaMovement(this.getDeltaMovement().x(), fallSpeed, this.getDeltaMovement().z());
                this.hasImpulse = true;
            }
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            --this.attackAnimationTick;
        }
    }

    private void evaporate() {
        Pair<BlockPos, BlockPos> minMax = this.getDefaultBounds(this);
        AetherBossMob.super.evaporate(this, minMax.getLeft(), minMax.getRight(), (blockState) -> true);
    }

    private void spawnParticles() {
        if (this.level().isClientSide()) {
            if (this.getHealth() > 0.0F) {
                double a = (this.getRandom().nextFloat() - 0.5F);
                double b = this.getRandom().nextFloat();
                double c = (this.getRandom().nextFloat() - 0.5F);
                double x = this.position().x() + a * b;
                double y = this.getBoundingBox().minY + b - 0.30000001192092896D;
                double z = this.position().z() + c * b;
                if (!this.isAwake()) {
                    this.level().addParticle(new DustParticleOptions(Vec3.fromRGB24(4741535).toVector3f(), 1.0F), x, y, z, 0.29, 0.28, 0.48);
                } else {
                    this.level().addParticle(new DustParticleOptions(Vec3.fromRGB24(10429253).toVector3f(), 1.0F), x, y, z, 0.43, 0.18, 0.28);
                }
            }
        }
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossFight.setProgress(this.getHealth() / this.getMaxHealth());
        this.trackDungeon();
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackAnimationTick = 10;
        this.level().broadcastEntityEvent(this, (byte) 4);
        boolean flag = entity.hurt(this.damageSources().mobAttack(this), 5 + this.random.nextInt(3));
        if (flag) {
            double d2;
            if (entity instanceof LivingEntity living) {
                d2 = living.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
            } else {
                d2 = 0.0D;
            }
            double d0 = d2;
            double d1 = Math.max(0.0, 1.0 - d0);
            entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 0.4 * d1, 0.0));
            this.doEnchantDamageEffects(this, entity);
        }
        return flag;
    }

    public boolean hurt(DamageSource source, float amount) {
        Optional<LivingEntity> damageResult = this.canDamageSentryGuardian(source);
        if (damageResult.isPresent()) {
            if (super.hurt(source, amount) && this.getHealth() > 0) {
                if (!this.isBossFight()) {
                    this.start();
                }
                if (!this.level().isClientSide() && source.getEntity() instanceof LivingEntity living) {
                    this.mostDamageTargetGoal.addAggro(living, amount); // AI goal for being hurt.
                }
                return true;
            }
        }
        return false;
    }

    private Optional<LivingEntity> canDamageSentryGuardian(DamageSource source) {
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
            this.setPos(this.getDungeon().originCoordinates());
            this.openRoom();
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
    public BossRoomTracker<SentryGuardian> getDungeon() {
        return this.bronzeDungeon;
    }

    @Override
    public void setDungeon(BossRoomTracker<SentryGuardian> bossRoomTracker) {
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

    public int getAttackAnimationTick() {
        return this.attackAnimationTick;
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
        return GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_LIVING.get();
    }

    @Override
    protected SoundEvent getHurtSound( DamageSource damageSource) {
        return GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_HIT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_DEATH.get();
    }

    @Override
    protected float getJumpPower() {
        return 0.0F;
    }

    @Override
    public boolean isPushable() {
        return false;
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

    public static class AttackPlayerGoal extends ContinuousMeleeAttackGoal {
        private final SentryGuardian sentryGuardian;

        public AttackPlayerGoal(SentryGuardian sentryGuardian) {
            super(sentryGuardian, 1.0, false);
            this.sentryGuardian = sentryGuardian;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && this.sentryGuardian.isAwake();
        }
    }

    public static class LookAroundGoal extends RandomLookAroundGoal {
        private final SentryGuardian sentryGuardian;

        public LookAroundGoal(SentryGuardian sentryGuardian) {
            super(sentryGuardian);
            this.sentryGuardian = sentryGuardian;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && this.sentryGuardian.isAwake();
        }
    }

    public static class StrollGoal extends WaterAvoidingRandomStrollGoal {
        private final SentryGuardian sentryGuardian;

        public StrollGoal(SentryGuardian sentryGuardian, double speedModifier) {
            super(sentryGuardian, speedModifier);
            this.sentryGuardian = sentryGuardian;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && this.sentryGuardian.isAwake();
        }
    }

    public static class SummonSentryGoal extends Goal {
        private final SentryGuardian sentryGuardian;
        private int spawnDelay;

        public SummonSentryGoal(SentryGuardian sentryGuardian) {
            this.sentryGuardian = sentryGuardian;
        }

        @Override
        public boolean canUse() {
            if (this.sentryGuardian.isAwake()) {
                LivingEntity target = this.sentryGuardian.getTarget();
                if (target != null && target.isAlive()) {
                    return this.sentryGuardian.level().getDifficulty() != Difficulty.PEACEFUL;
                }
            }
            return false;
        }

        @Override
        public void tick() {
            if (this.sentryGuardian.level().getRandom().nextInt(75) == 1 && this.sentryGuardian.getTarget() != null) {
                this.spawnDelay = 10;
                this.sentryGuardian.setDeltaMovement(0, 0.5, 0);
            }
            this.spawnDelay--;
            if (this.spawnDelay == 0) {
                this.spawnSentry();
                this.spawnDelay = -1;
            }
            super.tick();
        }

        public void spawnSentry() {
            Sentry sentry = new Sentry(AetherEntityTypes.SENTRY.get(), this.sentryGuardian.level());
            sentry.setPos(this.sentryGuardian.position());
            sentry.setDeltaMovement(0.0, 1.0, 0.0);
            sentry.fallDistance = -100.0F;
            sentry.setTarget(this.sentryGuardian.getTarget());
            this.sentryGuardian.level().addFreshEntity(sentry);
            this.sentryGuardian.level().playSound(this.sentryGuardian, this.sentryGuardian.blockPosition(), GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_SUMMON.get(), SoundSource.AMBIENT, 2.0F, 1.0F);
        }
    }

    public static class InactiveGoal extends Goal {
        private final SentryGuardian sentryGuardian;

        public InactiveGoal(SentryGuardian sentryGuardian) {
            this.sentryGuardian = sentryGuardian;
            this.sentryGuardian.setRot(0, 0);
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
        }

        @Override
        public boolean canUse() {
            return !this.sentryGuardian.isAwake();
        }

        @Override
        public void start() {
            this.sentryGuardian.setDeltaMovement(Vec3.ZERO);
            this.sentryGuardian.setPos(this.sentryGuardian.position().x(), this.sentryGuardian.position().y(), this.sentryGuardian.position().z());
        }
    }
}