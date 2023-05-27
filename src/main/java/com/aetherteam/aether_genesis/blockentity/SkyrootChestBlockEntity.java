package com.aetherteam.aether_genesis.blockentity;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.blockentity.AetherBlockEntityTypes;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SkyrootChestBlockEntity extends ChestBlockEntity {
    protected SkyrootChestBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public SkyrootChestBlockEntity(BlockPos pPos, BlockState pBlockState) {
        this(GenesisBlockEntityTypes.SKYROOT_CHEST.get(), pPos, pBlockState);
    }

    public SkyrootChestBlockEntity() {
        this(GenesisBlockEntityTypes.SKYROOT_CHEST.get(), BlockPos.ZERO, GenesisBlocks.SKYROOT_CHEST.get().defaultBlockState());
    }
}
