package com.toastertim.spikemod.crafting;

import com.toastertim.spikemod.SpikeMod;
import com.toastertim.spikemod.block.SpikeBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by toastertim on 10/6/2016.
 */
public class SpikeRecipes {
	
	private static ResourceLocation group = new ResourceLocation(SpikeMod.MODID, "spikes");
	
	public static void addRecipes() {

		addShapedRecipe(new ItemStack(SpikeBlocks.woodenSpike, 4), " S ", "SLS", "LWL", 'S', Items.WOODEN_SWORD, 'L', Blocks.LOG, 'W', Blocks.PLANKS);
		addShapedRecipe(new ItemStack(SpikeBlocks.stoneSpike, 4), " S ", "SKS", "KBK", 'S', Items.STONE_SWORD, 'K', SpikeBlocks.woodenSpike, 'B', Blocks.STONEBRICK);
		addShapedRecipe(new ItemStack(SpikeBlocks.ironSpike, 4), " S ", "SKS", "KBK", 'S', Items.IRON_SWORD, 'K', SpikeBlocks.stoneSpike, 'B', Blocks.IRON_BLOCK);
		addShapedRecipe(new ItemStack(SpikeBlocks.goldSpike, 4), " S ", "SKS", "KBK", 'S', Items.GOLDEN_SWORD, 'K', SpikeBlocks.ironSpike, 'B', Blocks.GOLD_BLOCK);
		addShapedRecipe(new ItemStack(SpikeBlocks.diamondSpike, 4), " S ", "SKS", "KBK", 'S', Items.DIAMOND_SWORD, 'K', SpikeBlocks.goldSpike, 'B', Blocks.DIAMOND_BLOCK);
		addShapedRecipe(new ItemStack(SpikeBlocks.lootingSpike, 4), "ASA", "SXS", "AEA", 'S', SpikeBlocks.diamondSpike, 'A', Items.BOOK, 'X', Items.NETHER_STAR, 'E', Blocks.ENCHANTING_TABLE);
		addShapedRecipe(new ItemStack(SpikeBlocks.freezingSpike, 1), " I ", "PSP", "   ", 'I', Blocks.ICE, 'P', Blocks.PACKED_ICE, 'S', SpikeBlocks.stoneSpike);
		addShapedRecipe(new ItemStack(SpikeBlocks.extraSharpSpike, 1), " W ", "WSW", "   ", 'W', Items.STONE_SWORD, 'S', SpikeBlocks.stoneSpike);
		addShapedRecipe(new ItemStack(SpikeBlocks.hotSpike, 1), " F ", "TST", "   ", 'F', Items.FIRE_CHARGE, 'T', Items.FLINT_AND_STEEL, 'S', SpikeBlocks.ironSpike);


	}
	
	public static void addShapedRecipe(ItemStack output, Object... recipe){
		GameRegistry.addShapedRecipe(output.getItem().getRegistryName(), group, output, recipe);
	}
	
}
