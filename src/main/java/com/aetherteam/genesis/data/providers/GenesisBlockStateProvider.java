package com.aetherteam.genesis.data.providers;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.block.miscellaneous.FacingPillarBlock;
import com.aetherteam.aether.data.providers.AetherBlockStateProvider;
import com.aetherteam.genesis.AetherGenesis;
import com.aetherteam.genesis.block.GenesisBlocks;
import com.aetherteam.genesis.block.miscellaneous.ColdFireBlock;
import com.aetherteam.genesis.block.natural.OrangeTreeBlock;
import com.aetherteam.genesis.block.natural.PurpleAercloudBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Map;

public abstract class GenesisBlockStateProvider extends AetherBlockStateProvider {
    public GenesisBlockStateProvider(PackOutput output, String id, ExistingFileHelper helper) {
        super(output, id, helper);
    }

    public void enchantedVanillaGrass(Block block, Block grassBlock, Block dirtBlock) {
        ModelFile grass = this.cubeBottomTop(this.name(block),
                this.extend(this.texture(this.name(block), "natural/"), "_side"),
                this.mcLoc("block/" + this.name(dirtBlock)),
                this.extend(this.texture(this.name(block), "natural/"), "_top"));
        ModelFile grassSnowed = this.cubeBottomTop(this.name(block) + "_snow",
                this.extend(this.mcLoc("block/" + this.name(grassBlock)), "_snow"),
                this.mcLoc("block/" + this.name(dirtBlock)),
                this.extend(this.texture(this.name(block), "natural/"), "_top"));
        this.getVariantBuilder(block).forAllStatesExcept(state -> {
            boolean snowy = state.getValue(SnowyDirtBlock.SNOWY);
            return ConfiguredModel.allYRotations(snowy ? grassSnowed : grass, 0, false);
        }, AetherBlockStateProperties.DOUBLE_DROPS);
    }

