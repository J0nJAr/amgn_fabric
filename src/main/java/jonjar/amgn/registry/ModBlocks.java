package jonjar.amgn.registry;

import jonjar.amgn.Amgn;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModBlocks {

    /*
    블럭 만들기
     */
    public static final Block CHOCOLATE_BLOCK = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES,0)
            //.requiresTool()
            .strength(0.75f,8.0f)
            .sounds(BlockSoundGroup.METAL)
            //luminance()
    );


    /*
    블럭 등록
     */
    public  static void registerBlocks(){
        Registry.register(Registry.BLOCK, new Identifier(Amgn.MODID,"chocolate_block"), CHOCOLATE_BLOCK);
    }
}
