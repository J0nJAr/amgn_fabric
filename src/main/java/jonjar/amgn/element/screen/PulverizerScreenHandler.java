package jonjar.amgn.element.screen;

import jonjar.amgn.element.blockentity.AbstractPulverizerBlockEntity;
import jonjar.amgn.element.recipe.pulverizer.AbstractPulverizerRecipe;
import jonjar.amgn.element.recipe.pulverizer.PulverizerRecipe;
import jonjar.amgn.element.recipe.pulverizer.PulverizerRecipeRegister;
import jonjar.amgn.registry.ModScreen;
import jonjar.amgn.registry.ect.ModItemTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

public class PulverizerScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    protected final World world;
    private final RecipeType<? extends AbstractPulverizerRecipe> recipeType;

    protected PulverizerScreenHandler(ScreenHandlerType<PulverizerScreenHandler> type, RecipeType<PulverizerRecipe> recipeType, int i, PlayerInventory playerInventory) {
        this(type, recipeType, i, playerInventory, new SimpleInventory(4), new ArrayPropertyDelegate(4));
    }

    protected PulverizerScreenHandler(ScreenHandlerType<PulverizerScreenHandler> type, RecipeType<? extends AbstractPulverizerRecipe> recipeType, int i, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(type, i);
        this.recipeType = recipeType;
        checkSize(inventory, 4);
        checkDataCount(propertyDelegate, 4);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.world = playerInventory.player.world;
        this.addSlot(new Slot(inventory, 0, 56, 17));
        this.addSlot(new Slot(inventory, 1, 56, 53));
        this.addSlot(new Slot(inventory, 2, 116, 35));
        this.addSlot(new Slot(inventory, 3, 20, 35));

        int l;
        for(l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, 84 + l * 18));
            }
        }

        for(l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 142));
        }

        this.addProperties(propertyDelegate);
    }

    public PulverizerScreenHandler(int i, PlayerInventory playerInventory) {
        this(ModScreen.PULVERIZER_SCREEN_HANDLER, PulverizerRecipeRegister.PULVERIZE_RECIPE_TYPE,i,playerInventory);
    }
    public PulverizerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        this(ModScreen.PULVERIZER_SCREEN_HANDLER,PulverizerRecipeRegister.PULVERIZE_RECIPE_TYPE,syncId, playerInventory, inventory, propertyDelegate);
    }


    public void populateRecipeFinder(RecipeFinder finder) {
        if (this.inventory instanceof RecipeInputProvider) {
            ((RecipeInputProvider)this.inventory).provideRecipeInputs(finder);
        }

    }

    public void clearCraftingSlots() {
        this.inventory.clear();
    }


    public boolean matches(Recipe<? super Inventory> recipe) {
        return recipe.matches(this.inventory, this.world);
    }

    public int getCraftingResultSlotIndex() {
        return 2;
    }

    public int getCraftingWidth() {
        return 1;
    }

    public int getCraftingHeight() {
        return 1;
    }


    @Environment(EnvType.CLIENT)
    public int getCraftingSlotCount() {
        return 3;
    }

    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();

            if (index == 2) {
                if (!this.insertItem(itemStack2, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onStackChanged(itemStack2, itemStack);
            } else if (index != 1 && index != 0 && index != 3) {
                if (this.isSmeltable(itemStack2)) {
                    if (!this.insertItem(itemStack2, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(itemStack2)) {
                    if (!this.insertItem(itemStack2, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (ModItemTags.BLADE.contains(itemStack2.getItem())) {
                    if (!this.insertItem(itemStack2, 3, 4, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 4 && index < 31) {
                    if (!this.insertItem(itemStack2, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 31 && index < 40 && !this.insertItem(itemStack2, 4, 40, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    protected boolean isSmeltable(ItemStack itemStack) {
        return this.world.getRecipeManager().getFirstMatch(this.recipeType, new SimpleInventory(new ItemStack[]{itemStack}), this.world).isPresent();
    }

    protected boolean isFuel(ItemStack itemStack) {
        return AbstractPulverizerBlockEntity.canUseAsFuel(itemStack);
    }

    @Environment(EnvType.CLIENT)
    public int getCookProgress() {
        int i = this.propertyDelegate.get(2);
        int j = this.propertyDelegate.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    @Environment(EnvType.CLIENT)
    public int getFuelProgress() {
        int i = this.propertyDelegate.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.propertyDelegate.get(0) * 13 / i;
    }

    @Environment(EnvType.CLIENT)
    public boolean isBurning() {
        return this.propertyDelegate.get(0) > 0;
    }


}