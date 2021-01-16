package jonjar.amgn.mixin;

import jonjar.amgn.entity.ResizedEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Environment(EnvType.CLIENT)
@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;scale(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/util/math/MatrixStack;F)V"),
    method = "render", index = 1)
    public MatrixStack scale(LivingEntity livingEntity, MatrixStack ms, float amount){
        float scale = ((ResizedEntity) livingEntity).getScale();
        ms.scale(scale, scale, scale);
        return ms;
    }

}
