package jonjar.amgn.element.blockentity;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;


public class PulverizerBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty ISON = BooleanProperty.of("ison");

    public PulverizerBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(ISON, false));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new PulverizerBlockEntity();
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (world.isClient && blockEntity instanceof PulverizerBlockEntity) {
            return ActionResult.SUCCESS;
        } if (!world.isClient && blockEntity instanceof PulverizerBlockEntity) {
            this.openScreen(world, pos, player);
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    public void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof PulverizerBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
            // Optional: increment player's stat
            //player.incrementStat(Stats.INTERACT_WITH_FURNACE);
        }
    }
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AbstractPulverizerBlockEntity) {
                ((AbstractPulverizerBlockEntity)blockEntity).setCustomName(itemStack.getName());
            }
        }

    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        ItemScatterer.spawn(world, pos, (PulverizerBlockEntity) world.getBlockEntity(pos));
    }



    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING,ISON});
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(ISON)) {

            double d = (double)pos.getX() + 0.5D;
            double e = (double)pos.getY();
            double f = (double)pos.getZ() + 0.5D;


            if (random.nextDouble() < 0.1D) {
                world.playSound(d, e, f, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);

            }
        }
    }
}
