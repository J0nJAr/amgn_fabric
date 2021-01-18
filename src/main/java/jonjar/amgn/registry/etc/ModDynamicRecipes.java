package jonjar.amgn.registry.etc;

import com.google.gson.JsonObject;
import jonjar.amgn.registry.BaseRegistry;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory;
import net.minecraft.item.Item;
import net.minecraft.recipe.*;
import net.minecraft.tag.ItemTags;

import java.util.ArrayList;

public class ModDynamicRecipes extends BaseRegistry {

    public static ArrayList<JsonObject> FLOWER_TO_DYE_FROM_PULV = null;


    public void register(){
        for(Item flower: ItemTags.FLOWERS.values()){
            RecipeFinder rf = new RecipeFinder();
            if(rf.countRecipeCrafts())

        }
    }
}
