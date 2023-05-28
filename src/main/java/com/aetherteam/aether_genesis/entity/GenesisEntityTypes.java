package com.aetherteam.aether_genesis.entity;

import com.aetherteam.aether.data.resources.AetherMobCategory;
import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.entity.miscellaneous.BlueParachute;
import com.aetherteam.aether_genesis.entity.miscellaneous.GreenParachute;
import com.aetherteam.aether_genesis.entity.miscellaneous.PurpleParachute;
import com.aetherteam.aether_genesis.entity.miscellaneous.TempestThunderBall;
import com.aetherteam.aether_genesis.entity.monster.Tempest;
import com.aetherteam.aether_genesis.entity.passive.CarrionSprout;
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
            () -> EntityType.Builder.of(CarrionSprout::new, MobCategory.CREATURE).sized(1.5F, 1.5F).clientTrackingRange(8).build("carrion_sprout"));

    // Hostile Mobs
    public static final RegistryObject<EntityType<Swet>> DARK_SWET = ENTITY_TYPES.register("dark_swet",
            () -> EntityType.Builder.of(Swet::new, AetherMobCategory.AETHER_SURFACE_MONSTER).sized(0.9F, 0.95F).clientTrackingRange(10).build("dark_swet"));
    public static final RegistryObject<EntityType<Tempest>> TEMPEST = ENTITY_TYPES.register("tempest",
            () -> EntityType.Builder.of(Tempest::new, AetherMobCategory.AETHER_SKY_MONSTER).sized(4.5F, 3.5F).fireImmune().clientTrackingRange(10).build("tempest"));

    // Miscellaneous
    public static final RegistryObject<EntityType<BlueParachute>> BLUE_PARACHUTE = ENTITY_TYPES.register("blue_parachute",
            () -> EntityType.Builder.of(BlueParachute::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(8).build("blue_parachute"));
    public static final RegistryObject<EntityType<GreenParachute>> GREEN_PARACHUTE = ENTITY_TYPES.register("green_parachute",
            () -> EntityType.Builder.of(GreenParachute::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(8).build("green_parachute"));
    public static final RegistryObject<EntityType<PurpleParachute>> PURPLE_PARACHUTE = ENTITY_TYPES.register("purple_parachute",
            () -> EntityType.Builder.of(PurpleParachute::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(8).build("purple_parachute"));

    //Projectile
    public static final RegistryObject<EntityType<TempestThunderBall>> TEMPEST_THUNDERBALL = ENTITY_TYPES.register("tempest_thunderball",
            () -> EntityType.Builder.<TempestThunderBall>of(TempestThunderBall::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(10).build("tempest_thunderball"));
    public static final RegistryObject<EntityType<PhoenixDart>> PHOENIX_DART = ENTITY_TYPES.register("phoenix_dart",
            () -> EntityType.Builder.<PhoenixDart>of(PhoenixDart::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("phoenix_dart"));

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
    }
}
