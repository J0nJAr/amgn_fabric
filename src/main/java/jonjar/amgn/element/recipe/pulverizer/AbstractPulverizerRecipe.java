package jonjar.amgn.element.recipe.pulverizer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public abstract class AbstractPulverizerRecipe implements Recipe<Inventory> {

    protected final RecipeType<?> type;
    protected final Identifier id;
    protected final String group;
    protected final Ingredient input;
    protected final ItemStack output;
    protected final float experience;
    protected final int pulverizeTime;
    protected final int level;

    public AbstractPulverizerRecipe(RecipeType<?> type, Identifier id, String group, Ingredient input, ItemStack output, float experience, int pulverizeTime,int level) {
        this.type = type;
        this.id = id;
        this.group = group;
        this.input = input;
        this.output = output;
        this.experience = experience;
        this.pulverizeTime = pulverizeTime;
        this.level = level;
    }

    public boolean matches(Inventory inv, World world) {
        return this.input.test(inv.getStack(0));
    }

    public ItemStack craft(Inventory inv) {
        return this.output.copy();
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int width, int height) {
        return true;
    }

    public DefaultedList<Ingredient> getPreviewInputs() {
        DefaultedList<Ingredient> defaultedList = DefaultedList.of();
        defaultedList.add(this.input);
        return defaultedList;
    }

    public float getExperience() {
        return this.experience;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    @Environment(EnvType.CLIENT)
    public String getGroup() {
        return this.group;
    }

    public int getPulverizeTime() {
        return this.pulverizeTime;
    }

    public int getLevel(){
        return this.level;
    }

    public Identifier getId() {
        return this.id;
    }

    public RecipeType<?> getType() {
        return this.type;
    }
}