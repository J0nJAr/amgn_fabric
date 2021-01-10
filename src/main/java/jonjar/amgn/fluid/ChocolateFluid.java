package jonjar.amgn.fluid;

import jonjar.amgn.registry.ModBlocks;
import jonjar.amgn.registry.ModFluids;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;


public abstract class ChocolateFluid extends amgnFluid {

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
        return ModFluids.CHOCOLATE_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState)
    {
        // method_15741 converts the LEVEL_1_8 of the fluid state to the LEVEL_15 the fluid block uses
        return ModBlocks.MOLTEN_CHOCOLATE.getDefaultState().with(Properties.LEVEL_15, method_15741(fluidState));
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