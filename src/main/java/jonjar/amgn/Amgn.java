package jonjar.amgn;

import jonjar.amgn.registry.ModBlocks;
import jonjar.amgn.registry.ModFluids;
import jonjar.amgn.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
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


        ModFluids.registryFluids();//액체 등록
        ModBlocks.registerBlocks();//블럭 등록
        ModItems.registerItems();//아이템 등록
    }
}
