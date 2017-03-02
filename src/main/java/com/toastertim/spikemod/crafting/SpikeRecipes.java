package com.toastertim.spikemod.crafting;

import com.toastertim.spikemod.block.SpikeBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Tim on 10/6/2016.
 */
public class SpikeRecipes {
    public static void addRecipes(){

        GameRegistry.addRecipe(new ItemStack(SpikeBlocks.woodenSpike, 3), " S ", "SLS", "LWL", 'S', Items.WOODEN_SWORD, 'L', Blocks.LOG, 'W', Blocks.PLANKS);
        GameRegistry.addRecipe(new ItemStack(SpikeBlocks.stoneSpike, 3), " S ", "SKS", "KBK", 'S', Items.STONE_SWORD, 'K', SpikeBlocks.woodenSpike, 'B', Blocks.STONEBRICK);
        GameRegistry.addRecipe(new ItemStack(SpikeBlocks.ironSpike, 3), " S ", "SKS", "KBK", 'S', Items.IRON_SWORD, 'K', SpikeBlocks.stoneSpike, 'B', Blocks.IRON_BLOCK);
        GameRegistry.addRecipe(new ItemStack(SpikeBlocks.goldSpike, 3), " S ", "SKS", "KBK", 'S', Items.GOLDEN_SWORD, 'K', SpikeBlocks.ironSpike, 'B', Blocks.GOLD_BLOCK);
        GameRegistry.addRecipe(new ItemStack(SpikeBlocks.diamondSpike, 3), " S ", "SKS", "KBK", 'S', Items.DIAMOND_SWORD, 'K', SpikeBlocks.goldSpike, 'B', Blocks.DIAMOND_BLOCK );
        GameRegistry.addRecipe(new ItemStack(SpikeBlocks.lootingSpike, 1), "ASA", "SXS", "AEA", 'S', SpikeBlocks.diamondSpike, 'A', Items.BOOK, 'X', Items.NETHER_STAR, 'E', Blocks.ENCHANTING_TABLE);

    }
}
