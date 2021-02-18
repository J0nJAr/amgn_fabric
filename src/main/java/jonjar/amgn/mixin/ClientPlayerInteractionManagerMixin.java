package jonjar.amgn.mixin;

import jonjar.amgn.util.RangeCalculator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerInteractionManager.class)
abstract class ClientPlayerInteractionManagerMixin {
    @Shadow @Final private MinecraftClient client;

    @ModifyConstant(
            method = "getReachDistance()F",
            require = 2, allow = 2, constant = { @Constant(floatValue = 5.0F), @Constant(floatValue = 4.5F) })
    private float getActualReachDistance(final float reachDistance) {
        if (this.client.player != null) {
            // TODO Warn on loss of precision if present?
            float f = (float) RangeCalculator.getReachDistance(this.client.player, reachDistance);
            return f;
        }
        return reachDistance;
    }
}
