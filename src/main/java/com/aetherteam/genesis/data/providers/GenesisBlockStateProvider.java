package com.aetherteam.genesis.data.providers;

import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.miscellaneous.FacingPillarBlock;
import com.aetherteam.aether.data.providers.AetherBlockStateProvider;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.block.miscellaneous.ColdFireBlock;
import com.aetherteam.genesis.block.natural.OrangeTreeBlock;
import com.aetherteam.genesis.block.natural.PurpleAercloudBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public abstract class GenesisBlockStateProvider extends AetherBlockStateProvider {
    public GenesisBlockStateProvider(PackOutput output, String id, ExistingFileHelper helper) {
        super(output, id, helper);
    }

    public void purpleAercloud(Block block) {
        String blockName = this.name(block);
        ResourceLocation front = this.extend(this.texture(this.name(block), "natural/"), "_front");
        ResourceLocation back = this.extend(this.texture(this.name(block), "natural/"), "_back");
        ResourceLocation right = this.extend(this.texture(this.name(block), "natural/"), "_right");
        ResourceLocation left = this.extend(this.texture(this.name(block), "natural/"), "_left");
        ModelFile rightModel = this.models().cubeBottomTop(blockName, right, back, front).renderType(ResourceLocation.withDefaultNamespace("translucent"));
        ModelFile leftModel = this.models().cubeBottomTop(blockName, left, back, front).renderType(ResourceLocation.withDefaultNamespace("translucent"));
        this.getVariantBuilder(block).forAllStatesExcept((state) -> {
            Direction direction = state.getValue(PurpleAercloudBlock.FACING);
            switch(direction) {
                case NORTH -> {
                    return ConfiguredModel.builder().modelFile(leftModel).rotationX(90).build();
                }
                case SOUTH -> {
                    return ConfiguredModel.builder().modelFile(rightModel).rotationX(-90).build();
                }
                case WEST -> {
                    return ConfiguredModel.builder().modelFile(leftModel).rotationX(-90).rotationY(90).build();
                }
                case EAST -> {
                    return ConfiguredModel.builder().modelFile(rightModel).rotationX(90).rotationY(90).build();
                }
            }
            return ConfiguredModel.builder().build();
        }, AetherBlockStateProperties.DOUBLE_DROPS);
    }

    public void orangeTree(Block block) {
        String blockName = this.name(block);
        this.getVariantBuilder(block).forAllStatesExcept((state) -> {
            DoubleBlockHalf halfProperty = state.getValue(OrangeTreeBlock.HALF);
            int age = state.getValue(OrangeTreeBlock.AGE);
            boolean lower = halfProperty == DoubleBlockHalf.LOWER;
            int bottomAge = age == 3 ? 2 : age;
            int topAge = Math.max(age, 2);
            String halfString = lower ? "_bottom_" : "_top_";
            ResourceLocation location = lower ? this.extend(this.texture(blockName, "natural/"), halfString + bottomAge) : this.extend(this.texture(blockName, "natural/"), halfString + topAge);
            ModelFile model = this.models().cross(blockName + (lower ? (halfString + bottomAge) : (halfString + topAge)), location).renderType(ResourceLocation.withDefaultNamespace("cutout"));
            return ConfiguredModel.builder().modelFile(model).build();
        }, AetherBlockStateProperties.DOUBLE_DROPS);
    }

    public void pottedOrangeTree(Block block, Block tree) {
        ModelFile pot = this.models().withExistingParent(this.name(block), this.mcLoc("block/flower_pot_cross")).texture("plant", this.modLoc("block/natural/" + this.name(tree) + "_bottom_0")).renderType(ResourceLocation.withDefaultNamespace("cutout"));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }

    public void furnace(Block block) {
        String blockName = this.name(block);
        ResourceLocation side = this.extend(this.texture(this.name(block), "utility/"), "_side");
        ResourceLocation front_on =  this.extend(this.texture(this.name(block), "utility/"), "_front_on");
        ResourceLocation front =  this.extend(this.texture(this.name(block), "utility/"), "_front");
        ResourceLocation top = this.extend(this.texture(this.name(block), "utility/"), "_top");
        ModelFile normal = this.models().orientable(blockName, side, front, top);
        ModelFile lit = this.models().orientable(blockName + "_on", side, front_on, top);
        this.getVariantBuilder(block).forAllStatesExcept((state) -> {
            Direction direction = state.getValue(AbstractFurnaceBlock.FACING);
            if (state.getValue(AbstractFurnaceBlock.LIT))
            switch (direction) {
                case NORTH -> {
                    return ConfiguredModel.builder().modelFile(lit).build();
                }
                case SOUTH -> {
                    return ConfiguredModel.builder().modelFile(lit).rotationY(180).build();
                }
                case WEST -> {
                    return ConfiguredModel.builder().modelFile(lit).rotationY(270).build();
                }
                case EAST -> {
                    return ConfiguredModel.builder().modelFile(lit).rotationY(90).build();
                }
            }
            else
                switch (direction) {
                    case NORTH -> {
                        return ConfiguredModel.builder().modelFile(normal).build();
                    }
                    case SOUTH -> {
                        return ConfiguredModel.builder().modelFile(normal).rotationY(180).build();
                    }
                    case WEST -> {
                        return ConfiguredModel.builder().modelFile(normal).rotationY(270).build();
                    }
                    case EAST -> {
                        return ConfiguredModel.builder().modelFile(normal).rotationY(90).build();
                    }
                }
            return ConfiguredModel.builder().build();
        });
    }

    public void dungeonPillar(RotatedPillarBlock block) {
        this.axisBlock(block, this.extend(this.texture(this.name(block), "dungeon/"), "_side"), ResourceLocation.fromNamespaceAndPath(AetherGenesis.MODID, "block/dungeon/carved_pillar_top"));
    }

    public void dungeonPillarTop(FacingPillarBlock block) {
        ResourceLocation side = this.texture("carved_pillar_carved", "dungeon/");
        ResourceLocation end = this.texture(this.name(block), "dungeon/");
        ModelFile vertical = this.models().cubeColumn(this.name(block), side, end);
        ModelFile horizontal = this.models().cubeColumnHorizontal(this.name(block) + "_horizontal", side, end);
        this.getVariantBuilder(block)
                .partialState().with(FacingPillarBlock.FACING, Direction.DOWN).modelForState().modelFile(vertical).rotationX(180).addModel()
                .partialState().with(FacingPillarBlock.FACING, Direction.EAST).modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel()
                .partialState().with(FacingPillarBlock.FACING, Direction.NORTH).modelForState().modelFile(horizontal).rotationX(90).addModel()
                .partialState().with(FacingPillarBlock.FACING, Direction.SOUTH).modelForState().modelFile(horizontal).rotationX(90).rotationY(180).addModel()
                .partialState().with(FacingPillarBlock.FACING, Direction.UP).modelForState().modelFile(vertical).addModel()
                .partialState().with(FacingPillarBlock.FACING, Direction.WEST).modelForState().modelFile(horizontal).rotationX(90).rotationY(270).addModel();
    }

    public void coldFire(ColdFireBlock block) {
        ModelFile fireFloor0 = models().withExistingParent(this.name(block) + "_floor0", this.mcLoc("block/template_fire_floor")).renderType(ResourceLocation.withDefaultNamespace("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_0"));
        ModelFile fireFloor1 = models().withExistingParent(this.name(block) + "_floor1", this.mcLoc("block/template_fire_floor")).renderType(ResourceLocation.withDefaultNamespace("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_1"));
        ModelFile fireSide0 = models().withExistingParent(this.name(block) + "_side0", this.mcLoc("block/template_fire_side")).renderType(ResourceLocation.withDefaultNamespace("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_0"));
        ModelFile fireSide1 = models().withExistingParent(this.name(block) + "_side1", this.mcLoc("block/template_fire_side")).renderType(ResourceLocation.withDefaultNamespace("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_1"));
        ModelFile fireSideAlt0 = models().withExistingParent(this.name(block) + "_side_alt0", this.mcLoc("block/template_fire_side_alt")).renderType(ResourceLocation.withDefaultNamespace("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_0"));
        ModelFile fireSideAlt1 = models().withExistingParent(this.name(block) + "_side_alt1", this.mcLoc("block/template_fire_side_alt")).renderType(ResourceLocation.withDefaultNamespace("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_1"));
        ModelFile fireUp0 = models().withExistingParent(this.name(block) + "_up0", this.mcLoc("block/template_fire_up")).renderType(ResourceLocation.withDefaultNamespace("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_0"));
        ModelFile fireUp1 = models().withExistingParent(this.name(block) + "_up1", this.mcLoc("block/template_fire_up")).renderType(ResourceLocation.withDefaultNamespace("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_1"));
        ModelFile fireUpAlt0 = models().withExistingParent(this.name(block) + "_up_alt0", this.mcLoc("block/template_fire_up_alt")).renderType(ResourceLocation.withDefaultNamespace("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_0"));
        ModelFile fireUpAlt1 = models().withExistingParent(this.name(block) + "_up_alt1", this.mcLoc("block/template_fire_up_alt")).renderType(ResourceLocation.withDefaultNamespace("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_1"));
        this.getMultipartBuilder(block)
                .part().modelFile(fireFloor0).nextModel().modelFile(fireFloor1).addModel()
                .condition(ColdFireBlock.EAST, false)
                .condition(ColdFireBlock.NORTH, false)
                .condition(ColdFireBlock.SOUTH, false)
                .condition(ColdFireBlock.UP, false)
                .condition(ColdFireBlock.WEST, false).end()
                .part().modelFile(fireSide0).nextModel().modelFile(fireSide1).nextModel().modelFile(fireSideAlt0).nextModel().modelFile(fireSideAlt1).addModel()
                .useOr().nestedGroup().condition(ColdFireBlock.NORTH, true).end()
                .useOr().nestedGroup().condition(ColdFireBlock.EAST, false)
                .condition(ColdFireBlock.NORTH, false)
                .condition(ColdFireBlock.SOUTH, false)
                .condition(ColdFireBlock.UP, false)
                .condition(ColdFireBlock.WEST, false).end().end()
                .part().modelFile(fireSide0).rotationY(90).nextModel().modelFile(fireSide1).rotationY(90).nextModel().modelFile(fireSideAlt0).rotationY(90).nextModel().modelFile(fireSideAlt1).rotationY(90).addModel()
                .useOr().nestedGroup().condition(ColdFireBlock.EAST, true).end()
                .useOr().nestedGroup().condition(ColdFireBlock.EAST, false)
                .condition(ColdFireBlock.NORTH, false)
                .condition(ColdFireBlock.SOUTH, false)
                .condition(ColdFireBlock.UP, false)
                .condition(ColdFireBlock.WEST, false).end().end()
                .part().modelFile(fireSide0).rotationY(180).nextModel().modelFile(fireSide1).rotationY(180).nextModel().modelFile(fireSideAlt0).rotationY(180).nextModel().modelFile(fireSideAlt1).rotationY(180).addModel()
                .useOr().nestedGroup().condition(ColdFireBlock.SOUTH, true).end()
                .useOr().nestedGroup().condition(ColdFireBlock.EAST, false)
                .condition(ColdFireBlock.NORTH, false)
                .condition(ColdFireBlock.SOUTH, false)
                .condition(ColdFireBlock.UP, false)
                .condition(ColdFireBlock.WEST, false).end().end()
                .part().modelFile(fireSide0).rotationY(270).nextModel().modelFile(fireSide1).rotationY(270).nextModel().modelFile(fireSideAlt0).rotationY(270).nextModel().modelFile(fireSideAlt1).rotationY(270).addModel()
                .useOr().nestedGroup().condition(ColdFireBlock.WEST, true).end()
                .useOr().nestedGroup().condition(ColdFireBlock.EAST, false)
                .condition(ColdFireBlock.NORTH, false)
                .condition(ColdFireBlock.SOUTH, false)
                .condition(ColdFireBlock.UP, false)
                .condition(ColdFireBlock.WEST, false).end().end()
                .part().modelFile(fireUp0).nextModel().modelFile(fireUp1).nextModel().modelFile(fireUpAlt0).nextModel().modelFile(fireUpAlt1).addModel()
                .condition(ColdFireBlock.UP, true).end();
    }
}
