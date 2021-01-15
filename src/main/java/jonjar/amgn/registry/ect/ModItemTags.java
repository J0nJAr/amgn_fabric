package jonjar.amgn.registry.ect;

import jonjar.amgn.Amgn;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final Tag<Item> BLADE = TagRegistry.item(new Identifier(Amgn.MODID,"blade"));

    public static void register(){

    }
}
