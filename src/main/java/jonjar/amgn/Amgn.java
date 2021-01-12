package jonjar.amgn;

import jonjar.amgn.registry.ModBlocks;
import jonjar.amgn.registry.ModFluids;
import jonjar.amgn.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.impl.registry.sync.FabricRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.lang.reflect.Field;
import java.util.Iterator;

public class Amgn implements ModInitializer {

    public static final String MODID ="amgn";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MODID, "general"),
            () -> new ItemStack(Items.SEA_LANTERN)
    );

    @Override
    public void onInitialize() {

        for(Block b : Registry.BLOCK){
            if(b.getTranslationKey().contains("minecraft")){
                try{
                    Class c = Class.forName("net.minecraft.block.AbstractBlock");
                    Field field = c.getDeclaredField("slipperiness");
                    field.setAccessible(true);
                    field.set(b, 1.11F);
                    field.setAccessible(false);
                } catch(Exception e){
                    e.printStackTrace();
                }

            }

        }


        ModFluids.registryFluids();//액체 등록
        ModBlocks.registerBlocks();//블럭 등록
        ModItems.registerItems();//아이템 등록
    }
}
