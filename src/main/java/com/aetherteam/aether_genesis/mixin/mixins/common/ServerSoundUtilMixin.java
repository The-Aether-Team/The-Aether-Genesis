package com.aetherteam.aether_genesis.mixin.mixins.common;

import com.aetherteam.aether.util.ServerSoundUtil;
import com.aetherteam.aether_genesis.GenesisConfig;
import com.aetherteam.aether_genesis.client.GenesisSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerSoundUtil.class)
public class ServerSoundUtilMixin {

    @Redirect(remap = false, method = "playPortalSound", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/registries/RegistryObject;get()Ljava/lang/Object;"))
    private static Object replacePortalTravelSound(RegistryObject instance)
    {
        return GenesisConfig.COMMON.aether_ii_portal_sounds.get() ? GenesisSoundEvents.PORTAL_TRAVEL.get() : instance.get();
    }

}
