package jonjar.amgn.entity;

import net.minecraft.entity.EntityDimensions;

public interface ResizedEntity {

    float getScale();


    EntityDimensions scaleDimensions(EntityDimensions dimensions);
}
