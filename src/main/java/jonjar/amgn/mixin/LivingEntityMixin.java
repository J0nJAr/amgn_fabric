package jonjar.amgn.mixin;

import jonjar.amgn.entity.PlayerEntityExt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow @Final private DefaultedList<ItemStack> equippedArmor;

    @Shadow public abstract void travel(Vec3d movementInput);

    @Shadow public abstract Vec3d method_26318(Vec3d vec3d, float f);

    public LivingEntityMixin(EntityType<?> type, World world){
        super(type, world);
    }

    @Inject(method = "onDeath", at = @At(("HEAD")))
    public void onDeath(DamageSource source, CallbackInfo ci){
        Entity attacker = source.getAttacker();
        if(attacker instanceof PlayerEntity){
            ((PlayerEntityExt) attacker).addKills(1);
        }
    }
//    @Inject(at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/LivingEntity;travel(Lnet/minecraft/util/math/Vec3d;)V",shift = At.Shift.AFTER),method = "travel")
//    public void travel(Vec3d movementInput, CallbackInfo ci) {
//        Vec3d vec3d7;
//        BlockPos blockPos = this.getVelocityAffectingPos();
//
//        float t = this.world.getBlockState(blockPos).getBlock().getSlipperiness();
//        if((Object)this instanceof PlayerEntity){
//            PlayerEntity pe = (PlayerEntity) (Object) this;
//            for (ItemStack is : pe.getArmorItems()){
//                if(is.getItem() instanceof AntiSlipperyArmor) {
//                    t=1.0f;
//                }
//            }
//        }
//    }
}
