package jonjar.amgn.block;

import jonjar.amgn.registry.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Random;

public class ChocolateBlock extends Block {

    public ChocolateBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(LightType.BLOCK, pos) > 11 - state.getOpacity(world, pos)) {
            this.melt(state, world, pos);
        }

    }

    protected void melt(BlockState state, World world, BlockPos pos) {
        if (world.getDimension().isUltrawarm()) {
            world.removeBlock(pos, false);
        } else {
            world.setBlockState(pos, ModBlocks.MOLTEN_CHOCOLATE.getDefaultState());
            world.updateNeighbor(pos, ModBlocks.MOLTEN_CHOCOLATE, pos);
        }
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.NORMAL;
    }
}