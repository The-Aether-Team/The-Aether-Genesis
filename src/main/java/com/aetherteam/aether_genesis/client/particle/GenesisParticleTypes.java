package com.aetherteam.aether_genesis.client.particle;

import com.aetherteam.aether_genesis.Genesis;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod.EventBusSubscriber(modid = Genesis.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Genesis.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PURPLE_CRYSTAL_LEAVES = PARTICLES.register("purple_crystal_leaves", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TEMPEST_ELECTRICITY = PARTICLES.register("tempest_electricity", () -> new SimpleParticleType(false));

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(PURPLE_CRYSTAL_LEAVES.get(), PurpleCrystalLeavesParticle.Factory::new);
        event.registerSpriteSet(TEMPEST_ELECTRICITY.get(), TempestElectricityParticle.Factory::new);
    }
}
