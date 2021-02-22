package jonjar.amgn.util;

import jonjar.amgn.entity.PlayerEntityExt;
import jonjar.amgn.entity.ResizedEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayDeque;
import java.util.Deque;

public class Excavate {

    // REFERENCE : https://github.com/kyrptonaught/DiggusMaximus/blob/master/src/main/java/net/kyrptonaught/diggusmaximus/Excavate.java

    /*
    변형 방식?
    - 아이디어 노트 노랑 포스트잇
     */

    private BlockPos basePos;
    private final PlayerEntity player;
    private String id;
    private final Item startTool;

    private BlockPos startPos;

    private int mined = 0;
    private int range = 0;
    private final World world;
    private final Deque<BlockPos> points = new ArrayDeque<>();

    public Excavate(BlockPos pos, PlayerEntity entity){
        int size = (int) (((ResizedEntity) entity).getScale() * 2);
        this.range = (size / 5) * 2 + 1;

        this.basePos = pos;
        this.player = entity;
        this.world = player.getEntityWorld();
        this.id = world.getBlockState(pos).getBlock().getTranslationKey();
        this.startTool = player.getMainHandStack().getItem();
    }

    public void startExcavate(){
        forceExcavateAt(basePos);
        startPos = ExcavatorHelper.calculateStartBlock(basePos, range);
        ((PlayerEntityExt) player).setExcavating(true);
        for(int x=0;x<range;x++){
            for(int y=0;y<range;y++){
                for(int z=0;z<range;z++){
                    BlockPos bp = startPos.add(x, y, z);
                    excavateAt(bp);
                }
            }
        }
        ((PlayerEntityExt) player).setExcavating(false);
    }


    private void forceExcavateAt(BlockPos pos){
        if (((ServerPlayerEntity) player).interactionManager.tryBreakBlock(pos)) {
            mined++;
            // 드롭템 줍기?
        }
    }

    private void excavateAt(BlockPos pos){
        boolean same = id.equals(world.getBlockState(pos).getBlock().getTranslationKey());
        if(same){
            if(((ServerPlayerEntity) player).interactionManager.tryBreakBlock(pos)) {
                mined++;
            }
        }

    }

}
