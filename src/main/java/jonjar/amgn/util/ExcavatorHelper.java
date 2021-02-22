package jonjar.amgn.util;

import net.minecraft.util.math.BlockPos;

public class ExcavatorHelper {

    public static BlockPos calculateStartBlock(BlockPos pos, int range){
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int dx = getDigit(x, range);
        int dy = getDigit(y, range);
        int dz = getDigit(z, range);

        //System.out.println("x " + dx + " / y " + dy + " /  z " + dz);

        return new BlockPos(dx, dy, dz);
    }

    private static int getDigit(int i, int range){
        boolean minus = i < 0;
        int v = Math.abs(i) - (minus ? 1 : 0);
        int r = v - v % range;
        //System.out.println("get " + i + " , " + " r is " + r);
        return minus ? -r-range : r;
    }

    // TODO : (0,0,0) 이면 false인데... 왜? 0,0,0이 valid하지 않은가?
    static boolean isValidPos(BlockPos pos) {
        return (Math.abs(pos.getX()) + Math.abs(pos.getY()) + Math.abs(pos.getZ())) != 0;
    }

}
