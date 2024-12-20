package com.aetherteam.genesis.mixin.mixins.client;

import com.aetherteam.aether.block.portal.PortalClientUtil;
import com.aetherteam.genesis.GenesisConfig;
import com.aetherteam.genesis.client.GenesisSoundEvents;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PortalClientUtil.class)
public class PortalClientUtilMixin { //todo: replace with a config-enabled resource pack that overrides the sounds.json
    /**
     * Changes the Aether Portal's sound.
     */
    @Redirect(remap = false, method = "playTriggerSound", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/registries/DeferredHolder;get()Ljava/lang/Object;"))
    private static Object playTriggerPortalSound(DeferredHolder<?, ?> instance) {
        return GenesisConfig.COMMON.aether_ii_portal_sounds.get() ? GenesisSoundEvents.BLOCK_AETHER_PORTAL_TRIGGER.get() : instance.get();
    }

    @Redirect(remap = false, method = "playTravelSound", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/registries/DeferredHolder;get()Ljava/lang/Object;"))
    private static Object playTravelPortalSound(DeferredHolder<?, ?> instance) {
        return GenesisConfig.COMMON.aether_ii_portal_sounds.get() ? GenesisSoundEvents.BLOCK_AETHER_PORTAL_TRAVEL.get() : instance.get();
    }
}
