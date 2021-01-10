package jonjar.amgn.registry;


import jonjar.amgn.Amgn;
import jonjar.amgn.fluid.ChocolateFluid;
import jonjar.amgn.fluid.SlimeFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;



public class ModFluids {

    public static FlowableFluid STILL_MOLTEN_CHOCOLATE;
    public static FlowableFluid FLOWING_CHOCOLATE;

    public static FlowableFluid STILL_SLIME;
    public static FlowableFluid FLOWING_SLIME;

    public static Item  CHOCOLATE_BUCKET;


    public static void registryFluids()
    {
        STILL_SLIME = Registry.register(Registry.FLUID, new Identifier(Amgn.MODID, "still_slime"), new SlimeFluid.Still());
        FLOWING_SLIME = Registry.register(Registry.FLUID, new Identifier(Amgn.MODID, "flowing_slime"), new SlimeFluid.Flowing());

        STILL_MOLTEN_CHOCOLATE = Registry.register(Registry.FLUID, new Identifier(Amgn.MODID, "acid"), new ChocolateFluid.Still());
       FLOWING_CHOCOLATE = Registry.register(Registry.FLUID, new Identifier(Amgn.MODID, "flowing_acid"), new ChocolateFluid.Flowing());
       CHOCOLATE_BUCKET = Registry.register(Registry.ITEM, new Identifier(Amgn.MODID, "acid_bucket"), new BucketItem(STILL_MOLTEN_CHOCOLATE, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(Amgn.ITEM_GROUP)));

    }

}


