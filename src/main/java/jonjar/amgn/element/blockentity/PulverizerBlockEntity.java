package jonjar.amgn.element.blockentity;

import jonjar.amgn.element.screen.PulverizerScreenHandler;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class PulverizerBlockEntity extends AbstractPulverizerBlockEntity {
    public PulverizerBlockEntity() {
        super(BlockEntityType.FURNACE, RecipeType.SMELTING);
    }

    protected Text getContainerName() {
        return new TranslatableText("container.furnace");
    }



    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
       return new PulverizerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
   }
}
