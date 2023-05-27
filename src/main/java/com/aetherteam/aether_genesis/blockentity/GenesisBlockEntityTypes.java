package com.aetherteam.aether_genesis.blockentity;

import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GenesisBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Genesis.MODID);

    public static final RegistryObject<BlockEntityType<HolystoneFurnaceBlockEntity>> HOLYSTONE_FURNACE = BLOCK_ENTITY_TYPES.register("holystone_furnace", () ->
            BlockEntityType.Builder.of(HolystoneFurnaceBlockEntity::new, GenesisBlocks.HOLYSTONE_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<SkyrootChestBlockEntity>> SKYROOT_CHEST = BLOCK_ENTITY_TYPES.register("skyroot_chest", () ->
            BlockEntityType.Builder.of(SkyrootChestBlockEntity::new, GenesisBlocks.SKYROOT_CHEST.get()).build(null));

}
