package com.aetherteam.genesis.mixin;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.world.structurepiece.AetherStructurePieceTypes;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.world.structurepiece.GenesisStructurePieceTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.ArrayList;
import java.util.List;

public class GenesisMixinHooks {
    /**
     * Changes chests or chest mimics to their skyroot counterparts.
     * @param state The chest's {@link BlockState}.
     * @param structurePieceType The {@link StructurePieceType} that the chest is in.
     * @return The new chest {@link BlockState}.
     */
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

    /**
     * @return The {@link StructurePieceType}s that can have their chests replaced.
     */
    public static List<StructurePieceType> getValidPieceTypes() {
        List<StructurePieceType> types = new ArrayList<>();
        types.add(AetherStructurePieceTypes.SILVER_DUNGEON_ROOM.get());
        types.add(AetherStructurePieceTypes.BRONZE_DUNGEON_ROOM.get());
        types.add(GenesisStructurePieceTypes.BRONZE_DUNGEON_ROOM.get());
        return types;
    }
}
