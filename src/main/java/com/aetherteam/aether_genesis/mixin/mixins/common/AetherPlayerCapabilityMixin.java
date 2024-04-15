package com.aetherteam.aether_genesis.mixin.mixins.common;

import com.aetherteam.aether.capability.player.AetherPlayerCapability;
import com.aetherteam.aether_genesis.GenesisConfig;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.neoforged.neoforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AetherPlayerCapability.class)
public class AetherPlayerCapabilityMixin {
    @Redirect(remap = false, method = "playPortalSound", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/registries/RegistryObject;get()Ljava/lang/Object;"))
    private Object replacePortalTravelSound(RegistryObject<?> instance) {
        return GenesisConfig.COMMON.aether_ii_portal_sounds.get() ? GenesisSoundEvents.PORTAL_TRIGGER.get() : instance.get();
    }
}
