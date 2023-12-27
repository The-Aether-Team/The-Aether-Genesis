package com.aetherteam.aether_genesis.entity;

import com.aetherteam.aether.data.resources.AetherMobCategory;
import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether.entity.monster.dungeon.Mimic;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.entity.companion.*;
import com.aetherteam.aether_genesis.entity.miscellaneous.*;
import com.aetherteam.aether_genesis.entity.monster.BattleSentry;
import com.aetherteam.aether_genesis.entity.monster.SkyrootMimic;
import com.aetherteam.aether_genesis.entity.monster.Tempest;
import com.aetherteam.aether_genesis.entity.monster.TrackingGolem;
import com.aetherteam.aether_genesis.entity.monster.boss.LabyrinthEye;
import com.aetherteam.aether_genesis.entity.monster.boss.SentryGuardian;
import com.aetherteam.aether_genesis.entity.monster.boss.SliderHostMimic;
import com.aetherteam.aether_genesis.entity.passive.CarrionSprout;
import com.aetherteam.aether_genesis.entity.projectile.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Genesis.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Genesis.MODID);

    // Passive Mobs
    public static final RegistryObject<EntityType<CarrionSprout>> CARRION_SPROUT = ENTITY_TYPES.register("carrion_sprout",
            () -> EntityType.Builder.of(CarrionSprout::new, MobCategory.CREATURE).sized(1.0F, 1.0F).clientTrackingRange(8).build("carrion_sprout"));

    // Hostile Mobs
    public static final RegistryObject<EntityType<Swet>> DARK_SWET = ENTITY_TYPES.register("dark_swet",
            () -> EntityType.Builder.of(Swet::new, AetherMobCategory.AETHER_SURFACE_MONSTER).sized(0.9F, 0.95F).clientTrackingRange(10).build("dark_swet"));
    public static final RegistryObject<EntityType<Tempest>> TEMPEST = ENTITY_TYPES.register("tempest",
            () -> EntityType.Builder.of(Tempest::new, AetherMobCategory.AETHER_SKY_MONSTER).sized(4.5F, 3.5F).fireImmune().clientTrackingRange(10).build("tempest"));
    public static final RegistryObject<EntityType<BattleSentry>> BATTLE_SENTRY = ENTITY_TYPES.register("battle_sentry",
            () -> EntityType.Builder.of(BattleSentry::new, MobCategory.MONSTER).sized(2.0F, 2.0F).clientTrackingRange(10).build("battle_sentry"));
    public static final RegistryObject<EntityType<TrackingGolem>> TRACKING_GOLEM = ENTITY_TYPES.register("tracking_golem",
            () -> EntityType.Builder.of(TrackingGolem::new, MobCategory.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build("tracking_golem"));
    public static final RegistryObject<EntityType<SkyrootMimic>> SKYROOT_MIMIC = ENTITY_TYPES.register("skyroot_mimic",
            () -> EntityType.Builder.of(SkyrootMimic::new, MobCategory.MONSTER).sized(1.0F, 2.0F).clientTrackingRange(8).build("skyroot_mimic"));

    public static final RegistryObject<EntityType<SentryGuardian>> SENTRY_GUARDIAN = ENTITY_TYPES.register("sentry_guardian",
            () -> EntityType.Builder.of(SentryGuardian::new, MobCategory.MONSTER).sized(2.25F, 2.5F).fireImmune().clientTrackingRange(10).build("sentry_guardian"));
    public static final RegistryObject<EntityType<SliderHostMimic>> SLIDER_HOST_MIMIC = ENTITY_TYPES.register("slider_host_mimic",
            () -> EntityType.Builder.of(SliderHostMimic::new, MobCategory.MONSTER).sized(2, 2.5F).clientTrackingRange(10).build("slider_host_mimic"));
    public static final RegistryObject<EntityType<LabyrinthEye>> LABYRINTH_EYE = ENTITY_TYPES.register("labyrinth_eye",
            () -> EntityType.Builder.of(LabyrinthEye::new, MobCategory.MONSTER).sized(2, 2).clientTrackingRange(8).build("labyrinth_eye"));

    // Miscellaneous
    public static final RegistryObject<EntityType<BlueParachute>> BLUE_PARACHUTE = ENTITY_TYPES.register("blue_parachute",
            () -> EntityType.Builder.of(BlueParachute::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(8).build("blue_parachute"));
    public static final RegistryObject<EntityType<GreenParachute>> GREEN_PARACHUTE = ENTITY_TYPES.register("green_parachute",
            () -> EntityType.Builder.of(GreenParachute::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(8).build("green_parachute"));
    public static final RegistryObject<EntityType<PurpleParachute>> PURPLE_PARACHUTE = ENTITY_TYPES.register("purple_parachute",
            () -> EntityType.Builder.of(PurpleParachute::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(8).build("purple_parachute"));

    // Companions
    public static final RegistryObject<EntityType<FleetingWisp>> FLEETING_WISP = ENTITY_TYPES.register("fleeting_wisp",
            () -> EntityType.Builder.of(FleetingWisp::new, MobCategory.MISC).sized(0.8F, 1.8F).clientTrackingRange(10).build("fleeting_wisp"));
    public static final RegistryObject<EntityType<SoaringWisp>> SOARING_WISP = ENTITY_TYPES.register("soaring_wisp",
            () -> EntityType.Builder.of(SoaringWisp::new, MobCategory.MISC).sized(0.8F, 1.8F).clientTrackingRange(10).build("soaring_wisp"));
    public static final RegistryObject<EntityType<EtherealWisp>> ETHEREAL_WISP = ENTITY_TYPES.register("ethereal_wisp",
            () -> EntityType.Builder.of(EtherealWisp::new, MobCategory.MISC).sized(0.8F, 1.8F).clientTrackingRange(10).build("ethereal_wisp"));
    public static final RegistryObject<EntityType<ShadeOfArkenzus>> SHADE_OF_ARKENZUS = ENTITY_TYPES.register("shade_of_arkenzus",
            () -> EntityType.Builder.of(ShadeOfArkenzus::new, MobCategory.MISC).sized(0.4F, 1.0F).clientTrackingRange(10).build("shade_of_arkenzus"));
    public static final RegistryObject<EntityType<FrostpineTotem>> FROSTPINE_TOTEM = ENTITY_TYPES.register("frostpine_totem",
            () -> EntityType.Builder.of(FrostpineTotem::new, MobCategory.MISC).sized(0.8F, 1.8F).clientTrackingRange(10).build("frostpine_totem"));
    public static final RegistryObject<EntityType<BabyPinkSwet>> BABY_PINK_SWET = ENTITY_TYPES.register("baby_pink_swet",
            () -> EntityType.Builder.of(BabyPinkSwet::new, MobCategory.MISC).sized(0.9F, 0.95F).clientTrackingRange(10).build("baby_pink_swet"));

    // Projectile
    public static final RegistryObject<EntityType<DaggerfrostSnowball>> DAGGERFROST_SNOWBALL = ENTITY_TYPES.register("daggerfrost_snowball",
            () -> EntityType.Builder.<DaggerfrostSnowball>of(DaggerfrostSnowball::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("daggerfrost_snowball"));
    public static final RegistryObject<EntityType<TempestThunderBall>> TEMPEST_THUNDERBALL = ENTITY_TYPES.register("tempest_thunderball",
            () -> EntityType.Builder.<TempestThunderBall>of(TempestThunderBall::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(10).build("tempest_thunderball"));
    public static final RegistryObject<EntityType<PhoenixDart>> PHOENIX_DART = ENTITY_TYPES.register("phoenix_dart",
            () -> EntityType.Builder.<PhoenixDart>of(PhoenixDart::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("phoenix_dart"));
    public static final RegistryObject<EntityType<ContinuumBomb>> CONTINUUM_BOMB = ENTITY_TYPES.register("continuum_bomb",
            () -> EntityType.Builder.<ContinuumBomb>of(ContinuumBomb::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("continuum_bomb"));
    public static final RegistryObject<EntityType<RewardItem>> REWARD_ITEM = ENTITY_TYPES.register("reward_item",
            () -> EntityType.Builder.<RewardItem>of(RewardItem::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("reward_item"));
    public static final RegistryObject<EntityType<HostEyeProjectile>> HOST_EYE = ENTITY_TYPES.register("host_eye",
            () -> EntityType.Builder.of(HostEyeProjectile::new, MobCategory.MISC).sized(0.4F, 0.4F).clientTrackingRange(10).fireImmune().build("host_eye"));
    public static final RegistryObject<EntityType<CogProjectile>> COG_ARROW = ENTITY_TYPES.register("cog_arrow",
            () -> EntityType.Builder.<CogProjectile>of(CogProjectile::new, MobCategory.MISC).clientTrackingRange(4).updateInterval(10).sized(0.9F, 0.9F).fireImmune().build("cog_arrow"));

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(GenesisEntityTypes.CARRION_SPROUT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CarrionSprout::checkCarrionSproutSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(GenesisEntityTypes.DARK_SWET.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Swet::checkSwetSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
        event.register(GenesisEntityTypes.TEMPEST.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Tempest::checkTempestSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(GenesisEntityTypes.CARRION_SPROUT.get(), CarrionSprout.createMobAttributes().build());
        event.put(GenesisEntityTypes.DARK_SWET.get(), Swet.createMobAttributes().build());
        event.put(GenesisEntityTypes.TEMPEST.get(), Tempest.createMobAttributes().build());
        event.put(GenesisEntityTypes.BATTLE_SENTRY.get(), BattleSentry.createMobAttributes().build());
        event.put(GenesisEntityTypes.TRACKING_GOLEM.get(), TrackingGolem.createMobAttributes().build());
        event.put(GenesisEntityTypes.SKYROOT_MIMIC.get(), Mimic.createMobAttributes().build());
        event.put(GenesisEntityTypes.SENTRY_GUARDIAN.get(), SentryGuardian.createMobAttributes().build());
        event.put(GenesisEntityTypes.SLIDER_HOST_MIMIC.get(), SliderHostMimic.createHostAttributes().build());
        event.put(GenesisEntityTypes.LABYRINTH_EYE.get(), LabyrinthEye.createMobAttributes().build());
        event.put(GenesisEntityTypes.HOST_EYE.get(), HostEyeProjectile.createMobAttributes().build());
        event.put(GenesisEntityTypes.FLEETING_WISP.get(), Wisp.createAttributes().build());
        event.put(GenesisEntityTypes.SOARING_WISP.get(), Wisp.createAttributes().build());
        event.put(GenesisEntityTypes.ETHEREAL_WISP.get(), Wisp.createAttributes().build());
        event.put(GenesisEntityTypes.SHADE_OF_ARKENZUS.get(), ShadeOfArkenzus.createAttributes().build());
        event.put(GenesisEntityTypes.FROSTPINE_TOTEM.get(), FrostpineTotem.createAttributes().build());
        event.put(GenesisEntityTypes.BABY_PINK_SWET.get(), BabyPinkSwet.createAttributes().build());
    }
}
