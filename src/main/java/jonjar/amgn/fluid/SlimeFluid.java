package jonjar.amgn.fluid;

import jonjar.amgn.registry.ModBlocks;
import jonjar.amgn.registry.ModFluids;
import jonjar.amgn.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;

public class SlimeFluid extends amgnFluid{

    //
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_SLIME;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_SLIME;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.SLIME_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.MOLTEN_SLIME.getDefaultState().with(Properties.LEVEL_15, method_15741(state));
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }

    public static class Flowing extends SlimeFluid {

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }
    }

    public static class Still extends SlimeFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
