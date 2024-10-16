package com.aetherteam.genesis.client.particle;

import com.aetherteam.genesis.AetherGenesis;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod.EventBusSubscriber(modid = AetherGenesis.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GenesisParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, AetherGenesis.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PURPLE_CRYSTAL_LEAVES = PARTICLES.register("purple_crystal_leaves", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TEMPEST_ELECTRICITY = PARTICLES.register("tempest_electricity", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TRACKING_GOLEM_WARNING = PARTICLES.register("tracking_golem_warning", () -> new SimpleParticleType(true));

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(PURPLE_CRYSTAL_LEAVES.get(), PurpleCrystalLeavesParticle.Factory::new);
        event.registerSpriteSet(TEMPEST_ELECTRICITY.get(), TempestElectricityParticle.Factory::new);
        event.registerSpecial(TRACKING_GOLEM_WARNING.get(), (pType, pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed) -> new TrackingGolemWarningParticle.Provider().createParticle(pType, pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed));
    }
}
