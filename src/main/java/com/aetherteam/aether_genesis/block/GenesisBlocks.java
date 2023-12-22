package com.aetherteam.aether_genesis.block;

import com.aetherteam.aether.block.dungeon.DoorwayBlock;
import com.aetherteam.aether.block.dungeon.TrappedBlock;
import com.aetherteam.aether.block.dungeon.TreasureDoorwayBlock;
import com.aetherteam.aether.block.miscellaneous.FacingPillarBlock;
import com.aetherteam.aether.block.natural.AercloudBlock;
import com.aetherteam.aether.block.natural.AetherDoubleDropsLeaves;
import com.aetherteam.aether.block.natural.LeavesWithParticlesBlock;
import com.aetherteam.aether.entity.AetherEntityTypes;
import com.aetherteam.aether.mixin.mixins.common.accessor.FireBlockAccessor;
import com.aetherteam.aether_genesis.Genesis;
import com.aetherteam.aether_genesis.block.utility.HolystoneFurnaceBlock;
import com.aetherteam.aether_genesis.block.utility.SkyrootChestBlock;
import com.aetherteam.aether_genesis.block.dungeon.SkyrootChestMimicBlock;
import com.aetherteam.aether_genesis.block.utility.SkyrootCraftingTableBlock;
import com.aetherteam.aether_genesis.block.miscellaneous.ColdFireBlock;
import com.aetherteam.aether_genesis.block.natural.*;
import com.aetherteam.aether_genesis.blockentity.GenesisBlockEntityTypes;
import com.aetherteam.aether_genesis.blockentity.SkyrootChestBlockEntity;
import com.aetherteam.aether_genesis.blockentity.SkyrootChestMimicBlockEntity;
import com.aetherteam.aether_genesis.client.particle.GenesisParticleTypes;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.aetherteam.aether_genesis.item.block.WoodenBlockItem;
import com.aetherteam.aether_genesis.world.treegrower.BlueSkyrootTree;
import com.aetherteam.aether_genesis.world.treegrower.DarkBlueSkyrootTree;
import com.aetherteam.aether_genesis.world.treegrower.PurpleCrystalTree;
import com.aetherteam.nitrogen.item.block.EntityBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class GenesisBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Genesis.MODID);

    public static final RegistryObject<Block> ENCHANTED_GRASS_BLOCK = register("enchanted_grass_block", () -> new EnchantedGrassBlock(Block.Properties.of().mapColor(MapColor.GOLD).randomTicks().strength(0.2F).sound(SoundType.GRASS)));

    public static final RegistryObject<Block> GREEN_AERCLOUD = register("green_aercloud", () -> new GreenAercloudBlock(Block.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).instrument(NoteBlockInstrument.FLUTE).strength(0.3F).sound(SoundType.WOOL).noOcclusion().dynamicShape().isRedstoneConductor(GenesisBlocks::never).isSuffocating(GenesisBlocks::never).isViewBlocking(GenesisBlocks::never)));
    public static final RegistryObject<Block> PURPLE_AERCLOUD = register("purple_aercloud", () -> new PurpleAercloudBlock(Block.Properties.of().mapColor(MapColor.COLOR_MAGENTA).instrument(NoteBlockInstrument.FLUTE).strength(0.3F).sound(SoundType.WOOL).noOcclusion().dynamicShape().isRedstoneConductor(GenesisBlocks::never).isSuffocating(GenesisBlocks::never).isViewBlocking(GenesisBlocks::never)));
    public static final RegistryObject<Block> STORM_AERCLOUD = register("storm_aercloud", () -> new AercloudBlock(Block.Properties.of().mapColor(MapColor.DEEPSLATE).instrument(NoteBlockInstrument.FLUTE).strength(0.3F).sound(SoundType.WOOL).noOcclusion().dynamicShape().isRedstoneConductor(GenesisBlocks::never).isSuffocating(GenesisBlocks::never).isViewBlocking(GenesisBlocks::never)));

    public static final RegistryObject<Block> CONTINUUM_ORE = register("continuum_ore", () -> new DropExperienceBlock(Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F).requiresCorrectToolForDrops(), UniformInt.of(3, 5)));

    public static final RegistryObject<Block> BLUE_SKYROOT_LEAVES = register("blue_skyroot_leaves", () -> new AetherDoubleDropsLeaves(Block.Properties.of().mapColor(MapColor.DIAMOND).ignitedByLava().pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(GenesisBlocks::ocelotOrParrot).isSuffocating(GenesisBlocks::never).isViewBlocking(GenesisBlocks::never)));
    public static final RegistryObject<Block> DARK_BLUE_SKYROOT_LEAVES = register("dark_blue_skyroot_leaves", () -> new AetherDoubleDropsLeaves(Block.Properties.of().mapColor(MapColor.LAPIS).ignitedByLava().pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(GenesisBlocks::ocelotOrParrot).isSuffocating(GenesisBlocks::never).isViewBlocking(GenesisBlocks::never)));
    public static final RegistryObject<Block> PURPLE_CRYSTAL_LEAVES = register("purple_crystal_leaves", () -> new LeavesWithParticlesBlock(GenesisParticleTypes.PURPLE_CRYSTAL_LEAVES, Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).ignitedByLava().pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(GenesisBlocks::ocelotOrParrot).isSuffocating(GenesisBlocks::never).isViewBlocking(GenesisBlocks::never)));
    public static final RegistryObject<Block> PURPLE_CRYSTAL_FRUIT_LEAVES = register("purple_crystal_fruit_leaves", () -> new LeavesWithParticlesBlock(GenesisParticleTypes.PURPLE_CRYSTAL_LEAVES, Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).ignitedByLava().pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(GenesisBlocks::ocelotOrParrot).isSuffocating(GenesisBlocks::never).isViewBlocking(GenesisBlocks::never)));

    public static final RegistryObject<Block> ORANGE_TREE = register("orange_tree", () -> new OrangeTreeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).pushReaction(PushReaction.DESTROY).noCollission().strength(0.2F).sound(SoundType.GRASS)));
    public static final RegistryObject<FlowerPotBlock> POTTED_ORANGE_TREE = BLOCKS.register("potted_orange_tree", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ORANGE_TREE, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final RegistryObject<FacingPillarBlock> HOLYSTONE_HEADSTONE = register("holystone_headstone", () -> new FacingPillarBlock(Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(2.0F, 10.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<FacingPillarBlock> HOLYSTONE_KEYSTONE = register("holystone_keystone", () -> new FacingPillarBlock(Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(2.0F, 10.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<FacingPillarBlock> HOLYSTONE_HIGHLIGHT = register("holystone_highlight", () -> new FacingPillarBlock(Block.Properties.of().mapColor(MapColor.WOOL).instrument(NoteBlockInstrument.BASEDRUM).strength(2.0F, 10.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<SaplingBlock> BLUE_SKYROOT_SAPLING = register("blue_skyroot_sapling", () -> new SaplingBlock(new BlueSkyrootTree(), Block.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<SaplingBlock> DARK_BLUE_SKYROOT_SAPLING = register("dark_blue_skyroot_sapling", () -> new SaplingBlock(new DarkBlueSkyrootTree(), Block.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<SaplingBlock> PURPLE_CRYSTAL_TREE_SAPLING = register("purple_crystal_tree_sapling", () -> new SaplingBlock(new PurpleCrystalTree(), Block.Properties.copy(Blocks.OAK_SAPLING)));
    public static final RegistryObject<FlowerPotBlock> POTTED_BLUE_SKYROOT_SAPLING = BLOCKS.register("potted_blue_skyroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_SKYROOT_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<FlowerPotBlock> POTTED_DARK_BLUE_SKYROOT_SAPLING = BLOCKS.register("potted_dark_blue_skyroot_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, DARK_BLUE_SKYROOT_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<FlowerPotBlock> POTTED_PURPLE_CRYSTAL_TREE_SAPLING = BLOCKS.register("potted_purple_crystal_tree_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PURPLE_CRYSTAL_TREE_SAPLING, Block.Properties.copy(Blocks.FLOWER_POT)));

    public static final RegistryObject<WallBlock> SKYROOT_LOG_WALL = register("skyroot_log_wall", () -> new GenesisDoubleDropsWall(Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).ignitedByLava().strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<WallBlock> STRIPPED_SKYROOT_LOG_WALL = register("stripped_skyroot_log_wall", () -> new WallBlock(Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).ignitedByLava().strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<WallBlock> SKYROOT_WOOD_WALL = register("skyroot_wood_wall", () -> new GenesisDoubleDropsWall(Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).ignitedByLava().strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<WallBlock> STRIPPED_SKYROOT_WOOD_WALL = register("stripped_skyroot_wood_wall", () -> new WallBlock(Block.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).ignitedByLava().strength(2.0F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SKYROOT_CRAFTING_TABLE = register("skyroot_crafting_table", () -> new SkyrootCraftingTableBlock(Block.Properties.copy(Blocks.CRAFTING_TABLE)));
    public static final RegistryObject<Block> HOLYSTONE_FURNACE = register("holystone_furnace", () -> new HolystoneFurnaceBlock(Block.Properties.copy(Blocks.FURNACE)));
    public static final RegistryObject<Block> SKYROOT_CHEST = register("skyroot_chest", () -> new SkyrootChestBlock(Block.Properties.copy(Blocks.CHEST), GenesisBlockEntityTypes.SKYROOT_CHEST::get));
    public static final RegistryObject<LadderBlock> SKYROOT_LADDER = register("skyroot_ladder", () -> new LadderBlock(BlockBehaviour.Properties.copy(Blocks.LADDER).strength(0.4F).sound(SoundType.LADDER).noOcclusion()));

    public static final RegistryObject<FacingPillarBlock> CARVED_PILLAR = register("carved_pillar", () -> new FacingPillarBlock(Block.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<RotatedPillarBlock> CARVED_PILLAR_SIDE = register("carved_pillar_side", () -> new RotatedPillarBlock(Block.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 6.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SKYROOT_CHEST_MIMIC = register("skyroot_chest_mimic", () -> new SkyrootChestMimicBlock(Block.Properties.copy(SKYROOT_CHEST.get()).noLootTable()));

    public static final RegistryObject<Block> DIVINE_CARVED_STONE = register("divine_carved_stone", () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).strength(0.5F, 6.0F).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DIVINE_SENTRY_STONE = register("divine_sentry_stone", () -> new Block(Block.Properties.copy(DIVINE_CARVED_STONE.get()).lightLevel(GenesisBlocks::lightLevel11)));

    public static final RegistryObject<Block> LOCKED_DIVINE_CARVED_STONE = register("locked_divine_carved_stone", () -> new Block(Block.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).strength(-1.0F, 3600000.0F)));
    public static final RegistryObject<Block> LOCKED_DIVINE_SENTRY_STONE = register("locked_divine_sentry_stone", () -> new Block(Block.Properties.copy(LOCKED_DIVINE_CARVED_STONE.get()).lightLevel(GenesisBlocks::lightLevel11)));

    public static final RegistryObject<Block> TRAPPED_DIVINE_CARVED_STONE = register("trapped_divine_carved_stone", () -> new TrappedBlock(AetherEntityTypes.SENTRY::get, () -> DIVINE_CARVED_STONE.get().defaultBlockState(), Block.Properties.copy(DIVINE_CARVED_STONE.get())));
    public static final RegistryObject<Block> TRAPPED_DIVINE_SENTRY_STONE = register("trapped_divine_sentry_stone", () -> new TrappedBlock(AetherEntityTypes.SENTRY::get, () -> DIVINE_SENTRY_STONE.get().defaultBlockState(), Block.Properties.copy(DIVINE_SENTRY_STONE.get())));

    public static final RegistryObject<Block> BOSS_DOORWAY_DIVINE_CARVED_STONE = register("boss_doorway_divine_carved_stone", () -> new DoorwayBlock(AetherEntityTypes.SLIDER::get, BlockBehaviour.Properties.copy(DIVINE_CARVED_STONE.get())));
    public static final RegistryObject<Block> BOSS_DOORWAY_DIVINE_SENTRY_STONE = register("boss_doorway_divine_sentry_stone", () -> new DoorwayBlock(AetherEntityTypes.SLIDER::get, BlockBehaviour.Properties.copy(DIVINE_SENTRY_STONE.get())));

    public static final RegistryObject<Block> TREASURE_DOORWAY_DIVINE_CARVED_STONE = register("treasure_doorway_divine_carved_stone", () -> new TreasureDoorwayBlock(BlockBehaviour.Properties.copy(DIVINE_CARVED_STONE.get())));
    public static final RegistryObject<Block> TREASURE_DOORWAY_DIVINE_SENTRY_STONE = register("treasure_doorway_divine_sentry_stone", () -> new TreasureDoorwayBlock(BlockBehaviour.Properties.copy(DIVINE_SENTRY_STONE.get())));

    public static final RegistryObject<WallBlock> DIVINE_CARVED_WALL = register("divine_carved_wall", () -> new WallBlock(Block.Properties.copy(DIVINE_CARVED_STONE.get())));

    public static final RegistryObject<StairBlock> DIVINE_CARVED_STAIRS = register("divine_carved_stairs",
            () -> new StairBlock(() -> DIVINE_CARVED_STONE.get().defaultBlockState(), Block.Properties.copy(DIVINE_CARVED_STONE.get())));

    public static final RegistryObject<SlabBlock> DIVINE_CARVED_SLAB = register("divine_carved_slab",
            () -> new SlabBlock(Block.Properties.copy(DIVINE_CARVED_STONE.get()).strength(0.5F, 6.0F)));

    public static final RegistryObject<Block> BLOOD_MOSS_HOLYSTONE = register("blood_moss_holystone", () -> new Block(Block.Properties.copy(Blocks.BEDROCK).noLootTable()));

    public static final RegistryObject<ColdFireBlock> COLD_FIRE = BLOCKS.register("cold_fire", () -> new ColdFireBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().instabreak().lightLevel((state) -> 10).sound(SoundType.WOOL)));

    public static void registerPots() {
        FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;
        pot.addPlant(GenesisBlocks.BLUE_SKYROOT_SAPLING.getId(), GenesisBlocks.POTTED_BLUE_SKYROOT_SAPLING);
        pot.addPlant(GenesisBlocks.DARK_BLUE_SKYROOT_SAPLING.getId(), GenesisBlocks.POTTED_DARK_BLUE_SKYROOT_SAPLING);
        pot.addPlant(GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.getId(), GenesisBlocks.POTTED_PURPLE_CRYSTAL_TREE_SAPLING);
        pot.addPlant(GenesisBlocks.ORANGE_TREE.getId(), GenesisBlocks.POTTED_ORANGE_TREE);
    }

    public static void registerFlammability() {
        FireBlockAccessor fireBlockAccessor = (FireBlockAccessor) Blocks.FIRE;
        fireBlockAccessor.callSetFlammable(GenesisBlocks.BLUE_SKYROOT_LEAVES.get(), 30, 60);
        fireBlockAccessor.callSetFlammable(GenesisBlocks.DARK_BLUE_SKYROOT_LEAVES.get(), 30, 60);
        fireBlockAccessor.callSetFlammable(GenesisBlocks.SKYROOT_LOG_WALL.get(), 5, 5);
        fireBlockAccessor.callSetFlammable(GenesisBlocks.STRIPPED_SKYROOT_LOG_WALL.get(), 5, 5);
        fireBlockAccessor.callSetFlammable(GenesisBlocks.SKYROOT_WOOD_WALL.get(), 5, 5);
        fireBlockAccessor.callSetFlammable(GenesisBlocks.STRIPPED_SKYROOT_WOOD_WALL.get(), 5, 5);
    }

    private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        GenesisItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    @SuppressWarnings("unchecked")
    private static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block) {
        return (RegistryObject<B>) baseRegister(name, block, GenesisBlocks::registerBlockItem);
    }

    private static <B extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<B> blockRegistryObject) {
        return () -> {
            B block = Objects.requireNonNull(blockRegistryObject.get());
            if (block == SKYROOT_CRAFTING_TABLE.get() || block == SKYROOT_LADDER.get()) {
                return new WoodenBlockItem(block, new Item.Properties());
            } else if (block == SKYROOT_CHEST.get()) {
                return new EntityBlockItem(block, SkyrootChestBlockEntity::new, new Item.Properties());
            }else if (block == SKYROOT_CHEST_MIMIC.get()) {
                return new EntityBlockItem(block, SkyrootChestMimicBlockEntity::new, new Item.Properties());
            } else {
                return new BlockItem(block, new Item.Properties());
            }
        };
    }

    private static boolean never(BlockState p_test_1_, BlockGetter p_test_2_, BlockPos p_test_3_) {
        return false;
    }

    private static boolean ocelotOrParrot(BlockState p_235441_0_, BlockGetter p_235441_1_, BlockPos p_235441_2_, EntityType<?> p_235441_3_) {
        return p_235441_3_ == EntityType.OCELOT || p_235441_3_ == EntityType.PARROT;
    }

    private static int lightLevel11(BlockState state) {
        return 11;
    }
}
