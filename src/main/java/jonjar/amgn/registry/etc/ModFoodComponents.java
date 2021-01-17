package jonjar.amgn.registry.etc;

import jonjar.amgn.registry.BaseRegistry;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents extends BaseRegistry {
    public static FoodComponent RICECAKE;

    public void register(){
        RICECAKE = new FoodComponent.Builder().hunger(20).saturationModifier(0.6F).build();
    }
}