    public void purpleAercloud(Block block) {
        String blockName = this.name(block);
        ResourceLocation front = this.extend(this.texture(this.name(block), "natural/"), "_front");
        ResourceLocation back = this.extend(this.texture(this.name(block), "natural/"), "_back");
        ResourceLocation right = this.extend(this.texture(this.name(block), "natural/"), "_right");
        ResourceLocation left = this.extend(this.texture(this.name(block), "natural/"), "_left");
        ModelFile rightModel = this.models().cubeBottomTop(blockName, right, back, front).renderType(new ResourceLocation("translucent"));
        ModelFile leftModel = this.models().cubeBottomTop(blockName, left, back, front).renderType(new ResourceLocation("translucent"));
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
            ModelFile model = this.models().cross(blockName + (lower ? (halfString + bottomAge) : (halfString + topAge)), location).renderType(new ResourceLocation("cutout"));
            return ConfiguredModel.builder().modelFile(model).build();
        }, AetherBlockStateProperties.DOUBLE_DROPS);
    }

    public void pottedOrangeTree(Block block, Block tree) {
        ModelFile pot = this.models().withExistingParent(this.name(block), this.mcLoc("block/flower_pot_cross")).texture("plant", this.modLoc("block/natural/" + this.name(tree) + "_bottom_0")).renderType(new ResourceLocation("cutout"));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(pot));
    }

    protected BlockModelBuilder makeWallPostModel(int width, int height, String name) {
        return models().withExistingParent(name, this.mcLoc("block/block"))
                .element().from(8 - width, 0.0F, 8 - width).to(8 + width, height, 8 + width)
                .face(Direction.DOWN).texture("#top").cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").cullface(Direction.UP).end()
                .face(Direction.NORTH).texture("#side").end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
    }

    protected BlockModelBuilder makeWallSideModel(int length, int height, String name, ModelBuilder.FaceRotation faceRotation, int u1, int u2) {
        return models().withExistingParent(name, this.mcLoc("block/block"))
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, height, length)
                .face(Direction.DOWN).texture("#top").rotation(faceRotation).uvs(u1, 5, u2, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(faceRotation).uvs(u1, 5, u2, 11).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
    }

    public void logWallBlock(WallBlock block, Block baseBlock, String location, String modid, boolean postUsesTop, ModelFile postBig, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt, ModelFile sideShort, ModelFile sideAltShort, ModelFile sideTallShort, ModelFile sideTallAltShort) {
        this.logWallBlockInternal(block, this.name(block), new ResourceLocation(modid, "block/" + location + this.name(baseBlock)), postUsesTop, postBig, postShort, postTall, side, sideAlt, sideTall, sideTallAlt, sideShort, sideAltShort, sideTallShort, sideTallAltShort);
    }

    private void logWallBlockInternal(WallBlock block, String baseName, ResourceLocation texture, boolean postUsesTop, ModelFile postBig, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt, ModelFile sideShort, ModelFile sideAltShort, ModelFile sideTallShort, ModelFile sideTallAltShort) {
        this.logWallBlock(
                this.getMultipartBuilder(block),
                models().getBuilder(baseName + "_post_short").parent(postShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_post_tall").parent(postTall).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side").parent(side).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_alt").parent(sideAlt).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall").parent(sideTall).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_alt").parent(sideTallAlt).texture("particle", texture).texture("top", texture).texture("side", texture)
        );

        this.logWallBlockWithPost(
                this.getMultipartBuilder(block),
                models().getBuilder(baseName + "_post").parent(postBig).texture("particle", texture).texture("top", postUsesTop ? (texture + "_top") : texture.toString()).texture("side", texture),
                models().getBuilder(baseName + "_side_short").parent(sideShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_alt_short").parent(sideAltShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_short").parent(sideTallShort).texture("particle", texture).texture("top", texture).texture("side", texture),
                models().getBuilder(baseName + "_side_tall_alt_short").parent(sideTallAltShort).texture("particle", texture).texture("top", texture).texture("side", texture)
        );
    }

    public void logWallBlock(MultiPartBlockStateBuilder builder, ModelFile postShort, ModelFile postTall, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt) {
        builder
                // Smaller post when West & East are both short while North & South being none
                .part().modelFile(postShort).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.LOW).condition(WallBlock.WEST_WALL, WallSide.LOW).end().end()
                // Taller thinner post when West & East are both tall while North & South being none
                .part().modelFile(postTall).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.TALL).condition(WallBlock.WEST_WALL, WallSide.TALL).end().end()
                // Rotated small post when West & East are both none while North & South are short
                .part().modelFile(postShort).rotationY(90).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.NONE).condition(WallBlock.NORTH_WALL, WallSide.LOW).condition(WallBlock.WEST_WALL, WallSide.NONE).condition(WallBlock.SOUTH_WALL, WallSide.LOW).end().end()
                // Rotated small post when West & East are both none while North & South are tall
                .part().modelFile(postTall).rotationY(90).addModel()
                .nestedGroup().condition(WallBlock.UP, false).condition(WallBlock.EAST_WALL, WallSide.NONE).condition(WallBlock.NORTH_WALL, WallSide.TALL).condition(WallBlock.WEST_WALL, WallSide.NONE).condition(WallBlock.SOUTH_WALL, WallSide.TALL).end().end();
        WALL_PROPS.entrySet().stream()
                .filter(e -> e.getKey().getAxis().isHorizontal())
                .forEach(e -> {
                    this.logWallSidePart(builder, side, sideAlt, e, WallSide.LOW, false);
                    this.logWallSidePart(builder, sideTall, sideTallAlt, e, WallSide.TALL, false);
                });
    }

    public void logWallBlockWithPost(MultiPartBlockStateBuilder builder, ModelFile postBig, ModelFile side, ModelFile sideAlt, ModelFile sideTall, ModelFile sideTallAlt) {
        builder
                // Big post for connections, typically including angled
                .part().modelFile(postBig).addModel()
                .condition(WallBlock.UP, true).end();
        WALL_PROPS.entrySet().stream()
                .filter(e -> e.getKey().getAxis().isHorizontal())
                .forEach(e -> {
                    this.logWallSidePart(builder, side, sideAlt, e, WallSide.LOW, true);
                    this.logWallSidePart(builder, sideTall, sideTallAlt, e, WallSide.TALL, true);
                });
    }

    private void logWallSidePart(MultiPartBlockStateBuilder builder, ModelFile model, ModelFile modelAlt, Map.Entry<Direction, Property<WallSide>> entry, WallSide height, boolean hasPost) {
        int rotation = (((int) entry.getKey().toYRot()) + 180) % 360;
        builder.part()
                .modelFile(rotation < 180 ? model : modelAlt)
                .rotationY(rotation)
                .addModel()
                .condition(entry.getValue(), height).condition(WallBlock.UP, hasPost);
    }

    public void skyrootCraftingTable(Block block, Block baseBlock, String location, String modid) {
        ResourceLocation baseTexture = new ResourceLocation(modid, "block/" + location + this.name(baseBlock));
        ModelFile workbench = this.models().cube(this.name(block),
                baseTexture,
                this.extend(this.texture(this.name(block), "utility/"), "_top"),
                this.extend(this.texture(this.name(block), "utility/"), "_front"),
                this.extend(this.texture(this.name(block), "utility/"), "_side"),
                this.extend(this.texture(this.name(block), "utility/"), "_front"),
                this.extend(this.texture(this.name(block), "utility/"), "_side"))
                .texture("particle", this.extend(this.texture(this.name(block), "utility/"), "_front"));
        this.getVariantBuilder(block).partialState().addModels(new ConfiguredModel(workbench));
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

    public void holystonePillar(FacingPillarBlock block) {
        ResourceLocation side = this.texture(this.name(block), "construction/");
        if (block == GenesisBlocks.HOLYSTONE_HEADSTONE.get()) {
            side = new ResourceLocation(Aether.MODID, "block/construction/" + this.name(AetherBlocks.HOLYSTONE_BRICKS.get()));
        }
        ResourceLocation end = this.extend(this.texture(this.name(block), "construction/"), "_top");
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

    public void dungeonPillar(RotatedPillarBlock block) {
        this.axisBlock(block, this.extend(this.texture(this.name(block), "dungeon/"), "_side"), new ResourceLocation(AetherGenesis.MODID, "block/dungeon/carved_pillar_top"));
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

    public void skyrootChest(Block block) {
        ModelFile chest = this.models().cubeAll(this.name(block), new ResourceLocation(Aether.MODID, "block/construction/skyroot_planks"));
        this.chest(block, chest);
    }

    public void skyrootLadder(LadderBlock block) {
        ResourceLocation location = this.texture(this.name(block), "construction/");
        ModelFile ladder = models().withExistingParent(this.name(block), this.mcLoc("block/block")).renderType(new ResourceLocation("cutout")).ao(false)
                .texture("particle", location).texture("texture", location)
                .element().from(0.0F, 0.0F, 15.2F).to(16.0F, 16.0F, 15.2F).shade(false)
                .face(Direction.NORTH).uvs(0.0F, 0.0F, 16.0F, 16.0F).texture("#texture").end()
                .face(Direction.SOUTH).uvs(16.0F, 0.0F, 0.0F, 16.0F).texture("#texture").end()
                .end();
        this.getVariantBuilder(block).forAllStatesExcept((state) -> {
            Direction direction = state.getValue(LadderBlock.FACING);
            return ConfiguredModel.builder().modelFile(ladder).rotationY((int) (direction.toYRot() + 180) % 360).build();
        }, LadderBlock.WATERLOGGED);
    }

    public void coldFire(ColdFireBlock block) {
        ModelFile fireFloor0 = models().withExistingParent(this.name(block) + "_floor0", this.mcLoc("block/template_fire_floor")).renderType(new ResourceLocation("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_0"));
        ModelFile fireFloor1 = models().withExistingParent(this.name(block) + "_floor1", this.mcLoc("block/template_fire_floor")).renderType(new ResourceLocation("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_1"));
        ModelFile fireSide0 = models().withExistingParent(this.name(block) + "_side0", this.mcLoc("block/template_fire_side")).renderType(new ResourceLocation("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_0"));
        ModelFile fireSide1 = models().withExistingParent(this.name(block) + "_side1", this.mcLoc("block/template_fire_side")).renderType(new ResourceLocation("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_1"));
        ModelFile fireSideAlt0 = models().withExistingParent(this.name(block) + "_side_alt0", this.mcLoc("block/template_fire_side_alt")).renderType(new ResourceLocation("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_0"));
        ModelFile fireSideAlt1 = models().withExistingParent(this.name(block) + "_side_alt1", this.mcLoc("block/template_fire_side_alt")).renderType(new ResourceLocation("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_1"));
        ModelFile fireUp0 = models().withExistingParent(this.name(block) + "_up0", this.mcLoc("block/template_fire_up")).renderType(new ResourceLocation("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_0"));
        ModelFile fireUp1 = models().withExistingParent(this.name(block) + "_up1", this.mcLoc("block/template_fire_up")).renderType(new ResourceLocation("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_1"));
        ModelFile fireUpAlt0 = models().withExistingParent(this.name(block) + "_up_alt0", this.mcLoc("block/template_fire_up_alt")).renderType(new ResourceLocation("cutout"))
                .texture("fire", this.extend(this.texture(this.name(block), "miscellaneous/"), "_0"));
        ModelFile fireUpAlt1 = models().withExistingParent(this.name(block) + "_up_alt1", this.mcLoc("block/template_fire_up_alt")).renderType(new ResourceLocation("cutout"))
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
