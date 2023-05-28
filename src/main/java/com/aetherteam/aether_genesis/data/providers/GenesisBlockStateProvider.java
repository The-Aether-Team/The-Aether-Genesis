package com.aetherteam.aether_genesis.data.providers;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.block.AetherBlockStateProperties;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.providers.AetherBlockStateProvider;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.block.natural.OrangeTreeBlock;
import com.aetherteam.aether_genesis.block.natural.PurpleAercloudBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Map;

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

    public void logWallBlock(WallBlock block, Block baseBlock, String location, String modid, boolean postUsesTop) {
        this.logWallBlockInternal(block, this.name(block), new ResourceLocation(modid, "block/" + location + this.name(baseBlock)), postUsesTop);
    }

    private void logWallBlockInternal(WallBlock block, String baseName, ResourceLocation texture, boolean postUsesTop) {
        ModelFile postBig = models().withExistingParent(baseName + "_post", this.mcLoc("block/block"))
                .texture("particle", texture).texture("top", postUsesTop ? (texture + "_top") : texture.toString()).texture("side", texture)
                .element().from(4.0F, 0.0F, 4.0F).to(12.0F, 16.0F, 12.0F)
                .face(Direction.DOWN).texture("#top").cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").cullface(Direction.UP).end()
                .face(Direction.NORTH).texture("#side").end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
        ModelFile postShort = models().withExistingParent(baseName + "_post_short", this.mcLoc("block/block"))
                .texture("particle", texture).texture("top", texture).texture("side", texture)
                .element().from(5.0F, 0.0F, 5.0F).to(11.0F, 14.0F, 11.0F)
                .face(Direction.DOWN).texture("#top").cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").cullface(Direction.UP).end()
                .face(Direction.NORTH).texture("#side").end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
        ModelFile postTall = models().withExistingParent(baseName + "_post_tall", this.mcLoc("block/block"))
                .texture("particle", texture).texture("top", texture).texture("side", texture)
                .element().from(5.0F, 0.0F, 5.0F).to(11.0F, 16.0F, 11.0F)
                .face(Direction.DOWN).texture("#top").cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").cullface(Direction.UP).end()
                .face(Direction.NORTH).texture("#side").end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();

        ModelFile side = models().withExistingParent(baseName + "_side", this.mcLoc("block/block"))
                .texture("particle", texture).texture("top", texture).texture("side", texture)
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, 14.0F, 5.0F)
                .face(Direction.DOWN).texture("#top").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).uvs(0, 5, 5, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).uvs(0, 5, 5, 11).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
        ModelFile sideAlt = models().withExistingParent(baseName + "_side_alt", this.mcLoc("block/block"))
                .texture("particle", texture).texture("top", texture).texture("side", texture)
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, 14.0F, 5.0F)
                .face(Direction.DOWN).texture("#top").rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90).uvs(11, 5, 16, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90).uvs(11, 5, 16, 11).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
        ModelFile sideTall = models().withExistingParent(baseName + "_side_tall", this.mcLoc("block/block"))
                .texture("particle", texture).texture("top", texture).texture("side", texture)
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, 16.0F, 5.0F)
                .face(Direction.DOWN).texture("#top").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).uvs(0, 5, 5, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).uvs(0, 5, 5, 11).cullface(Direction.UP).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
        ModelFile sideTallAlt = models().withExistingParent(baseName + "_side_tall_alt", this.mcLoc("block/block"))
                .texture("particle", texture).texture("top", texture).texture("side", texture)
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, 16.0F, 5.0F)
                .face(Direction.DOWN).texture("#top").rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90).uvs(11, 5, 16, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90).uvs(11, 5, 16, 11).cullface(Direction.UP).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();

        ModelFile sideShort = models().withExistingParent(baseName + "_side_short", this.mcLoc("block/block"))
                .texture("particle", texture).texture("top", texture).texture("side", texture)
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, 14.0F, 4.0F)
                .face(Direction.DOWN).texture("#top").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).uvs(0, 5, 4, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).uvs(0, 5, 4, 11).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
        ModelFile sideAltShort = models().withExistingParent(baseName + "_side_alt_short", this.mcLoc("block/block"))
                .texture("particle", texture).texture("top", texture).texture("side", texture)
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, 14.0F, 4.0F)
                .face(Direction.DOWN).texture("#top").rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90).uvs(12, 5, 16, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90).uvs(12, 5, 16, 11).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
        ModelFile sideTallShort = models().withExistingParent(baseName + "_side_tall_short", this.mcLoc("block/block"))
                .texture("particle", texture).texture("top", texture).texture("side", texture)
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, 16.0F, 4.0F)
                .face(Direction.DOWN).texture("#top").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).uvs(0, 5, 4, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(ModelBuilder.FaceRotation.CLOCKWISE_90).uvs(0, 5, 4, 11).cullface(Direction.UP).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();
        ModelFile sideTallAltShort = models().withExistingParent(baseName + "_side_tall_alt_short", this.mcLoc("block/block"))
                .texture("particle", texture).texture("top", texture).texture("side", texture)
                .element().from(5.0F, 0.0F, 0.0F).to(11.0F, 16.0F, 4.0F)
                .face(Direction.DOWN).texture("#top").rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90).uvs(12, 5, 16, 11).cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").rotation(ModelBuilder.FaceRotation.COUNTERCLOCKWISE_90).uvs(12, 5, 16, 11).cullface(Direction.UP).end()
                .face(Direction.NORTH).texture("#side").cullface(Direction.NORTH).end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end().end();

        this.logWallBlock(this.getMultipartBuilder(block), postShort, postTall, side, sideAlt, sideTall, sideTallAlt);
        this.logWallBlockWithPost(this.getMultipartBuilder(block), postBig, sideShort, sideAltShort, sideTallShort, sideTallAltShort);
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

    public void woodWallBlock(WallBlock block, Block baseBlock, String location, String modid) {
        this.wallBlockInternal(block, this.name(block), new ResourceLocation(modid, "block/" + location + this.name(baseBlock)));
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

    public void brick(RotatedPillarBlock block) {
        ResourceLocation side = this.texture(this.name(block), "construction/");
        if(block == GenesisBlocks.HOLYSTONE_HEADSTONE.get())
            side = new ResourceLocation(Aether.MODID, "block/construction/" + this.name(AetherBlocks.HOLYSTONE_BRICKS.get()));
        this.axisBlock(block, side, this.extend(this.texture(this.name(block), "construction/"), "_top"));
    }

    public void skyrootChest(Block block) {
        ModelFile chest = this.models().cubeAll(this.name(block), new ResourceLocation(Aether.MODID, "block/construction/skyroot_planks"));
        this.chest(block, chest);
    }
}
