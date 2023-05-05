package com.aetherteam.aether_genesis.entity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.entity.miscellaneous.BlueParachute;
import com.aetherteam.aether_genesis.entity.miscellaneous.GreenParachute;
import com.aetherteam.aether_genesis.entity.miscellaneous.PurpleParachute;
import com.aetherteam.aether.entity.monster.Swet;
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

    // Hostile Mobs
    public static final RegistryObject<EntityType<Swet>> DARK_SWET = ENTITY_TYPES.register("dark_swet",
            () -> EntityType.Builder.of(Swet::new, MobCategory.MONSTER).sized(0.9F, 0.95F).clientTrackingRange(10).build("dark_swet"));

    // Miscellaneous
    public static final RegistryObject<EntityType<BlueParachute>> BLUE_PARACHUTE = ENTITY_TYPES.register("blue_parachute",
            () -> EntityType.Builder.of(BlueParachute::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(8).build("blue_parachute"));
    public static final RegistryObject<EntityType<GreenParachute>> GREEN_PARACHUTE = ENTITY_TYPES.register("green_parachute",
            () -> EntityType.Builder.of(GreenParachute::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(8).build("green_parachute"));
    public static final RegistryObject<EntityType<PurpleParachute>> PURPLE_PARACHUTE = ENTITY_TYPES.register("purple_parachute",
            () -> EntityType.Builder.of(PurpleParachute::new, MobCategory.MISC).sized(1.0F, 1.0F).clientTrackingRange(8).build("purple_parachute"));

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(GenesisEntityTypes.DARK_SWET.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Swet::checkSwetSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(GenesisEntityTypes.DARK_SWET.get(), Swet.createMobAttributes().build());
    }
}
