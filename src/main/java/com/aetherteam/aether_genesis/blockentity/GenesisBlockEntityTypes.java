package com.aetherteam.aether_genesis.blockentity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GenesisBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Genesis.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HolystoneFurnaceBlockEntity>> HOLYSTONE_FURNACE = BLOCK_ENTITY_TYPES.register("holystone_furnace", () ->
            BlockEntityType.Builder.of(HolystoneFurnaceBlockEntity::new, GenesisBlocks.HOLYSTONE_FURNACE.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SkyrootChestBlockEntity>> SKYROOT_CHEST = BLOCK_ENTITY_TYPES.register("skyroot_chest", () ->
            BlockEntityType.Builder.of(SkyrootChestBlockEntity::new, GenesisBlocks.SKYROOT_CHEST.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SkyrootChestMimicBlockEntity>> SKYROOT_CHEST_MIMIC = BLOCK_ENTITY_TYPES.register("skyroot_chest_mimic", () ->
            BlockEntityType.Builder.of(SkyrootChestMimicBlockEntity::new, GenesisBlocks.SKYROOT_CHEST_MIMIC.get()).build(null));
}
