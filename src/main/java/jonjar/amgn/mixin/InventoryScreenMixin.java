package jonjar.amgn.mixin;

import jonjar.amgn.entity.ResizedEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Environment(EnvType.CLIENT)
@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

    @ModifyVariable(at = @At("HEAD"), method = "drawEntity", argsOnly = true, index = 2)
    private static int drawEntity(int initial, int x, int y, int size, float mouseX, float mouseY, LivingEntity entity){
        if(entity instanceof ResizedEntity){
            return (int) (initial / ((ResizedEntity) entity).getScale());
        } else {
            return initial;
        }
    }

}
