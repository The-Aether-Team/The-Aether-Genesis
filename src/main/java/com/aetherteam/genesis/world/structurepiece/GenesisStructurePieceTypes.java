package com.aetherteam.genesis.world.structurepiece;

import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.world.structurepiece.bronzedungeon.GenesisBronzeBossRoom;
import com.aetherteam.genesis.world.structurepiece.bronzedungeon.GenesisBronzeDungeonRoom;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Locale;

public class GenesisStructurePieceTypes {
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registries.STRUCTURE_PIECE, AetherGenesis.MODID);

    public static final DeferredHolder<StructurePieceType, StructurePieceType> BRONZE_BOSS_ROOM = register("BBossRoom", GenesisBronzeBossRoom::new);
    public static final DeferredHolder<StructurePieceType, StructurePieceType> BRONZE_DUNGEON_ROOM = register("BDungeonRoom", GenesisBronzeDungeonRoom::new);

    private static DeferredHolder<StructurePieceType, StructurePieceType> register(String name, StructurePieceType structurePieceType) {
        return STRUCTURE_PIECE_TYPES.register(name.toLowerCase(Locale.ROOT), () -> structurePieceType);
    }
}