package com.aetherteam.aether_genesis.entity.monster.boss;

import com.aetherteam.aether.api.BossNameGenerator;
import com.aetherteam.aether.api.BossRoomTracker;
import com.aetherteam.aether.entity.BossMob;
import com.aetherteam.aether.network.AetherPacketHandler;
import com.aetherteam.aether.network.packet.client.BossInfoPacket;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import com.aetherteam.aether_genesis.entity.miscellaneous.CogArrow;
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
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
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

public class LabyrinthEye extends PathfinderMob implements BossMob<LabyrinthEye>, Enemy, IEntityAdditionalSpawnData {
    public static final EntityDataAccessor<Boolean> DATA_AWAKE_ID = SynchedEntityData.defineId(LabyrinthEye.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Component> DATA_BOSS_NAME_ID = SynchedEntityData.defineId(LabyrinthEye.class, EntityDataSerializers.COMPONENT);
    public static final EntityDataAccessor<Integer> DATA_BOSS_STAGE = SynchedEntityData.defineId(LabyrinthEye.class, EntityDataSerializers.INT);

    public int chatTime;
    private final boolean[] stageDone = new boolean[13];
//    private int cappedAmount;

    private final ServerBossEvent bossFight;

    private BossRoomTracker<LabyrinthEye> bronzeDungeon;

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
        this.targetSelector.addGoal(4, new ArrowAttackCogGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoalBoss(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, LabyrinthEye.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, livingEntity -> this.isBossFight()));
    }

    public LabyrinthEye(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.bossFight = new ServerBossEvent(this.getBossName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);
        this.bossFight.setVisible(false);
        this.xpReward = XP_REWARD_BOSS;
        this.setPersistenceRequired();
        for (int i = 0; i < 12; i++)
            this.stageDone[i] = false;
    }

    private boolean isBossStage(int stage) {
        return switch (stage) {
            case 1 -> (getHealth() <= 250.0F && getHealth() >= 231.0F);
            case 2 -> (getHealth() < 231.0F && getHealth() >= 212.0F);
            case 3 -> (getHealth() < 212.0F && getHealth() >= 193.0F);
            case 4 -> (getHealth() < 193.0F && getHealth() >= 174.0F);
            case 5 -> (getHealth() < 174.0F && getHealth() >= 155.0F);
            case 6 -> (getHealth() < 155.0F && getHealth() >= 136.0F);
            case 7 -> (getHealth() < 136.0F && getHealth() >= 117.0F);
            case 8 -> (getHealth() < 117.0F && getHealth() >= 98.0F);
            case 9 -> (getHealth() < 98.0F && getHealth() >= 79.0F);
            case 10 -> (getHealth() < 79.0F && getHealth() >= 60.0F);
            case 11 -> (getHealth() < 60.0F && getHealth() >= 41.0F);
            case 12 -> (getHealth() < 41.0F && getHealth() >= 22.0F);
            case 13 -> (getHealth() < 3.0F);
            default -> false;
        };
    }

    private void setStage(int stage) {
        this.entityData.set(DATA_BOSS_STAGE, stage);
        this.entityData.get(DATA_BOSS_STAGE);
    }

    public int getStage() {
        return this.entityData.get(DATA_BOSS_STAGE);
    }

   @Override
   protected SoundEvent getDeathSound() {
       return GenesisSoundEvents.ENTITY_LABYRINTH_EYE_DEATH.get();
   }

    @Override
    protected SoundEvent getAmbientSound() {
        return GenesisSoundEvents.ENTITY_LABYRINTH_EYE_MOVE.get();
    }

    public void die(DamageSource source) {
        this.level.explode(this, this.position().x, this.position().y, this.position().z, 0.3F, false, Level.ExplosionInteraction.TNT);

        super.die(source);
    }

