package jonjar.amgn.registry;

import jonjar.amgn.Amgn;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems {

    /*
    아이템 만들기
     */

    //아이템
    public static final Item CHOCOLATE_INGOT = new Item(new Item.Settings().group(Amgn.ITEM_GROUP));
    //블럭아이템
    public static final BlockItem CHOCOLATE_BLOCK = new BlockItem(ModBlocks.CHOCOLATE_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP));

    /*
    아이템등록
     */
    public static void registerItems(){
        Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"chocolate_ingot"), CHOCOLATE_INGOT);
        Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"chocolate_block"), CHOCOLATE_BLOCK);
    }
}
