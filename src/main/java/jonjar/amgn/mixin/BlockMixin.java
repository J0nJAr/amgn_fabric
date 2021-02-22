package jonjar.amgn.mixin;

import jonjar.amgn.entity.PlayerEntityExt;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Block.class)
class MixinBlock {

    @Redirect(method = "afterBreak", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addExhaustion(F)V"))
    private void cancelExhaustion(PlayerEntity player, float exhaustion) {
        if (((PlayerEntityExt) player).isExcavating()) {
            player.addExhaustion(exhaustion);
            return;
        }
    }
}
