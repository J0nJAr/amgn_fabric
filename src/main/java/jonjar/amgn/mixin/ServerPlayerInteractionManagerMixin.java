package jonjar.amgn.mixin;

import jonjar.amgn.util.RangeCalculator;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayerInteractionManager.class)
abstract class ServerPlayerInteractionManagerMixin {
    @Shadow public ServerWorld world;

    @Shadow private boolean mining;

    @Shadow public ServerPlayerEntity player;

    @ModifyConstant(
            method = "processBlockBreakingAction(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/network/packet/c2s/play/PlayerActionC2SPacket$Action;Lnet/minecraft/util/math/Direction;I)V",
            require = 1, allow = 1, constant = @Constant(doubleValue = 36.0))
    private double getActualReachDistance(final double reachDistance) {

        return RangeCalculator.getSquaredReachDistance(((ServerPlayerInteractionManager) (Object) this).player, reachDistance);
    }

    /*
    @Inject(method = "processBlockBreakingAction(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/network/packet/c2s/play/PlayerActionC2SPacket$Action;Lnet/minecraft/util/math/Direction;I)V",
            at=@At("TAIL"))
    public void processBlockBreakingAction(BlockPos pos, net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action action, Direction direction, int worldHeight, CallbackInfo ci){
        System.out.println(this.mining);
        double d = this.player.getX() - ((double)pos.getX() + 0.5D);
        double e = this.player.getY() - ((double)pos.getY() + 0.5D) + 1.5D;
        double f = this.player.getZ() - ((double)pos.getZ() + 0.5D);
        double g = d * d + e * e + f * f;
        System.out.println(g);
    }


     */



}
