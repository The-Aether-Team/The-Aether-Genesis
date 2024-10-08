package com.aetherteam.genesis.world.structurepiece.bronzedungeon;

import com.aetherteam.aether.loot.AetherLoot;
import com.aetherteam.aether.world.structurepiece.AetherTemplateStructurePiece;
import com.aetherteam.aether.world.structurepiece.bronzedungeon.BronzeDungeonPiece;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.world.structurepiece.GenesisStructurePieceTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class GenesisBronzeBossRoom extends BronzeDungeonPiece {

    public GenesisBronzeBossRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation, Holder<StructureProcessorList> processors) {
        super(GenesisStructurePieceTypes.BRONZE_BOSS_ROOM.get(), manager, new ResourceLocation(AetherGenesis.MODID, "bronze_dungeon/" + name), AetherTemplateStructurePiece.makeSettingsWithPivot(makeSettings(), manager, BronzeDungeonPiece.makeLocation(name), rotation), pos, processors);
    }

    public GenesisBronzeBossRoom(StructurePieceSerializationContext context, CompoundTag tag) {
        super(GenesisStructurePieceTypes.BRONZE_BOSS_ROOM.get(), context.registryAccess(), tag, context.structureTemplateManager(), (resourceLocation) -> makeSettings());
    }

    static StructurePlaceSettings makeSettings() {
        return (new StructurePlaceSettings()).setFinalizeEntities(true);
    }

    protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
        if (name.equals("Treasure Chest")) {
            BlockPos chest = pos.below();
            BlockEntity entity = level.getBlockEntity(chest);
            if (entity instanceof RandomizableContainerBlockEntity container) {
                container.setLootTable(AetherLoot.BRONZE_DUNGEON_REWARD, random.nextLong());
            }
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        }
    }
}