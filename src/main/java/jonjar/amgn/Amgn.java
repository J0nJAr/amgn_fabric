package jonjar.amgn;

import jonjar.amgn.element.recipe.pulverizer.PulverizerRecipeRegister;
import jonjar.amgn.registry.BaseRegistry;
import jonjar.amgn.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Amgn implements ModInitializer {

    // RESIZE REFERENCE : https://gitea.thebrokenrail.com/TheBrokenRail/GulliverReloaded

    public static boolean TOGGLED_GRAVITY = false;

    public static final String MODID ="amgn";

    public static final Logger LOG = LogManager.getLogger(MODID);

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MODID, "general"),
            () -> new ItemStack(ModItems.PULVERIZER)
    );
    public static final ItemGroup ARMOR_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MODID, "armor"),
            () -> new ItemStack(ModItems.ICE_BOOTS)
    );
    @Override
    public void onInitialize() {

        /*
        try{
            Class c = Class.forName("net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry");
            Field field = c.getDeclaredField("renderManagerMap");
            Map<EntityType<?>, EntityRenderer<?>> map = (Map<EntityType<?>, EntityRenderer<?>>) field.get(c);

            for(EntityRenderer er : map.values()){
                er.getRenderManager().
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

         */


//        for(Block b : Registry.BLOCK){
//            if(b.getTranslationKey().contains("minecraft")){
//                try{
//                    Class c = Class.forName("net.minecraft.block.AbstractBlock");
//                    Field field = c.getDeclaredField("slipperiness");
//                    field.setAccessible(true);
//                    field.set(b, 1.11F);
//                    field.setAccessible(false);
//                } catch(Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//
//        }


        PulverizerRecipeRegister.registerRecipes();

        BaseRegistry.registerAll();
    }
}
