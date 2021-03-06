package jonjar.amgn.element.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CompressedRicecakeBlock extends Block {
    public static final IntProperty RiceCake_BITES = IntProperty.of("ricecakebites", 0, 7);

    public CompressedRicecakeBlock(Settings settings){
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(RiceCake_BITES, 0));
    }



    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(RiceCake_BITES);
    }


    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient){
            int i = (Integer)state.get(RiceCake_BITES);
            if(i < 7) {
                world.setBlockState(pos, state.with(RiceCake_BITES, i+1));
                //디버그 player.sendMessage(new LiteralText(Integer.toString(i+1)), false);
            }
            else {
                world.setBlockState(pos, state.with(RiceCake_BITES, 0));
                //디버그 player.sendMessage(new LiteralText("0"), false);
            }


            return ActionResult.SUCCESS;
        }

        return ActionResult.SUCCESS;

    }




}
