package jonjar.amgn.mixin;

import jonjar.amgn.entity.ResizedEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(Camera.class)
public abstract class CameraMixin {


    @Shadow private Entity focusedEntity;

    @Shadow protected abstract double clipToSpace(double desiredCameraDistance);

    @Redirect(method = "update", at= @At(value="INVOKE", target = "Lnet/minecraft/client/render/Camera;clipToSpace(D)D"))
    public double clipToSpace(Camera obj, double distance){
        float scale = focusedEntity instanceof ResizedEntity ? ((ResizedEntity) focusedEntity).getScale() : 1F;
        if(scale < 1F)
            scale = 1F;
        return clipToSpace(distance * scale);
    }

}
