package jonjar.amgn.element.item;

import net.minecraft.item.Item;

public class BladeItem extends Item {

    int modifier;
    int level;

    public BladeItem(int modifier,int level,Settings settings) {
        super(settings);
        this.modifier = modifier;
        this.level=level;
    }

    public int getModifier(){
        return this.modifier;
    }
    public int getLevel(){
        return this.level;
    }
}
