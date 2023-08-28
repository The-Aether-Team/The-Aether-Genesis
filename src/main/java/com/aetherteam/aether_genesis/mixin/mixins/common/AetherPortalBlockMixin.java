package com.aetherteam.aether_genesis.mixin.mixins.common;

import com.aetherteam.aether.block.portal.AetherPortalBlock;
import com.aetherteam.aether_genesis.GenesisConfig;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AetherPortalBlock.class)
public class AetherPortalBlockMixin {

    @Redirect(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/registries/RegistryObject;get()Ljava/lang/Object;"))
    private Object replacePortalTravelSound(RegistryObject instance)
    {
        if (instance.get() instanceof SoundEvent) {
            return GenesisConfig.COMMON.aether_ii_portal_sounds.get() ? GenesisSoundEvents.PORTAL_HUM.get() : instance.get();
        }
        return instance.get();
    }

    // Makes the Aether Portal ambient sound slightly less common, to account for the longer portal sound
    @Redirect(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"))
    private int changeSoundChance(RandomSource instance, int i)
    {
        if (i == 100 && GenesisConfig.COMMON.aether_ii_portal_sounds.get()) {
            return instance.nextInt(175);
        }
        return instance.nextInt(i);
    }

}
