package jonjar.amgn.mixin;

import jonjar.amgn.entity.PlayerEntityExt;
import jonjar.amgn.entity.ResizedEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityExt {

    private int killCount = 0;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world){
        super(type, world);
    }

    public void addKills(int amount){
        killCount += amount;
        System.out.println("kills : " + killCount);
    }

    // Resize 관련 구문

    @Inject(at = @At("RETURN"), method = "getDimensions", cancellable = true)
    public void getDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> info){
        info.setReturnValue(((ResizedEntity) this).scaleDimensions(info.getReturnValue()));
    }

}
