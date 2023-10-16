package com.aetherteam.aether_genesis.mixin;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.world.structurepiece.AetherStructurePieceTypes;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.ArrayList;
import java.util.List;

public class GenesisMixinHooks {
    public static List<StructurePieceType> getValidPieceTypes() {
        List<StructurePieceType> types = new ArrayList<>();
        types.add(AetherStructurePieceTypes.SILVER_DUNGEON_ROOM.get());
        types.add(AetherStructurePieceTypes.BRONZE_DUNGEON_ROOM.get());
        return types;
    }

    public static BlockState replaceChest(BlockState state, StructurePieceType structurePieceType) {
        if (getValidPieceTypes().contains(structurePieceType)) {
            if (state.is(Blocks.CHEST)) {
                return GenesisBlocks.SKYROOT_CHEST.get().withPropertiesOf(state);
            } else if (state.is(AetherBlocks.CHEST_MIMIC.get())) {
                return GenesisBlocks.SKYROOT_CHEST_MIMIC.get().withPropertiesOf(state);
            }
        }
        return state;
    }
}
