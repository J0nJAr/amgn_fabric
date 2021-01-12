package jonjar.amgn.registry;

import jonjar.amgn.Amgn;
import jonjar.amgn.element.blockentity.TestFurnace;
import jonjar.amgn.element.blockentity.TestFurnaceBlockEntity;
import jonjar.amgn.element.recipe.TestRecipe;
import jonjar.amgn.element.screen.TestFurnaceScreenHandler;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CookingRecipeExample  {
    public static final Block TEST_FURNACE_BLOCK;
    public static final BlockEntityType TEST_FURNACE_BLOCK_ENTITY;
    public static final RecipeType<TestRecipe> TEST_RECIPE_TYPE;
    public static final ScreenHandlerType<TestFurnaceScreenHandler> TEST_FURNACE_SCREEN_HANDLER;
    public static final RecipeSerializer<TestRecipe> TEST_RECIPE_SERIALIZER;

    public static void registerRecipes(){};
    static {
        //Block
        TEST_FURNACE_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Amgn.MODID, "test_furnace"), new TestFurnace(FabricBlockSettings.of(Material.METAL)));
        //BlockItem
        Registry.register(Registry.ITEM, new Identifier(Amgn.MODID, "test_furnace"), new BlockItem(TEST_FURNACE_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
        //BlockEntity
        TEST_FURNACE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Amgn.MODID, "test_furnace"), BlockEntityType.Builder.create(TestFurnaceBlockEntity::new, TEST_FURNACE_BLOCK).build(null));
        TEST_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(Amgn.MODID, "test_furnace"), new RecipeType<TestRecipe>() {
            @Override
            public String toString() {return "test_furnace";}
        });
        TEST_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Amgn.MODID, "test_furnace"), TestFurnaceScreenHandler::new);

        TEST_RECIPE_SERIALIZER = RecipeSerializer.register(Amgn.MODID, "test_furnace", new CookingRecipeSerializer(TestRecipe::new ,200));

    }
}
