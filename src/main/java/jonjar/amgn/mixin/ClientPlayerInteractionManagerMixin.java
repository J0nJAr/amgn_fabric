package jonjar.amgn.mixin;

import jonjar.amgn.entity.ResizedEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

    @Shadow @Final private MinecraftClient client;

    @Inject(at = @At("RETURN"), method="getReachDistance", cancellable = true)
    public void getReachDistance(CallbackInfoReturnable<Float> info){

        float scale = ((ResizedEntity) client.player).getScale();

        info.setReturnValue(scale > 1F ? info.getReturnValue() * scale : info.getReturnValue());
    }

}
