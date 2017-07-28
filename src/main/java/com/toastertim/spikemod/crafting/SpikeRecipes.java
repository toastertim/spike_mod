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

		addShapedRecipe(new ItemStack(SpikeBlocks.WOOD_SPIKE, 4), " S ", "SLS", "LWL", 'S', Items.WOODEN_SWORD, 'L', Blocks.LOG, 'W', Blocks.PLANKS);
		addShapedRecipe(new ItemStack(SpikeBlocks.STONE_SPIKE, 4), " S ", "SKS", "KBK", 'S', Items.STONE_SWORD, 'K', SpikeBlocks.WOOD_SPIKE, 'B', Blocks.STONEBRICK);
		addShapedRecipe(new ItemStack(SpikeBlocks.IRON_SPIKE, 4), " S ", "SKS", "KBK", 'S', Items.IRON_SWORD, 'K', SpikeBlocks.STONE_SPIKE, 'B', Blocks.IRON_BLOCK);
		addShapedRecipe(new ItemStack(SpikeBlocks.GOLD_SPIKE, 4), " S ", "SKS", "KBK", 'S', Items.GOLDEN_SWORD, 'K', SpikeBlocks.IRON_SPIKE, 'B', Blocks.GOLD_BLOCK);
		addShapedRecipe(new ItemStack(SpikeBlocks.DIAMOND_SPIKE, 4), " S ", "SKS", "KBK", 'S', Items.DIAMOND_SWORD, 'K', SpikeBlocks.GOLD_SPIKE, 'B', Blocks.DIAMOND_BLOCK);
		addShapedRecipe(new ItemStack(SpikeBlocks.LOOTING_SPIKE, 3), "ASA", "SXS", "AEA", 'S', SpikeBlocks.DIAMOND_SPIKE, 'A', Items.BOOK, 'X', Items.NETHER_STAR, 'E', Blocks.ENCHANTING_TABLE);
		addShapedRecipe(new ItemStack(SpikeBlocks.FREEZING_SPIKE, 1), " I ", "PSP", "   ", 'I', Blocks.ICE, 'P', Blocks.PACKED_ICE, 'S', SpikeBlocks.STONE_SPIKE);
		addShapedRecipe(new ItemStack(SpikeBlocks.WITHERING_SPIKE, 3), " W ", "DSD", "   ", 'W', new ItemStack(Items.SKULL, 1, 1), 'S', Blocks.SOUL_SAND, 'D', Items.DIAMOND_SWORD);
		addShapedRecipe(new ItemStack(SpikeBlocks.FLAMING_SPIKE, 1), " F ", "TST", "   ", 'F', Items.FIRE_CHARGE, 'T', Items.FLINT_AND_STEEL, 'S', SpikeBlocks.IRON_SPIKE);


	}
	
	public static void addShapedRecipe(ItemStack output, Object... recipe){
		GameRegistry.addShapedRecipe(output.getItem().getRegistryName(), group, output, recipe);
	}
	
}