    public void spawnLargeCog(Entity entityToAttack, int stage) {
        if (this.stageDone[stage])
            return;
        CogArrow entityarrow = new CogArrow(this.level, this, true);
        entityarrow.setYRot(this.getYRot());
        entityarrow.setXRot(this.getXRot());
        double var3 = entityToAttack.position().x + entityToAttack.getMotionDirection().getStepX() - this.position().x;
        double var5 = entityToAttack.position().y + -this.getMotionDirection().getStepY();
        double var7 = entityToAttack.position().z + entityToAttack.getMotionDirection().getStepZ() - this.position().z;
        float var9 = (float) Math.sqrt(var3 * var3 + var7 * var7);
        if (!this.level.isClientSide) {
            float distance = var9 * 0.075F;
            entityarrow.shoot(var3, var5 + (var9 * 0.2F), var7, distance, 0.0F);
            this.playSound(GenesisSoundEvents.ENTITY_LABYRINTH_EYE_COGLOSS.get(), 2.0F, 1.0F);
            this.playSound(SoundEvents.ITEM_BREAK, 0.8F, 0.8F + this.level.random.nextFloat() * 0.4F);
            this.level.addFreshEntity(entityarrow);
        }
        stageDone[stage] = true;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.isAwake() || (this.getTarget() instanceof Player player && (player.isCreative() || player.isSpectator()))) {
            this.setTarget(null);
        }

        if (this.chatTime > 0) {
            this.chatTime--;
        }
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

    @Override
    public void checkDespawn() {}

    @Override
    public void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_AWAKE_ID, false);
        this.entityData.define(DATA_BOSS_STAGE, 13);
        this.entityData.define(DATA_BOSS_NAME_ID, Component.literal("Labyrinth Eye"));
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
        for (int stage = 0; stage < 13; stage++) {
            if (isBossStage(stage) && !this.stageDone[stage]) {
                setStage(stage);
                spawnLargeCog(this, stage);
            }
        }
        return super.hurt(source, damage);
    }

    protected float getJumpPower() {
        return 0.0F;
    }

    public LabyrinthEye self(){
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
    public BossRoomTracker<LabyrinthEye> getDungeon() {
        return this.bronzeDungeon;
    }

    @Override
    public void setDungeon(BossRoomTracker<LabyrinthEye> bossRoomTracker) {
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
        AetherPacketHandler.sendToPlayer(new BossInfoPacket.Display(this.bossFight.getId()), player);
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
        AetherPacketHandler.sendToPlayer(new BossInfoPacket.Remove(this.bossFight.getId()), player);
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

    public static MutableComponent generateGuardianName() {
        MutableComponent result = BossNameGenerator.generateBossName();
        return result.append(Component.translatable("gui.aether_genesis.labyrinth_eye.title"));
    }

    @Override
    public boolean isNoGravity() {
        return !isAwake();
    }

    @Override
    public float getYRot() {
        return !isAwake() ? 0 : super.getYRot();
    }

    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor pLevel, @Nonnull DifficultyInstance pDifficulty, @Nonnull MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
        this.alignSpawnPos();
        SpawnGroupData data = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        this.setBossName(generateGuardianName());
        return data;
    }

    public static class DoNothingGoal extends Goal {
        private final LabyrinthEye labyrinthEye;
        public DoNothingGoal(LabyrinthEye labyrinthEye) {
            this.labyrinthEye = labyrinthEye;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
        }

        @Override
        public boolean canUse() {
            return !this.labyrinthEye.isBossFight();
        }

        @Override
        public void start() {
            this.labyrinthEye.setDeltaMovement(Vec3.ZERO);
            this.labyrinthEye.setPos(this.labyrinthEye.position().x,
                    this.labyrinthEye.position().y,
                    this.labyrinthEye.position().z);
        }
    }

    @Override
    public void setCustomName(@javax.annotation.Nullable Component name) {
        super.setCustomName(name);
        this.setBossName(name);
    }

    public static class ArrowAttackCogGoal extends Goal {
        private final LabyrinthEye labyrinthEye;

        public ArrowAttackCogGoal(LabyrinthEye labyrinthEye) {
            this.labyrinthEye = labyrinthEye;
        }

        @Override
        public boolean canUse() {
            return this.labyrinthEye.isBossFight() && this.labyrinthEye.random.nextInt(20) == 0;
        }

        @Override
        public void start() {
            Entity cog = new CogArrow(this.labyrinthEye.level, this.labyrinthEye, false);
            this.labyrinthEye.level.addFreshEntity(cog);
            cog.setPos(labyrinthEye.position());
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }

    public static class LookAtPlayerGoalBoss extends LookAtPlayerGoal{
        private final LabyrinthEye labyrinthEye;

        public LookAtPlayerGoalBoss(LabyrinthEye pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance) {
            super(pMob, pLookAtType, pLookDistance);
            this.labyrinthEye = pMob;
        }

        public boolean canUse() {
        return super.canUse() && this.labyrinthEye.isBossFight();
        }
    }
}