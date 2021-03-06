package jonjar.amgn.element.fluid;

import jonjar.amgn.base.BaseFluid;
import jonjar.amgn.registry.ModBlocks;
import jonjar.amgn.registry.ModFluids;
import jonjar.amgn.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.world.WorldView;


public abstract class ChocolateFluid extends BaseFluid {

    @Override
    public Fluid getStill()
    {
        return ModFluids.STILL_MOLTEN_CHOCOLATE;
    }

    @Override
    public Fluid getFlowing()
    {
        return ModFluids.FLOWING_CHOCOLATE;
    }

    @Override
    public Item getBucketItem()
    {
        return ModItems.CHOCOLATE_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState)
    {
        // method_15741 converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
        return ModBlocks.MOLTEN_CHOCOLATE.getDefaultState().with(Properties.LEVEL_15, method_15741(fluidState));
    }
    @Override
    public int getTickRate(WorldView worldView)
    {
        return 15;
    }

    @Override
    protected int getFlowSpeed(WorldView worldView)
    {
        return 3;
    }

    public static class Flowing extends ChocolateFluid
    {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder)
        {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }


        @Override
        public int getLevel(FluidState fluidState)
        {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState)
        {
            return false;
        }
    }

    public static class Still extends ChocolateFluid
    {
        @Override
        public int getLevel(FluidState fluidState)
        {
            return 8;
        }


        @Override
        public boolean isStill(FluidState fluidState)
        {
            return true;
        }
    }
}
