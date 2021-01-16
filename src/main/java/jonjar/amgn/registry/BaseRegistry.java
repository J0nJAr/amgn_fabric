package jonjar.amgn.registry;

import jonjar.amgn.registry.ect.ModFeatures;
import jonjar.amgn.registry.ect.ModFoodComponents;
import jonjar.amgn.registry.ect.ModItemTags;

public abstract class BaseRegistry {

    public enum REGISTRIES {
        ITEM(new ModItems()),
        FLUID(new ModFluids()),
        BLOCK(new ModBlocks()),
        ITEM_TAG(new ModItemTags()),
        FOOD_COMPONENT(new ModFoodComponents()),
        FEATURE(new ModFeatures()),
        STATUS_EFFECT(new ModScreen());

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
