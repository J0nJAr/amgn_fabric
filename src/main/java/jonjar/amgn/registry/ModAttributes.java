package jonjar.amgn.registry;

import jonjar.amgn.Amgn;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModAttributes extends BaseRegistry{

    public static final EntityAttribute REACH = make("reach", 0.0, -1024.0, 1024.0);
    public static final EntityAttribute ATTACK_RANGE = make("attack_range", 0.0, -1024.0, 1024.0);

    @Override
    public void register() {
        Registry.register(Registry.ATTRIBUTE, new Identifier(Amgn.MODID, "reach"), REACH);
        Registry.register(Registry.ATTRIBUTE, new Identifier(Amgn.MODID, "attack_range"), ATTACK_RANGE);
    }

    private static EntityAttribute make(final String name, final double base, final double min, final double max) {
        return new ClampedEntityAttribute("attribute.name.generic." + Amgn.MODID + '.' + name, base, min, max).setTracked(true);
    }
}
