package com.toastertim.spikemod.block;

import java.util.ArrayList;
import java.util.List;

import com.toastertim.spikemod.potion.BleedingPotion;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@EventBusSubscriber
public class SpikeBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final List<Item> ITEMS = new ArrayList<Item>();

	public static final Block WOOD_SPIKE = new BlockSpike(SpikeTypes.WOODEN, Material.WOOD, SoundType.WOOD);
	public static final Block STONE_SPIKE = new BlockSpike(SpikeTypes.STONE, Material.ROCK, SoundType.STONE);
	public static final Block IRON_SPIKE = new BlockSpike(SpikeTypes.IRON, Material.IRON, SoundType.METAL);
	public static final Block GOLD_SPIKE = new BlockSpike(SpikeTypes.GOLD, Material.IRON, SoundType.METAL);
	public static final Block DIAMOND_SPIKE = new BlockSpike(SpikeTypes.DIAMOND, Material.IRON, SoundType.METAL);
	public static final Block FREEZING_SPIKE = new BlockIceSpike();
	public static final Block WITHERING_SPIKE = new BlockWitherSpike();
	public static final Block FLAMING_SPIKE = new BlockFireSpike();
	public static final Block BLEEDING_SPIKE = new BlockBleedingSpike();
	public static final Block LOOTING_SPIKE = new LootingSpike();

	@SubscribeEvent
	public static void onBlockRegister(Register<Block> e) {
		e.getRegistry().registerAll(BLOCKS.toArray(new Block[0]));
		BLOCKS.clear();
	}

	@SubscribeEvent
	public static void onItemRegister(Register<Item> e) {
		e.getRegistry().registerAll(ITEMS.toArray(new Item[0]));
	}
	
	public static final Potion BLEEDING = new BleedingPotion();
	
	@SubscribeEvent
	public static void onPotionRegister(Register<Potion> e) {
		e.getRegistry().register(BLEEDING);
	}

}
