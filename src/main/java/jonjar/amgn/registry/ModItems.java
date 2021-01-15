package jonjar.amgn.registry;

import jonjar.amgn.Amgn;
import jonjar.amgn.registry.ect.ModFoodComponents;
import net.minecraft.entity.EquipmentSlot;
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
    public static final Item RICECAKE_INGOT = new Item(new Item.Settings().group(Amgn.ITEM_GROUP).food(ModFoodComponents.RICECAKE));
    public static Item IRON_BLADE;
    public static Item DIAMOND_BLADE;
    public static Item WOOL_BOOTS;

    // 양동이
    public static BucketItem SLIME_BUCKET;
    public static Item  CHOCOLATE_BUCKET;

    //블럭아이템
    public static BlockItem CHOCOLATE_BLOCK;
    public static BlockItem COMPRESSED_SLIME_BLOCK;
    public static final BlockItem COMPRESSED_RICECAKE_BLOCK = new BlockItem(ModBlocks.COMPRESSED_RICECAKE_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP));
    public static BlockItem PULVERIZER;
    public static BlockItem TEST;
    /*
    아이템등록
     */
    public static void registerItems(){
        CHOCOLATE_INGOT=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"chocolate_ingot"), new Item(new Item.Settings().group(Amgn.ITEM_GROUP)));
        SLIME_INGOT= Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"slime_ingot"), new Item(new Item.Settings().group(Amgn.ITEM_GROUP)));
        WOOL_BOOTS = Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"wool_boots"), new ArmorItem(ArmorMaterials.NETHERITE, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT).fireproof()));

        Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"ricecake_ingot"), RICECAKE_INGOT);
        Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"compressed_ricecake_block"), COMPRESSED_RICECAKE_BLOCK);

        IRON_BLADE= Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"iron_blade"), new Item(new Item.Settings().maxCount(1).maxDamage(328).group(Amgn.ITEM_GROUP)));
        DIAMOND_BLADE= Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"diamond_blade"), new Item(new Item.Settings().maxCount(1).maxDamage(328).group(Amgn.ITEM_GROUP)));

        CHOCOLATE_BLOCK=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"chocolate_block"), new BlockItem(ModBlocks.CHOCOLATE_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP)));
        COMPRESSED_SLIME_BLOCK=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"compressed_slime_block"), new BlockItem(ModBlocks.COMPRESSED_SLIME_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP)));

        CHOCOLATE_BUCKET = Registry.register(Registry.ITEM, new Identifier(Amgn.MODID, "chocolate_bucket"), new BucketItem(ModFluids.STILL_MOLTEN_CHOCOLATE, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(Amgn.ITEM_GROUP)));
        SLIME_BUCKET = Registry.register(Registry.ITEM, new Identifier(Amgn.MODID, "slime_bucket"), new BucketItem(ModFluids.STILL_SLIME, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(Amgn.ITEM_GROUP)));

        PULVERIZER=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"pulverizer"), new BlockItem(ModBlocks.PULVERIZER_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP)));
        TEST=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"test"), new BlockItem(ModBlocks.TEST, new Item.Settings().group(Amgn.ITEM_GROUP)));
    }
}
