package com.aetherteam.genesis.mixin.mixins.common;

import com.aetherteam.aether.attachment.AetherPlayerAttachment;
import com.aetherteam.genesis.GenesisConfig;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AetherPlayerAttachment.class)
public class AetherPlayerCapabilityMixin { //todo: replace with a config-enabled resource pack that overrides the sounds.json
    /**
     * Changes the Aether Portal's sound.
     */
    @Redirect(remap = false, method = "playPortalSound(Lnet/minecraft/client/Minecraft;Lnet/minecraft/world/entity/player/Player;)V", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/registries/DeferredHolder;get()Ljava/lang/Object;"))
    private Object playPortalSound(DeferredHolder<?, ?> instance) {
        return GenesisConfig.COMMON.aether_ii_portal_sounds.get() ? GenesisSoundEvents.BLOCK_AETHER_PORTAL_TRIGGER.get() : instance.get();
    }
}
