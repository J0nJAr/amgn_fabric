package jonjar.amgn.registry.ect;

import jonjar.amgn.Amgn;
import jonjar.amgn.registry.BaseRegistry;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ModItemTags extends BaseRegistry {
    public static Tag<Item> BLADE;

    public void register(){
        BLADE = TagRegistry.item(new Identifier(Amgn.MODID,"blade"));
    }
}
