package jonjar.amgn.registry;

import jonjar.amgn.Amgn;
import jonjar.amgn.base.BaseStatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModStatusEffects extends BaseRegistry{

    public static BaseStatusEffect SHRINK_EFFECT;
    public static BaseStatusEffect GROW_EFFECT;

    public void register(){
        SHRINK_EFFECT = effect("shrink", 0xEAEAEA);
        GROW_EFFECT = effect("grow", 0x1DDB16);
    }

    public BaseStatusEffect effect(String name, int color){
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(Amgn.MODID, name), new BaseStatusEffect(StatusEffectType.NEUTRAL, color));
    }

}
