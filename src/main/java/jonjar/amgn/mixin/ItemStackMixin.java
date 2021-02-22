package jonjar.amgn.mixin;

import jonjar.amgn.entity.PlayerEntityExt;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ItemStack.class)
class MixinCancelDurability {
    @Inject(method = "Lnet/minecraft/item/ItemStack;damage(ILjava/util/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z", at = @At(value = "HEAD"), cancellable = true)
    private void cancelDura(int amount, Random random, ServerPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (player != null && ((PlayerEntityExt) player).isExcavating())
            cir.setReturnValue(false);

    }
}