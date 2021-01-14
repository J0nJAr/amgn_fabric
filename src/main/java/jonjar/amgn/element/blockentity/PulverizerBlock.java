package jonjar.amgn.element.blockentity;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;


public class PulverizerBlock extends BlockWithEntity {
    public static final DirectionProperty FACING;
    public static final BooleanProperty LIT;
    public static final IntProperty CURRENT = IntProperty.of("current", 0, 4);

    public PulverizerBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(CURRENT, 0));
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
        //This is called by the onUse method inside AbstractFurnaceBlock so
        //it is a little bit different of how you open the screen for normal container
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
        builder.add(new Property[]{FACING, LIT,CURRENT});
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if ((Boolean)state.get(LIT)) {

            double d = (double)pos.getX() + 0.5D;
            double e = (double)pos.getY();
            double f = (double)pos.getZ() + 0.5D;
            int img = (int)(world.getTime()%4);


//            world.setBlockState(pos,state.with(CURRENT,img));


            if (random.nextDouble() < 0.1D) {
                world.playSound(d, e, f, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = (Direction)state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double g = 0.52D;
            double h = random.nextDouble() * 0.6D - 0.3D;
            double i = axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.52D : h;
            double j = random.nextDouble() * 6.0D / 16.0D;
            double k = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.52D : h;
            world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0D, 0.0D, 0.0D);
        }else{
            world.setBlockState(pos,state.with(CURRENT,1));
        }
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        LIT = Properties.LIT;

    }
}
