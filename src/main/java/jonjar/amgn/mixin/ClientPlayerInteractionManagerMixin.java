package jonjar.amgn.mixin;

import jonjar.amgn.entity.ResizedEntity;
import jonjar.amgn.registry.ModPacket;
import jonjar.amgn.util.RangeCalculator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerInteractionManager.class)
abstract class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @Shadow private GameMode gameMode;

    @ModifyConstant(
            method = "getReachDistance()F",
            require = 2, allow = 2, constant = { @Constant(floatValue = 5.0F), @Constant(floatValue = 4.5F) })
    private float getActualReachDistance(final float reachDistance) {
        if (this.client.player != null) {
            // TODO Warn on loss of precision if present?
            return (float) RangeCalculator.getReachDistance(this.client.player, reachDistance);
        }
        return reachDistance;
    }

    @Inject(method = "sendPlayerAction", at = @At(value = "HEAD"), cancellable = true)
    private void BlockBreak(PlayerActionC2SPacket.Action action, BlockPos blockPos, Direction direction, CallbackInfo ci) {
        if(((ResizedEntity) this.client.player).getScale() <= 1.0F) return;
            PlayerActionC2SPacket.Action requiredAction = this.gameMode == GameMode.CREATIVE ? PlayerActionC2SPacket.Action.START_DESTROY_BLOCK : PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK;
            if (action.equals(requiredAction) || this.client.world.getBlockState(blockPos).calcBlockBreakingDelta(this.client.player, this.client.player.world, blockPos) >= 1.0f) {
                ModPacket.sendExcavatePacket(blockPos);
                ci.cancel();
            }
    }
}
