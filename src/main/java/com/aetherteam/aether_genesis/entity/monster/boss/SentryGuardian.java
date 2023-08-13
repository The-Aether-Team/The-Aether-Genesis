package com.aetherteam.aether_genesis.entity.monster.boss;

import com.aetherteam.aether.entity.AetherBossMob;
import com.aetherteam.aether.entity.ai.goal.ContinuousMeleeAttackGoal;
import com.aetherteam.aether.entity.monster.dungeon.Sentry;
import com.aetherteam.aether.entity.monster.dungeon.boss.BossNameGenerator;
import com.aetherteam.aether.network.AetherPacketHandler;
import com.aetherteam.aether.network.packet.serverbound.BossInfoPacket;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.nitrogen.entity.BossRoomTracker;
import com.aetherteam.nitrogen.network.PacketRelay;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.EnumSet;

import static com.aetherteam.aether.entity.AetherEntityTypes.SENTRY;

public class SentryGuardian extends PathfinderMob implements AetherBossMob<SentryGuardian>, Enemy, IEntityAdditionalSpawnData {
    public static final EntityDataAccessor<Boolean> DATA_AWAKE_ID = SynchedEntityData.defineId(SentryGuardian.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Component> DATA_BOSS_NAME_ID = SynchedEntityData.defineId(SentryGuardian.class, EntityDataSerializers.COMPONENT);

    public int chatTime;
    private int attackTime = 0;
//    private int cappedAmount;

    private final ServerBossEvent bossFight;

    private BossRoomTracker<SentryGuardian> bronzeDungeon;

    @Nonnull
    public static AttributeSupplier.Builder createMobAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 250)
                .add(Attributes.MOVEMENT_SPEED, 0.28)
                .add(Attributes.FOLLOW_RANGE, 8.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new DoNothingGoal(this));
        this.targetSelector.addGoal(2,  new ContinuousMeleeAttackGoal(this, 1.0, false));
        this.targetSelector.addGoal(3, new SummonSentryGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, SentryGuardian.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, livingEntity -> this.isBossFight()));
    }

    public int getAttackAnimationTick() {
        return this.attackTime;
    }

    public SentryGuardian(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.bossFight = new ServerBossEvent(this.getBossName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);
        this.bossFight.setVisible(false);
        this.xpReward = XP_REWARD_BOSS;
        this.setPersistenceRequired();
    }

    public void spawnSentry() {
       // if (!this.level.isClientSide && this.cappedAmount < 5) {
        if (!this.level.isClientSide) {
            Sentry sentry = new Sentry(SENTRY.get(), level);
            sentry.setPos(this.position());
            this.level.addFreshEntity(sentry);
            sentry.setDeltaMovement(0, 1, 0);
      //      sentry.fallDistance = -100.0F;
            sentry.setTarget(this.getTarget());
          //  this.cappedAmount++;
        //    sentry.setParent(this);
            this.level.playSound(this, this.blockPosition(), GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_SUMMON.get(), SoundSource.AMBIENT, 2.0F, 1.0F);
        }
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_HIT.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_LIVING.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_DEATH.get();
    }

    public void die(DamageSource source) {
        this.level.explode(this, this.position().x, this.position().y, this.position().z, 0.3F, false, Level.ExplosionInteraction.TNT);
        spawnSentry();
        super.die(source);
    }

    public void tick() {
        super.tick();
        if (this.attackTime > 0)
            this.attackTime--;
        if (!this.isAwake() || (this.getTarget() instanceof Player player && (player.isCreative() || player.isSpectator()))) {
            this.setTarget(null);
        }
        this.extinguishFire();
        if (getHealth() > 0.0F) {
            double a = (this.random.nextFloat() - 0.5F);
            double b = this.random.nextFloat();
            double c = (this.random.nextFloat() - 0.5F);
            double d = this.position().x + a * b;
            double e = this.getBoundingBox().minY + b - 0.30000001192092896D;
            double f = this.position().z + c * b;
            if (!isAwake()) {
                this.level.addParticle(new DustParticleOptions(Vec3.fromRGB24(10444703).toVector3f(), 1.0F), d, e, f, 0.28999999165534973D, 0.2800000011920929D, 0.47999998927116394D);
            } else {
                this.level.addParticle(new DustParticleOptions(Vec3.fromRGB24(9315170).toVector3f(), 1.0F), d, e, f, 0.4300000071525574D, 0.18000000715255737D, 0.2800000011920929D);
            }
        }
        if (this.chatTime > 0)
            this.chatTime--;
    }

    public void reset() {
        this.setAwake(false);
        this.setBossFight(false);
        this.setTarget(null);
        this.setHealth(this.getMaxHealth());
        if (this.getDungeon() != null) {
            this.setPos(this.getDungeon().originCoordinates());
            this.openRoom();
        }
    }

    public boolean doHurtTarget(Entity pEntity) {
        this.attackTime = 10;
        this.level.broadcastEntityEvent(this, (byte)4);
        float f = 7 + this.random.nextInt(15);
        float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        boolean flag = pEntity.hurt(this.damageSources().mobAttack(this), f1);
        if (flag) {
            double d2;
            if (pEntity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity)pEntity;
                d2 = livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE);
            } else {
                d2 = 0.0D;
            }

            double d0 = d2;
            double d1 = Math.max(0.0D, 1.0D - d0);
            pEntity.setDeltaMovement(pEntity.getDeltaMovement().add(0.0D, (double)0.4F * d1, 0.0D));
            this.doEnchantDamageEffects(this, pEntity);
        }

        this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        return flag;
    }

    @Override
    public void checkDespawn() {}

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_AWAKE_ID, false);
        this.entityData.define(DATA_BOSS_NAME_ID, Component.literal("Sentry Guardian"));
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        this.addBossSaveData(tag);
        tag.putBoolean("Awake", this.isAwake());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.readBossSaveData(tag);
        if (tag.contains("Awake")) {
            this.setAwake(tag.getBoolean("Awake"));
        }
    }

    public boolean hurt(DamageSource source, float damage) {
        Entity entity = source.getDirectEntity();
        Entity attacker = source.getEntity();
        if (entity != null && source.is(DamageTypeTags.IS_PROJECTILE)) {
            if (!this.level.isClientSide && attacker instanceof Player && ((Player)attacker).getMainHandItem() != Items.AIR.getDefaultInstance()) {
                this.chatTime = 60;
                attacker.sendSystemMessage(Component.translatable("gui.aether_genesis.boss.message.projectile"));
            }
            return false;
        }
        if (!this.isBossFight()) {
            this.setAwake(true);
            this.setBossFight(true);
        }
        return super.hurt(source, damage);
    }

    protected float getJumpPower() {
        return 0.0F;
    }

    public SentryGuardian self(){
        return this;
    }

    @Override
    public boolean isBossFight() {
        return this.bossFight.isVisible();
    }

    @Override
    public void setBossFight(boolean isFighting) {
        this.bossFight.setVisible(isFighting);
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
    public int getDeathScore() {
        return this.deathScore;
    }

    @Nullable
    @Override
    public BlockState convertBlock(BlockState blockState) {
        return null;
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

    @Override
    public void startSeenByPlayer(@Nonnull ServerPlayer player) {
        super.startSeenByPlayer(player);
        PacketRelay.sendToPlayer(AetherPacketHandler.INSTANCE, new BossInfoPacket.Display(this.bossFight.getId()), player);
        if (this.getDungeon() == null || this.getDungeon().isPlayerTracked(player)) {
            this.bossFight.addPlayer(player);
        }
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossFight.setProgress(this.getHealth() / this.getMaxHealth());
        this.trackDungeon();
    }

    @Override
    public void stopSeenByPlayer(@Nonnull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        PacketRelay.sendToPlayer(AetherPacketHandler.INSTANCE, new BossInfoPacket.Remove(this.bossFight.getId()), player);
        this.bossFight.removePlayer(player);
    }

    @Override
    public void onDungeonPlayerAdded(@javax.annotation.Nullable Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            this.bossFight.addPlayer(serverPlayer);
        }
    }

    @Override
    public void onDungeonPlayerRemoved(@javax.annotation.Nullable Player player) {
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

    protected void alignSpawnPos() {
        this.moveTo(Mth.floor(this.getX()), this.getY(), Mth.floor(this.getZ()));
    }

    public MutableComponent generateGuardianName() {
        MutableComponent result = BossNameGenerator.generateBossName(this.getRandom());
        return result.append(Component.translatable("gui.aether_genesis.sentry_guardian.title"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor pLevel, @Nonnull DifficultyInstance pDifficulty, @Nonnull MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
        this.alignSpawnPos();
        SpawnGroupData data = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        this.setBossName(generateGuardianName());
        return data;
    }

    public static class DoNothingGoal extends Goal {
        private final SentryGuardian sentryGuardian;
        public DoNothingGoal(SentryGuardian sentryGuardian) {
            this.sentryGuardian = sentryGuardian;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
        }

        @Override
        public boolean canUse() {
            return !this.sentryGuardian.isBossFight();
        }

        @Override
        public void start() {
            this.sentryGuardian.setDeltaMovement(Vec3.ZERO);
            this.sentryGuardian.setPos(this.sentryGuardian.position().x,
                    this.sentryGuardian.position().y,
                    this.sentryGuardian.position().z);
        }
    }

    @Override
    public void setCustomName(@javax.annotation.Nullable Component name) {
        super.setCustomName(name);
        this.setBossName(name);
    }

    public static class SummonSentryGoal extends Goal {
        private final Mob mob;
        private int heightOffsetUpdateTime = 10;
        private float heightOffset = 0.5F;

        public SummonSentryGoal(Mob mob) {
            this.mob = mob;
        }

        public void spawnSentry() {
            // if (!this.level.isClientSide && this.cappedAmount < 5) {
            if (!this.mob.level.isClientSide) {
                Sentry sentry = new Sentry(SENTRY.get(), this.mob.level);
                sentry.setPos(this.mob.position());
                this.mob.level.addFreshEntity(sentry);
                sentry.setDeltaMovement(0, 1, 0);
                //      sentry.fallDistance = -100.0F;
                sentry.setTarget(this.mob.getTarget());
                //  this.cappedAmount++;
                //    sentry.setParent(this);
                this.mob.level.playSound(this.mob, this.mob.blockPosition(), GenesisSoundEvents.ENTITY_SENTRY_GUARDIAN_SUMMON.get(), SoundSource.AMBIENT, 2.0F, 1.0F);
            }
        }

        public void tick() {
            if (!this.mob.level.isClientSide) {
                if (this.mob.level.random.nextInt(100) == 1 && this.mob.getTarget() != null)
                    spawnSentry();
                this.heightOffsetUpdateTime--;
                if (this.heightOffsetUpdateTime <= 0) {
                    this.heightOffsetUpdateTime = 100;
                    this.heightOffset = 0.5F + (float)this.mob.level.random.nextGaussian() * 3.0F;
                }
                if (this.mob.getTarget() != null && (this.mob.getTarget()).position().y + this.mob.getTarget().getEyeHeight() > this.mob.position().y + this.mob.getEyeHeight() + this.heightOffset)
                    this.mob.setDeltaMovement(0,  0.700000011920929D * 0.700000011920929D, 0);
            }
            if (!this.mob.isOnGround() && this.mob.getMotionDirection().getStepY() < 0.0D)
                this.mob.setDeltaMovement(0, 0.8D, 0);
            super.tick();
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.mob.getTarget();
            if (target != null && target.isAlive()) {
                return this.mob.level.getDifficulty() != Difficulty.PEACEFUL;
            } else {
                return false;
            }
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }
}