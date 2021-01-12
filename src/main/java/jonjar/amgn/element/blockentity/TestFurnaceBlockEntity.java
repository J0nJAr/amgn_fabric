package jonjar.amgn.element.blockentity;

import jonjar.amgn.element.screen.TestFurnaceScreenHandler;
import jonjar.amgn.registry.CookingRecipeExample;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;

public class TestFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    //Since we already now the BlockEntityType and RecipeType we don't need them in the constructor's parameters
    public TestFurnaceBlockEntity() {
        super(CookingRecipeExample.TEST_FURNACE_BLOCK_ENTITY, CookingRecipeExample.TEST_RECIPE_TYPE);
    }

    @Override
    public Text getContainerName() {
        //you should use a translation key instead but this is easier
        return Text.of("test furnace");
    }

    @Override
    public ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new TestFurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}