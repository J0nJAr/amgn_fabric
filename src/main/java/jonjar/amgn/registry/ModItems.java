package jonjar.amgn.registry;

import jonjar.amgn.Amgn;
import jonjar.amgn.element.item.AntiSlipperyArmor;
import jonjar.amgn.element.item.BladeItem;
import jonjar.amgn.element.item.SlipperyArmor;
import jonjar.amgn.registry.etc.ModFoodComponents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems extends BaseRegistry {

    /*
    아이템 만들기
     */

    //아이템
    public static Item CHOCOLATE_INGOT;
    public static Item SLIME_INGOT;
    public static Item RICECAKE_INGOT;
    public static Item IRON_BLADE;
    public static Item DIAMOND_BLADE;
    public static Item GOLD_BLADE;
    public static Item WOOL_BOOTS;
    public static Item ICE_BOOTS;

    // 양동이
    public static BucketItem SLIME_BUCKET;
    public static Item  CHOCOLATE_BUCKET;

    //블럭아이템
    public static BlockItem CHOCOLATE_BLOCK;
    public static BlockItem COMPRESSED_SLIME_BLOCK;
    public static BlockItem COMPRESSED_RICECAKE_BLOCK;
    public static BlockItem PULVERIZER;
    public static BlockItem TEST;
    /*
    아이템등록
     */
    public void register(){
        CHOCOLATE_INGOT=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"chocolate_ingot"), new Item(new Item.Settings().group(Amgn.ITEM_GROUP)));
        SLIME_INGOT= Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"slime_ingot"), new Item(new Item.Settings().group(Amgn.ITEM_GROUP)));
        WOOL_BOOTS = Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"wool_boots"), new AntiSlipperyArmor(ArmorMaterials.LEATHER, EquipmentSlot.FEET, (new Item.Settings()).group(Amgn.ARMOR_GROUP)));
        ICE_BOOTS= Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"ice_boots"), new SlipperyArmor(ArmorMaterials.IRON, EquipmentSlot.FEET, (new Item.Settings()).group(Amgn.ARMOR_GROUP)));

        RICECAKE_INGOT = Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"ricecake_ingot"), new Item(new Item.Settings().group(Amgn.ITEM_GROUP).food(ModFoodComponents.RICECAKE)));
        COMPRESSED_RICECAKE_BLOCK = Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"compressed_ricecake_block"), new BlockItem(ModBlocks.COMPRESSED_RICECAKE_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP)));

        IRON_BLADE = Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"iron_blade"), new BladeItem(2,1,new Item.Settings().maxCount(16).maxDamage(64).group(Amgn.ITEM_GROUP)));
        DIAMOND_BLADE = Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"diamond_blade"), new BladeItem(4,2,new Item.Settings().maxCount(16).maxDamage(256).group(Amgn.ITEM_GROUP)));
        GOLD_BLADE = Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"gold_blade"), new BladeItem(8,0,new Item.Settings().maxCount(16).maxDamage(32).group(Amgn.ITEM_GROUP)));

        CHOCOLATE_BLOCK=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"chocolate_block"), new BlockItem(ModBlocks.CHOCOLATE_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP)));
        COMPRESSED_SLIME_BLOCK=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"compressed_slime_block"), new BlockItem(ModBlocks.COMPRESSED_SLIME_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP)));

        CHOCOLATE_BUCKET = Registry.register(Registry.ITEM, new Identifier(Amgn.MODID, "chocolate_bucket"), new BucketItem(ModFluids.STILL_MOLTEN_CHOCOLATE, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(Amgn.ITEM_GROUP)));
        SLIME_BUCKET = Registry.register(Registry.ITEM, new Identifier(Amgn.MODID, "slime_bucket"), new BucketItem(ModFluids.STILL_SLIME, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(Amgn.ITEM_GROUP)));

        PULVERIZER=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"pulverizer"), new BlockItem(ModBlocks.PULVERIZER_BLOCK, new Item.Settings().group(Amgn.ITEM_GROUP)));
        TEST=Registry.register(Registry.ITEM,new Identifier(Amgn.MODID,"test"), new BlockItem(ModBlocks.TEST, new Item.Settings().group(Amgn.ITEM_GROUP)));
    }
}
