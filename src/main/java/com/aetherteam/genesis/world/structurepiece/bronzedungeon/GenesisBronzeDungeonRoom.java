package com.aetherteam.genesis.world.structurepiece.bronzedungeon;

import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.loot.AetherLoot;
import com.aetherteam.aether.world.structurepiece.AetherTemplateStructurePiece;
import com.aetherteam.aether.world.structurepiece.bronzedungeon.BronzeDungeonPiece;
import com.aetherteam.aether.world.structurepiece.bronzedungeon.BronzeDungeonRoom;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.entity.GenesisEntityTypes;
import com.aetherteam.genesis.world.structurepiece.GenesisStructurePieceTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.ArrayList;
import java.util.List;

public class GenesisBronzeDungeonRoom extends BronzeDungeonPiece {
    public GenesisBronzeDungeonRoom(StructureTemplateManager manager, String name, BlockPos pos, Rotation rotation, Holder<StructureProcessorList> processors) {
        super(GenesisStructurePieceTypes.BRONZE_DUNGEON_ROOM.get(), manager, new ResourceLocation(AetherGenesis.MODID, "bronze_dungeon/" + name), new StructurePlaceSettings().setRotation(rotation), pos, processors);
    }

    public GenesisBronzeDungeonRoom(StructurePieceSerializationContext context, CompoundTag tag) {
        super(GenesisStructurePieceTypes.BRONZE_DUNGEON_ROOM.get(), context.registryAccess(), tag, context.structureTemplateManager(), (resourceLocation) -> new StructurePlaceSettings());
    }

    protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        if (name.equals("Chest")) {
            BlockState state = Blocks.CHEST.defaultBlockState();
            this.createChest(level, box, random, pos, AetherLoot.BRONZE_DUNGEON, state);
        } else if (name.equals("Spawner")) {
            BlockState state = Blocks.SPAWNER.defaultBlockState();
            if (box.isInside(pos) && !level.getBlockState(pos).is(Blocks.SPAWNER)) {
                level.setBlock(pos, state, 2);
                BlockEntity blockentity = level.getBlockEntity(pos);
                if (blockentity instanceof SpawnerBlockEntity spawnerBlockEntity) {
                    List<EntityType<?>> mobs = List.of(GenesisEntityTypes.TRACKING_GOLEM.get(), GenesisEntityTypes.BATTLE_SENTRY.get(), AetherEntityTypes.SENTRY.get()); //todo sentry golem
                    EntityType<?> mob = mobs.get(random.nextInt(mobs.size()));
                    spawnerBlockEntity.setEntityId(mob, random);
                }
            }
        }
    }
}
