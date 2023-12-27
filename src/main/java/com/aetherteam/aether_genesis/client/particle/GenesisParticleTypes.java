package com.aetherteam.aether_genesis.client.particle;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Genesis.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Genesis.MODID);

    public static final RegistryObject<SimpleParticleType> PURPLE_CRYSTAL_LEAVES = PARTICLES.register("purple_crystal_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> TEMPEST_ELECTRICITY = PARTICLES.register("tempest_electricity", () -> new SimpleParticleType(false));

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(PURPLE_CRYSTAL_LEAVES.get(), PurpleCrystalLeavesParticle.Factory::new);
        event.registerSpriteSet(TEMPEST_ELECTRICITY.get(), TempestElectricityParticle.Factory::new);
    }
}
