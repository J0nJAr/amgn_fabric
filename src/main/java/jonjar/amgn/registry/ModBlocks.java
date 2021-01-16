package jonjar.amgn.registry;

import jonjar.amgn.Amgn;
import jonjar.amgn.element.block.ChocolateBlock;
import jonjar.amgn.element.block.CompressedRicecakeBlock;
import jonjar.amgn.element.block.CompressedSlimeBlock;
import jonjar.amgn.element.block.test;
import jonjar.amgn.element.blockentity.PulverizerBlock;
import jonjar.amgn.element.blockentity.PulverizerBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModBlocks extends BaseRegistry{

    /*
    블럭 만들기
     */
    public static final Block CHOCOLATE_BLOCK = new ChocolateBlock(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES,0)
            //.requiresTool()
            .strength(0.75f,8.0f)
            .sounds(BlockSoundGroup.METAL)
            .ticksRandomly()
            //luminance()
    );

    public static final Block COMPRESSED_SLIME_BLOCK = new CompressedSlimeBlock(FabricBlockSettings
            .of(Material.ORGANIC_PRODUCT)
            .breakByTool(FabricToolTags.PICKAXES, 0)
            .strength(0.75f, 8.0f)
            .sounds(BlockSoundGroup.SLIME)
            .nonOpaque()
            .slipperiness(0.6f)
    );

    public static final Block COMPRESSED_RICECAKE_BLOCK = new CompressedRicecakeBlock(FabricBlockSettings
            .of(Material.CAKE)
            .breakByTool(FabricToolTags.SHOVELS, 0)
            .breakByHand(true)
            .strength(0.5f, 5f)
            .sounds(BlockSoundGroup.WOOL)
            .nonOpaque());

    public static final Block TEST = new test(FabricBlockSettings
            .of(Material.CAKE)
            .breakByTool(FabricToolTags.SHOVELS, 0)
            .breakByHand(true)
            .strength(0.5f, 5f)
            .sounds(BlockSoundGroup.WOOL)
            .nonOpaque());

    public static Block MOLTEN_CHOCOLATE;
    public static Block MOLTEN_SLIME;

    public static Block PULVERIZER_BLOCK;
    public static BlockEntityType PULVERIZER_BLOCK_ENTITY;
    /*
    블럭 등록
     */
    public void register(){
        Registry.register(Registry.BLOCK, new Identifier(Amgn.MODID,"chocolate_block"), CHOCOLATE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Amgn.MODID,"compressed_slime_block"), COMPRESSED_SLIME_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Amgn.MODID,"compressed_ricecake_block"), COMPRESSED_RICECAKE_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(Amgn.MODID,"test"), TEST);

        MOLTEN_SLIME = Registry.register(Registry.BLOCK, new Identifier(Amgn.MODID, "molten_slime"), new FluidBlock(ModFluids.STILL_SLIME, FabricBlockSettings.copy(Blocks.WATER)){});
        MOLTEN_CHOCOLATE = Registry.register(Registry.BLOCK, new Identifier(Amgn.MODID, "molten_chocolate"), new FluidBlock(ModFluids.STILL_MOLTEN_CHOCOLATE, FabricBlockSettings.copy(Blocks.WATER)){});


        PULVERIZER_BLOCK = Registry.register(Registry.BLOCK, new Identifier(Amgn.MODID, "pulverizer"), new PulverizerBlock(FabricBlockSettings.of(Material.METAL)));
        PULVERIZER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Amgn.MODID, "pulverizer"), BlockEntityType.Builder.create(PulverizerBlockEntity::new, PULVERIZER_BLOCK).build(null));
    }
}
