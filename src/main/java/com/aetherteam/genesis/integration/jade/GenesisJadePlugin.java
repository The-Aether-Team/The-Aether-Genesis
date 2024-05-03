package com.aetherteam.genesis.integration.jade;

import com.aetherteam.genesis.block.GenesisBlocks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;
import snownee.jade.addon.vanilla.VanillaPlugin;
import snownee.jade.api.*;

@WailaPlugin
public class GenesisJadePlugin implements IWailaPlugin {
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.addRayTraceCallback(this::registerGenesisOverrides);
    }

    @Nullable
    public Accessor<?> registerGenesisOverrides(HitResult hitResult, @Nullable Accessor<?> accessor, @Nullable Accessor<?> originalAccessor) {
        if (accessor instanceof BlockAccessor target) {
            Player player = accessor.getPlayer();
            if (player.isCreative() || player.isSpectator()) {
                return accessor;
            }
            IWailaClientRegistration client = VanillaPlugin.CLIENT_REGISTRATION;
            if (target.getBlock() == GenesisBlocks.SKYROOT_CHEST_MIMIC.get()) { // Mimics show up as normal chests. There's not a single way to tell the difference between these and normal chests from the tooltip.
                return client.blockAccessor().from(target).serverData(this.createFakeChestData(target)).blockState(Blocks.CHEST.defaultBlockState()).build();
            }
        }
        return accessor;
    }

    /**
     * Adds the "inventory not generated" text to the mimic's tooltip
     */
    private CompoundTag createFakeChestData(BlockAccessor target) {
        CompoundTag tag = new CompoundTag();
        if (!target.getServerData().isEmpty()) {
            tag.putBoolean("Loot", true);
        }
        return tag;
    }
}
