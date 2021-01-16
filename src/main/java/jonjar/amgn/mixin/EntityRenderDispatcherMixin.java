package jonjar.amgn.mixin;

import jonjar.amgn.entity.ResizedEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Environment(EnvType.CLIENT)
@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderDispatcher;renderShadow(" +
            "Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;" +
            "Lnet/minecraft/entity/Entity;" +
            "FFLnet/minecraft/world/WorldView;F)V"), method = "render", index = 6)
    public float renderShadow(MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, Entity entity, float darkness, float tickDelta, WorldView world, float size){
        if(entity instanceof ResizedEntity){
            return ((ResizedEntity) entity).getScale() * size;
        } else {
            return size;
        }
    }

}
