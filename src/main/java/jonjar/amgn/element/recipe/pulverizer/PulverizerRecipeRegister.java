package jonjar.amgn.element.recipe.pulverizer;

import jonjar.amgn.Amgn;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PulverizerRecipeRegister {
    public static RecipeType<PulverizerRecipe> PULVERIZE_RECIPE_TYPE;
    public static RecipeSerializer<PulverizerRecipe> PULVERIZE_RECIPE_SERIALIZER;

    public void registerRecipes(){
        PULVERIZE_RECIPE_SERIALIZER = RecipeSerializer.register(Amgn.MODID+":pulverize", new PulverizerRecipeSerializer(PulverizerRecipe::new, 200));
        PULVERIZE_RECIPE_TYPE  = Registry.register(Registry.RECIPE_TYPE, new Identifier(Amgn.MODID, "pulverize"), new RecipeType<PulverizerRecipe>() {
            @Override
            public String toString() {
                return "pulverize";
            }
        });
    }

}
