package jonjar.amgn.element.recipe.pulverizer;

import jonjar.amgn.registry.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class PulverizerRecipe extends AbstractPulverizerRecipe {

    public PulverizerRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int pulverizeTime,int level) {
        super(PulverizerRecipeRegister.PULVERIZE_RECIPE_TYPE, id, group, input, output, experience, pulverizeTime,level);
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(ModBlocks.PULVERIZER_BLOCK);
    }

    public RecipeSerializer<?> getSerializer() {
        return PulverizerRecipeRegister.PULVERIZE_RECIPE_SERIALIZER;
    }
}
