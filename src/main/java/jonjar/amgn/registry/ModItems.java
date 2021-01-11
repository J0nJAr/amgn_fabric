package jonjar.amgn.registry;

import jonjar.amgn.Amgn;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems {

    /*
    아이템 만들기
     */

    //아이템
    public static Item CHOCOLATE_INGOT;
    public static Item SLIME_INGOT;
    public static final Item RICECAKE_INGOT = new Item(new Item.Settings().group(Amgn.ITEM_GROUP));

    // 양동이
    public static BucketItem SLIME_BUCKET;
    public static Item  CHOCOLATE_BUCKET;

    //블럭아이템
    public static BlockItem CHOCOLATE_BLOCK;
    public static BlockItem COMPRESSED_SLIME_BLOCK;
    public static final BlockItem COMPRESSED_RICECAKE_BLOCK = new BlockItem(ModBlocks.COMPRESSED_RICECAKE_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP));

    /*
    아이템등록
     */
    public static void registerItems(){
        CHOCOLATE_INGOT=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"chocolate_ingot"), new Item(new Item.Settings().group(Amgn.ITEM_GROUP)));
        SLIME_INGOT= Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"chocolate_block"), new Item(new Item.Settings().group(Amgn.ITEM_GROUP)));

        Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"ricecake_ingot"), RICECAKE_INGOT);
        Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"compressed_ricecake_block"), COMPRESSED_RICECAKE_BLOCK);

        CHOCOLATE_BLOCK=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"slime_ingot"), new BlockItem(ModBlocks.CHOCOLATE_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP)));
        COMPRESSED_SLIME_BLOCK=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"compressed_slime_block"), new BlockItem(ModBlocks.COMPRESSED_SLIME_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP)));

        CHOCOLATE_BUCKET = Registry.register(Registry.ITEM, new Identifier(Amgn.MODID, "chocolate_bucket"), new BucketItem(ModFluids.STILL_MOLTEN_CHOCOLATE, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(Amgn.ITEM_GROUP)));
        SLIME_BUCKET = Registry.register(Registry.ITEM, new Identifier(Amgn.MODID, "slime_bucket"), new BucketItem(ModFluids.STILL_SLIME, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(Amgn.ITEM_GROUP)));
    }
}
