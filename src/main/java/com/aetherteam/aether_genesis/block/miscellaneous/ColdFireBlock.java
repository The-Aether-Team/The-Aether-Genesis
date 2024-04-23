package com.aetherteam.aether_genesis.block.miscellaneous;

import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * [CODE COPY] - {@link net.minecraft.world.level.block.FireBlock}.<br><br>
 * Unable to spread like normal fire.<br><br>
 * Warning for "deprecation" is suppressed for methods that are fine to override.
 */
@SuppressWarnings("deprecation")
public class ColdFireBlock extends BaseFireBlock {
    public static final MapCodec<ColdFireBlock> CODEC = simpleCodec(ColdFireBlock::new);
    public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
    public static final BooleanProperty NORTH = PipeBlock.NORTH;
    public static final BooleanProperty EAST = PipeBlock.EAST;
    public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
    public static final BooleanProperty WEST = PipeBlock.WEST;
    public static final BooleanProperty UP = PipeBlock.UP;
    private static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter((map) -> map.getKey() != Direction.DOWN).collect(Util.toMap());
    private static final VoxelShape UP_AABB = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape WEST_AABB = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    private static final VoxelShape EAST_AABB = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    private static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
    private final Map<BlockState, VoxelShape> shapesCache;

    public ColdFireBlock(BlockBehaviour.Properties properties) {
        super(properties, 1.0F);
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0).setValue(NORTH, Boolean.FALSE).setValue(EAST, Boolean.FALSE).setValue(SOUTH, Boolean.FALSE).setValue(WEST, Boolean.FALSE).setValue(UP, Boolean.FALSE));
        this.shapesCache = ImmutableMap.copyOf(this.getStateDefinition().getPossibleStates().stream().filter((p_53497_) -> p_53497_.getValue(AGE) == 0).collect(Collectors.toMap(Function.identity(), ColdFireBlock::calculateShape)));
    }

    @Override
    protected MapCodec<? extends BaseFireBlock> codec() {
        return CODEC;
    }

    private static VoxelShape calculateShape(BlockState state) {
        VoxelShape voxelshape = Shapes.empty();
        if (state.getValue(UP)) {
            voxelshape = UP_AABB;
        }
        if (state.getValue(NORTH)) {
            voxelshape = Shapes.or(voxelshape, NORTH_AABB);
        }
        if (state.getValue(SOUTH)) {
            voxelshape = Shapes.or(voxelshape, SOUTH_AABB);
        }
        if (state.getValue(EAST)) {
            voxelshape = Shapes.or(voxelshape, EAST_AABB);
        }
        if (state.getValue(WEST)) {
            voxelshape = Shapes.or(voxelshape, WEST_AABB);
        }
        return voxelshape.isEmpty() ? DOWN_AABB : voxelshape;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return this.canSurvive(state, level, currentPos) ? this.getStateWithAge(level, currentPos, state.getValue(AGE)) : Blocks.AIR.defaultBlockState();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.shapesCache.get(state.setValue(AGE, 0));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.getStateForPlacement(context.getLevel(), context.getClickedPos());
    }

    protected BlockState getStateForPlacement(BlockGetter level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);
        if (!this.canCatchFire(level, pos, Direction.UP) && !belowState.isFaceSturdy(level, belowPos, Direction.UP)) {
            BlockState blockState = this.defaultBlockState();
            for (Direction direction : Direction.values()) {
                BooleanProperty booleanproperty = PROPERTY_BY_DIRECTION.get(direction);
                if (booleanproperty != null) {
                    blockState = blockState.setValue(booleanproperty, this.canCatchFire(level, pos.relative(direction), direction.getOpposite()));
                }
            }
            return blockState;
        } else {
            return this.defaultBlockState();
        }
    }

    public static BlockState getState(BlockGetter level, BlockPos pos) {
        return GenesisBlocks.COLD_FIRE.get().getStateForPlacement(level, pos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos belowPos = pos.below();
        return level.getBlockState(belowPos).isFaceSturdy(level, belowPos, Direction.UP) || this.isValidFireLocation(level, pos);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.scheduleTick(pos, this, getFireTickDelay(level.random));
        if (level.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
            state.canSurvive(level, pos);
            BlockState belowState = level.getBlockState(pos.below());
            boolean flag = belowState.isFireSource(level, pos, Direction.UP);
            int i = state.getValue(AGE);
            if (!flag && level.isRaining() && this.isNearRain(level, pos) && random.nextFloat() < 0.2F + (float) i * 0.03F) {
                level.removeBlock(pos, false);
                level.removeBlock(pos, false);
            } else {
                int j = Math.min(15, i + random.nextInt(3) / 2);
                if (i != j) {
                    state = state.setValue(AGE, j);
                    level.setBlock(pos, state, 4);
                }
                if (!flag) {
                    if (!this.isValidFireLocation(level, pos)) {
                        BlockPos belowPos = pos.below();
                        if (!level.getBlockState(belowPos).isFaceSturdy(level, belowPos, Direction.UP) || i > 3) {
                            level.removeBlock(pos, false);
                        }
                        return;
                    }
                    if (i == 15 && random.nextInt(4) == 0 && !this.canCatchFire(level, pos.below(), Direction.UP)) {
                        level.removeBlock(pos, false);
                    }
                }
            }
        }
    }

    protected boolean isNearRain(Level level, BlockPos pos) {
        return level.isRainingAt(pos) || level.isRainingAt(pos.west()) || level.isRainingAt(pos.east()) || level.isRainingAt(pos.north()) || level.isRainingAt(pos.south());
    }

    private BlockState getStateWithAge(LevelAccessor level, BlockPos pos, int age) {
        BlockState blockstate = getState(level, pos);
        return blockstate.is(GenesisBlocks.COLD_FIRE.get()) ? blockstate.setValue(AGE, age) : blockstate;
    }

    private boolean isValidFireLocation(BlockGetter level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (this.canCatchFire(level, pos.relative(direction), direction.getOpposite())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, getFireTickDelay(level.getRandom()));
    }

    private static int getFireTickDelay(RandomSource random) {
        return 30 + random.nextInt(10);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, NORTH, EAST, SOUTH, WEST, UP);
    }

    @Override
    protected boolean canBurn(BlockState state) {
        return false;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.hurt(level.damageSources().inFire(), 1.0F);
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 4));
        }
    }

    public boolean canCatchFire(BlockGetter level, BlockPos pos, Direction face) {
        return level.getBlockState(pos).isFlammable(level, pos, face);
    }
}
