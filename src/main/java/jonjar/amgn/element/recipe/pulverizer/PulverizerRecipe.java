package jonjar.amgn.element.recipe.pulverizer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class PulverizerRecipe extends AbstractPulverizerRecipe {

    public PulverizerRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(PulverizerRecipeRegister.PULVERIZE_RECIPE_TYPE, id, group, input, output, experience, cookTime);
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(Blocks.BLAST_FURNACE);
    }

    public RecipeSerializer<?> getSerializer() {
        return PulverizerRecipeRegister.PULVERIZE_RECIPE_SERIALIZER;
    }
}
