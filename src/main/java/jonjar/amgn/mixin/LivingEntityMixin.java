package jonjar.amgn.mixin;

import jonjar.amgn.Amgn;
import jonjar.amgn.element.item.AntiSlipperyArmor;
import jonjar.amgn.element.item.SlipperyArmor;
import jonjar.amgn.entity.PlayerEntityExt;
import jonjar.amgn.entity.ResizedEntity;
import jonjar.amgn.registry.ModAttributes;
import jonjar.amgn.registry.ModStatusEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ResizedEntity {

    @Shadow @Final private DefaultedList<ItemStack> equippedArmor;

    public LivingEntityMixin(EntityType<?> type, World world){
        super(type, world);
    }

    @Inject(
            method = "createLivingAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;",
            require = 1, allow = 1, at = @At("RETURN"))
    private static void addAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
        final DefaultAttributeContainer.Builder builder = info.getReturnValue();
        builder.add(ModAttributes.REACH);
        builder.add(ModAttributes.ATTACK_RANGE);
    }


    @Inject(method = "onDeath", at = @At(("HEAD")))
    public void onDeath(DamageSource source, CallbackInfo ci){
        Entity attacker = source.getAttacker();
        if(attacker instanceof PlayerEntity){
            ((PlayerEntityExt) attacker).addKills(1);
        }
    }

    @Inject(method="tickMovement", at=@At("TAIL"))
    public void tickMovement(CallbackInfo ci){
        Vec3d vec3d = this.getVelocity();
        if(!this.isOnGround() && vec3d.y < 0.0D){
            if(Amgn.TOGGLED_GRAVITY)
                this.setVelocity(vec3d.multiply(1.0D, 5.0D, 1.0D));
        }
    }

    @Inject(method="computeFallDamage(FF)I", at = @At("HEAD"), cancellable=true)
    public void computeFallDamage(float distance, float damageMultiplier, CallbackInfoReturnable<Integer> info){
        float size = ((ResizedEntity) this).getScale();
        if(size > 1.0F){
            info.setReturnValue((int) (distance - size*4));
        }
        if(Amgn.TOGGLED_GRAVITY)
            info.setReturnValue(100);

    }



    // Resize 관련 구문

    @Shadow @Nullable public abstract EntityAttributeInstance getAttributeInstance(EntityAttribute attribute);

    @Shadow public abstract Map<StatusEffect, StatusEffectInstance> getActiveStatusEffects();

    @Shadow public abstract void equipStack(EquipmentSlot slot, ItemStack stack);

    @Shadow public abstract void sendPickup(Entity item, int count);

    @Unique
    private static final UUID SCALED_SPEED_ID = UUID.fromString("c5267238-6a78-4257-ae83-a2a5e34c1128");

    @Unique
    private static final UUID REACH_ID = UUID.fromString("fe4cea66-7169-11eb-9439-0242ac130002");

    @Unique
    private static final UUID ATTACK_RANGE_ID = UUID.fromString("12fa50c0-716a-11eb-9439-0242ac130002");

    @Unique
    private static final TrackedData<Float> SCALE = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.FLOAT);

    @Override
    public float getScale(){
        return this.getDataTracker().get(SCALE);
    }

    private float computeScale(){
        // StatusEffect = 포션 효과
        Map<StatusEffect, StatusEffectInstance> effects = getActiveStatusEffects();
        if(effects != null && effects.size() > 0){      // 플레이어에게 적용된 포션효과가 있다면

            // 포션 효과 레벨 구한 뒤 사이즈 연산
            int shrink = effects.get(ModStatusEffects.SHRINK_EFFECT) != null ? effects.get(ModStatusEffects.SHRINK_EFFECT).getAmplifier() + 1 : 0;
            int grow = effects.get(ModStatusEffects.GROW_EFFECT) != null ? effects.get(ModStatusEffects.GROW_EFFECT).getAmplifier() + 1 : 0;
            float value = grow - shrink;

            // 계산식 : 2^value
            if(value > 0)
                return (float) 1F + value;
            else
                return (float) 1F + (value * 0.05F);
        } else {
            return 1F; // 기본 사이즈 = 1F
        }
    }

    // EntityDimensions = 히트 박스 모듈
    @Override
    public EntityDimensions scaleDimensions(EntityDimensions dimensions){
        float scale = getScale();
        if(scale < 0.2F)
            scale = 0.2F;
        return new EntityDimensions(dimensions.width * scale, dimensions.height * scale, dimensions.fixed);
    }

    // 히트박스 계산 후 리턴 변경
    @Inject(at = @At("RETURN"), method = "getDimensions", cancellable = true)
    public void getDimensions(EntityPose pose, CallbackInfoReturnable<EntityDimensions> info){
        info.setReturnValue(scaleDimensions(info.getReturnValue()));
    }


    // 잘 때는 제외?
    @Inject(at = @At("RETURN"), method="getEyeHeight", cancellable = true)
    public void getEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> info){
        if(pose != EntityPose.SLEEPING){
            float scale = getScale();
            if(scale < 0.2F) scale = 0.2F;
            info.setReturnValue(info.getReturnValue() * getScale());
        }
    }

    // 점프 높이 수정
    @Inject(at = @At("RETURN"), method = "getJumpVelocity", cancellable = true)
    public void getJumpVelocity(CallbackInfoReturnable<Float> info){
        float f = 0.42F * this.getJumpVelocityMultiplier();
        float n = info.getReturnValue() * (float) Math.pow(getScale(), 0.4F);
        if(n < f) n = f;
        info.setReturnValue(n);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo info){
        float scale = computeScale();
        if(!this.getEntityWorld().isClient()){
            if(this.getDataTracker().get(SCALE) != scale){
                this.getDataTracker().set(SCALE, computeScale());
            }
        }

        EntityAttributeInstance ea = getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if(ea.getModifier(SCALED_SPEED_ID) != null){
            ea.removeModifier(SCALED_SPEED_ID);
        }

        ea.addPersistentModifier(new EntityAttributeModifier(SCALED_SPEED_ID,
                "Resized speed multiplier",
                Math.pow(getScale(), 0.4) - 1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));


        if(scale / 2F != this.stepHeight)
            this.stepHeight = scale/ 2F;

        if(scale > 3F){
            for(Entity el : world.getOtherEntities((Entity) this, getBoundingBox())){
                if(el instanceof LivingEntity){

                    if(el.getHeight() * 4 < scale){
                        el.damage(DamageSource.mob((LivingEntity) ((Entity) this)), scale * 2);
                    }

                } else if(el instanceof ItemEntity && (((Entity) this) instanceof PlayerEntity)){
                    ItemEntity ie = (ItemEntity) el;
                    ie.onPlayerCollision(((PlayerEntity) ((Entity) this)));

                }
            }
        }
    }

    // TODO : DataTracker = 서버 종료 후에도 저장되는 값?
    @Inject(at = @At("RETURN"), method = "initDataTracker")
    public void initDataTracker(CallbackInfo ci){
        this.getDataTracker().startTracking(SCALE, 1F);
    }

    @Inject(at = @At("RETURN"), method = "onTrackedDataSet")
    public void onTrackedDataSet(TrackedData<?> data, CallbackInfo ci){
        if(SCALE.equals(data)){
            this.calculateDimensions();
        }
    }

    @Inject(at = @At("RETURN"), method="getMovementSpeed()F", cancellable = true)
    public void getMovementSpeed(CallbackInfoReturnable<Float> cir){
        cir.setReturnValue(cir.getReturnValue() * getScale());
    }

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"))
    public float antiSlippery(Block block) {
        float t = block.getSlipperiness();

        if((Object)this instanceof PlayerEntity){
            PlayerEntity pe = (PlayerEntity) (Object) this;
            for (ItemStack is : pe.getArmorItems()){
                if(is.getItem() instanceof AntiSlipperyArmor) {
                    t=0.6f;
                    break;
                }else if(is.getItem() instanceof SlipperyArmor) {
                    t= t >0.7f ? 1.0989011f : 0.99f;
                    break;
                }

            }
        }
        return t;
    }
}
