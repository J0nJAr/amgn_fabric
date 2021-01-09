package jonjar.amgn;

import jonjar.amgn.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class Amgn implements ModInitializer {

    public static final String MODID ="amgn";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MODID, "general"),
            () -> new ItemStack(Items.SEA_LANTERN)
    );
    @Override
    public void onInitialize() {

        ModItems.registerItems();//아이템 등록

    }
}
