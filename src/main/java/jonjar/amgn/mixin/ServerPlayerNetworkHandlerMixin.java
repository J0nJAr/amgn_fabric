package jonjar.amgn.mixin;

import jonjar.amgn.util.RangeCalculator;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayerNetworkHandlerMixin {
    @Shadow
    public ServerPlayerEntity player;

    @ModifyConstant(
            method = "onPlayerInteractEntity(Lnet/minecraft/network/packet/c2s/play/PlayerInteractEntityC2SPacket;)V",
            require = 2, allow = 2, constant = @Constant(doubleValue = 36.0))
    private double getActualAttackRange(final double attackRange, final PlayerInteractEntityC2SPacket packet) {
        if (packet.getType() == PlayerInteractEntityC2SPacket.InteractionType.ATTACK) {
            return RangeCalculator.getSquaredAttackRange(this.player, attackRange);
        }
        // INTERACT, INTERACT_AT
        return RangeCalculator.getSquaredReachDistance(this.player, attackRange);
    }

    @ModifyConstant(
            method = "onPlayerInteractBlock(Lnet/minecraft/network/packet/c2s/play/PlayerInteractBlockC2SPacket;)V",
            require = 1, allow = 1, constant = @Constant(doubleValue = 64.0))
    private double getActualReachDistance(final double reachDistance) {
        return RangeCalculator.getSquaredReachDistance(this.player, reachDistance);
    }
}
