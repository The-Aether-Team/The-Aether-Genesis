package com.aetherteam.genesis.client.particle;

import com.aetherteam.genesis.AetherGenesis;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, AetherGenesis.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PURPLE_CRYSTAL_LEAVES = PARTICLES.register("purple_crystal_leaves", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TEMPEST_ELECTRICITY = PARTICLES.register("tempest_electricity", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TRACKING_GOLEM_WARNING = PARTICLES.register("tracking_golem_warning", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> NEX_SPIRIT_RESURRECTION = PARTICLES.register("nex_spirit_resurrection", () -> new SimpleParticleType(false));

    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(PURPLE_CRYSTAL_LEAVES.get(), PurpleCrystalLeavesParticle.Factory::new);
        event.registerSpriteSet(TEMPEST_ELECTRICITY.get(), TempestElectricityParticle.Factory::new);
        event.registerSpecial(TRACKING_GOLEM_WARNING.get(), (type, level, x, y, z, xSpeed, ySpeed, zSpeed) -> new TrackingGolemWarningParticle.Provider().createParticle(type, level, x, y, z, xSpeed, ySpeed, zSpeed));
        event.registerSpriteSet(NEX_SPIRIT_RESURRECTION.get(), NexSpiritResurrectionParticle.Factory::new);
    }
}
