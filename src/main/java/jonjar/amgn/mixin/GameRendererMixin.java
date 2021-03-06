package jonjar.amgn.mixin;

import jonjar.amgn.util.RangeCalculator;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.resource.SynchronousResourceReloadListener;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameRenderer.class)
abstract class GameRendererMixin implements SynchronousResourceReloadListener/*, AutoCloseable*/ {
    @Shadow
    @Final
    private MinecraftClient client;

    @ModifyConstant(
            method = "updateTargetedEntity(F)V",
            require = 1, allow = 1, constant = @Constant(doubleValue = 6.0))
    private double getActualReachDistance(final double reachDistance) {
        if (this.client.player != null) {
            return RangeCalculator.getReachDistance(this.client.player, reachDistance);
        }
        return reachDistance;
    }

    @ModifyConstant(method = "updateTargetedEntity(F)V", constant = @Constant(doubleValue = 3.0))
    private double getActualAttackRange0(final double attackRange) {
        if (this.client.player != null) {
            return RangeCalculator.getAttackRange(this.client.player, attackRange);
        }
        return attackRange;
    }

    @ModifyConstant(method = "updateTargetedEntity(F)V", constant = @Constant(doubleValue = 9.0))
    private double getActualAttackRange1(final double attackRange) {
        if (this.client.player != null) {
            return RangeCalculator.getSquaredAttackRange(this.client.player, attackRange);
        }
        return attackRange;
    }
}
