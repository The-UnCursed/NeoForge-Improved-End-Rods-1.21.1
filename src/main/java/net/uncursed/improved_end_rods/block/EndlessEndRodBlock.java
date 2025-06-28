package net.uncursed.improved_end_rods.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

public class EndlessEndRodBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public EndlessEndRodBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) {
        return 1.0f;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
        Direction direction = state.getValue(FACING);
        switch (direction) {
            case UP:
            case DOWN:
                return Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
            case NORTH:
            case SOUTH:
                return Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D);
            case EAST:
            case WEST:
                return Block.box(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
            default:
                return Shapes.block();
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Direction direction = state.getValue(FACING);
        double d0 = (double)pos.getX() + 0.55 - (double)(random.nextFloat() * 0.1F);
        double d1 = (double)pos.getY() + 0.55 - (double)(random.nextFloat() * 0.1F);
        double d2 = (double)pos.getZ() + 0.55 - (double)(random.nextFloat() * 0.1F);
        double d3 = (double)(0.4F - (random.nextFloat() + random.nextFloat()) * 0.4F);
        if (random.nextInt(5) == 0) {
            level.addParticle(
                    ParticleTypes.END_ROD,
                    d0 + (double)direction.getStepX() * d3,
                    d1 + (double)direction.getStepY() * d3,
                    d2 + (double)direction.getStepZ() * d3,
                    random.nextGaussian() * 0.005,
                    random.nextGaussian() * 0.005,
                    random.nextGaussian() * 0.005
            );
        }
    }
}
