package jonjar.amgn.registry;

import jonjar.amgn.registry.ect.ModFeatures;
import jonjar.amgn.registry.ect.ModFoodComponents;
import jonjar.amgn.registry.ect.ModItemTags;

public abstract class BaseRegistry {

    public enum REGISTRIES {
        FLUID(new ModFluids()),
        BLOCK(new ModBlocks()),
        ITEM_TAG(new ModItemTags()),
        FOOD_COMPONENT(new ModFoodComponents()),
        ITEM(new ModItems()),
        FEATURE(new ModFeatures()),
        SCREEN(new ModScreen()),
        STATUS_EFFECT(new ModStatusEffects());

        private final BaseRegistry br;

        REGISTRIES(BaseRegistry br){
            this.br = br;
        }

        public BaseRegistry getBaseRegistry(){
            return this.br;
        }
    }

    public static void registerAll(){
        for(REGISTRIES r : REGISTRIES.values())
            r.getBaseRegistry().register();
    }

    public abstract void register();

}
