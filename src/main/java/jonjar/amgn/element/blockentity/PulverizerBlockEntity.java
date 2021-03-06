package jonjar.amgn.element.blockentity;

import jonjar.amgn.element.recipe.pulverizer.PulverizerRecipeRegister;
import jonjar.amgn.element.screen.PulverizerScreenHandler;
import jonjar.amgn.registry.ModBlocks;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class PulverizerBlockEntity extends AbstractPulverizerBlockEntity {
    public PulverizerBlockEntity() {
        super(ModBlocks.PULVERIZER_BLOCK_ENTITY, PulverizerRecipeRegister.PULVERIZE_RECIPE_TYPE);
    }

    protected Text getContainerName() {
        return new TranslatableText("container.pulverizer");
    }



    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
       return new PulverizerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
   }
}
