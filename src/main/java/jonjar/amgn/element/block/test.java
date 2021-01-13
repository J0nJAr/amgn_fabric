package jonjar.amgn.element.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class test extends Block {
    public static final IntProperty TEST = IntProperty.of("test", 1, 5);

    public test(Settings settings){
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(TEST, 1));
    }



    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(TEST);
    }


    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient){
            int i = (Integer)state.get(TEST);
            if(i < 4) {
                world.setBlockState(pos, state.with(TEST, i+1));
                player.sendMessage(new LiteralText(Integer.toString(i+1)), false);
            }
            else {
                world.setBlockState(pos, state.with(TEST, 1));
                player.sendMessage(new LiteralText("0"), false);
            }


            return ActionResult.SUCCESS;
        }

        return ActionResult.SUCCESS;

    }




}
