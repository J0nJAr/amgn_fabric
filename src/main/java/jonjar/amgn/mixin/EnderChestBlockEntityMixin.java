package jonjar.amgn.mixin;

import jonjar.amgn.util.RangeCalculator;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Tickable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EnderChestBlockEntity.class)
abstract class EnderChestBlockEntityMixin extends BlockEntity implements Tickable {
    EnderChestBlockEntityMixin(final BlockEntityType<?> type) {
        super(type);
    }

    @ModifyConstant(
            method = "canPlayerUse(Lnet/minecraft/entity/player/PlayerEntity;)Z",
            require = 1, allow = 1, constant = @Constant(doubleValue = 64.0))
    private static double getActualReachDistance(final double reachDistance, final PlayerEntity player) {
        return RangeCalculator.getSquaredReachDistance(player, reachDistance);
    }
}