package jonjar.amgn.mixin;

import jonjar.amgn.entity.PlayerEntityExt;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityExt {

    private int killCount = 0;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world){
        super(type, world);
    }

    public void addKills(int amount){
        killCount += amount;
        System.out.println("kills : " + killCount);
    }

}
