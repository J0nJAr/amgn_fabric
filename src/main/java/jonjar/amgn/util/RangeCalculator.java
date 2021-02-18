package jonjar.amgn.util;

import jonjar.amgn.entity.ResizedEntity;
import jonjar.amgn.registry.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RangeCalculator {

    public static double getReachDistance(final LivingEntity entity, final double baseReachDistance) {
        final EntityAttributeInstance reachDistance = entity.getAttributeInstance(ModAttributes.REACH);
        ResizedEntity re = (ResizedEntity) entity;
        return (reachDistance != null) ? (baseReachDistance + re.getScale()*4 - 4) : baseReachDistance;
    }

    public static double getSquaredReachDistance(final LivingEntity entity, final double sqBaseReachDistance) {
        final double reachDistance = getReachDistance(entity, Math.sqrt(sqBaseReachDistance));
        return reachDistance * reachDistance;
    }

    public static double getAttackRange(final LivingEntity entity, final double baseAttackRange) {
        final EntityAttributeInstance attackRange = entity.getAttributeInstance(ModAttributes.ATTACK_RANGE);
        ResizedEntity re = (ResizedEntity) entity;
        return (attackRange != null) ? (baseAttackRange + re.getScale()*4 - 4) : baseAttackRange;
    }

    public static double getSquaredAttackRange(final LivingEntity entity, final double sqBaseAttackRange) {
        final double attackRange = getAttackRange(entity, Math.sqrt(sqBaseAttackRange));
        return attackRange * attackRange;
    }

    public static List<PlayerEntity> getPlayersWithinReach(final World world, final int x, final int y, final int z, final double baseReachDistance) {
        final List<PlayerEntity> playersWithinReach = new ArrayList<>(0);
        for (final PlayerEntity player : world.getPlayers()) {
            final double reach = getSquaredReachDistance(player, baseReachDistance);
            final double dx = (x + 0.5) - player.getX();
            final double dy = (y + 0.5) - player.getEyeY();
            final double dz = (z + 0.5) - player.getZ();
            if (((dx * dx) + (dy * dy) + (dz * dz)) <= reach) {
                playersWithinReach.add(player);
            }
        }
        return playersWithinReach;
    }

}
