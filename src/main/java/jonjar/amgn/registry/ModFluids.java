package jonjar.amgn.registry;


import jonjar.amgn.Amgn;
import jonjar.amgn.element.fluid.ChocolateFluid;
import jonjar.amgn.element.fluid.SlimeFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;



public class ModFluids {

    public static FlowableFluid STILL_MOLTEN_CHOCOLATE;
    public static FlowableFluid FLOWING_CHOCOLATE;

    public static FlowableFluid STILL_SLIME;
    public static FlowableFluid FLOWING_SLIME;




    public static void registryFluids()
    {
        STILL_SLIME = Registry.register(Registry.FLUID, new Identifier(Amgn.MODID, "molten_slime"), new SlimeFluid.Still());
        FLOWING_SLIME = Registry.register(Registry.FLUID, new Identifier(Amgn.MODID, "flowing_slime"), new SlimeFluid.Flowing());

        STILL_MOLTEN_CHOCOLATE = Registry.register(Registry.FLUID, new Identifier(Amgn.MODID, "molten_chocolate"), new ChocolateFluid.Still());
       FLOWING_CHOCOLATE = Registry.register(Registry.FLUID, new Identifier(Amgn.MODID, "flowing_chocolate"), new ChocolateFluid.Flowing());


    }

}


