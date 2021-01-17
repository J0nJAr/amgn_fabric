package jonjar.amgn.element.recipe.pulverizer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

public class PulverizerRecipeSerializer<T extends AbstractPulverizerRecipe> implements RecipeSerializer<T> {

    private final int pulverizeTime;
    private final int level;
    private final PulverizerRecipeSerializer.RecipeFactory<T> recipeFactory;

    public PulverizerRecipeSerializer(PulverizerRecipeSerializer.RecipeFactory<T> recipeFactory, int pulverizeTime,int level) {
        this.pulverizeTime = pulverizeTime;
        this.recipeFactory = recipeFactory;
        this.level = level;
    }

    @Override
    public T read(Identifier identifier, JsonObject jsonObject) {
        String string = JsonHelper.getString(jsonObject, "group", "");
        JsonElement jsonElement = JsonHelper.hasArray(jsonObject, "ingredient") ? JsonHelper.getArray(jsonObject, "ingredient") : JsonHelper.getObject(jsonObject, "ingredient");
        Ingredient ingredient = Ingredient.fromJson((JsonElement)jsonElement);
        String string2 = JsonHelper.getString(jsonObject, "result");
        Identifier identifier2 = new Identifier(string2);
        ItemStack itemStack = new ItemStack((ItemConvertible)Registry.ITEM.getOrEmpty(identifier2).orElseThrow(() -> {
            return new IllegalStateException("Item: " + string2 + " does not exist");
        }));
        float f = JsonHelper.getFloat(jsonObject, "experience", 0.0F);
        int i = JsonHelper.getInt(jsonObject, "pulverizetime", this.pulverizeTime);
        int l = JsonHelper.getInt(jsonObject, "level", this.level);

        return this.recipeFactory.create(identifier, string, ingredient, itemStack, f, i,l);
    }

    public T read(Identifier identifier, PacketByteBuf packetByteBuf) {
        String string = packetByteBuf.readString(32767);
        Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
        ItemStack itemStack = packetByteBuf.readItemStack();
        float f = packetByteBuf.readFloat();
        int i = packetByteBuf.readVarInt();

        int l = packetByteBuf.readVarInt();

        return this.recipeFactory.create(identifier, string, ingredient, itemStack, f, i, l);
    }


    public void write(PacketByteBuf packetByteBuf, T AbstractPulverizerRecipe) {
        packetByteBuf.writeString(AbstractPulverizerRecipe.group);
        AbstractPulverizerRecipe.input.write(packetByteBuf);
        packetByteBuf.writeItemStack(AbstractPulverizerRecipe.output);
        packetByteBuf.writeFloat(AbstractPulverizerRecipe.experience);
        packetByteBuf.writeVarInt(AbstractPulverizerRecipe.pulverizeTime);
    }

    interface RecipeFactory<T extends AbstractPulverizerRecipe> {
        T create(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime,int level);
    }
}
