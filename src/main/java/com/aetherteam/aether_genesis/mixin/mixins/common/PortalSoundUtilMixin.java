package com.aetherteam.aether_genesis.mixin.mixins.common;

import com.aetherteam.aether.block.portal.PortalSoundUtil;
import com.aetherteam.aether_genesis.GenesisConfig;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PortalSoundUtil.class)
public class PortalSoundUtilMixin { //todo: replace with a config-enabled resource pack that overrides the sounds.json
    /**
     * Changes the Aether Portal's sound.
     */
    @Redirect(remap = false, method = "playPortalSound(Lnet/minecraft/client/player/LocalPlayer;)V", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/registries/DeferredHolder;get()Ljava/lang/Object;"))
    private static Object playPortalSound(DeferredHolder<?, ?> instance) {
        return GenesisConfig.COMMON.aether_ii_portal_sounds.get() ? GenesisSoundEvents.BLOCK_AETHER_PORTAL_TRAVEL.get() : instance.get();
    }
}
