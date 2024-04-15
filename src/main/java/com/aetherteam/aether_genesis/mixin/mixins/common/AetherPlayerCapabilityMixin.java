package com.aetherteam.aether_genesis.mixin.mixins.common;

import com.aetherteam.aether.attachment.AetherPlayerAttachment;
import com.aetherteam.aether_genesis.GenesisConfig;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AetherPlayerAttachment.class)
public class AetherPlayerCapabilityMixin {
    @Redirect(remap = false, method = "playPortalSound", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/registries/DeferredHolder;get()Ljava/lang/Object;"))
    private Object replacePortalTravelSound(DeferredHolder<?, ?> instance) {
        return GenesisConfig.COMMON.aether_ii_portal_sounds.get() ? GenesisSoundEvents.PORTAL_TRIGGER.get() : instance.get();
    }
}
