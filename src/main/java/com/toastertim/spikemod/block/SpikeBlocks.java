package com.toastertim.spikemod.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Tim on 10/6/2016.
 */

@EventBusSubscriber
public class SpikeBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final List<Item> ITEMS = new ArrayList<Item>();

	public static final Block woodenSpike = new BlockSpike(SpikeTypes.WOODEN, Material.WOOD, SoundType.WOOD);
	public static final Block stoneSpike = new BlockSpike(SpikeTypes.STONE, Material.ROCK, SoundType.STONE);
	public static final Block ironSpike = new BlockSpike(SpikeTypes.IRON, Material.IRON, SoundType.METAL);
	public static final Block goldSpike = new BlockSpike(SpikeTypes.GOLD, Material.IRON, SoundType.METAL);
	public static final Block diamondSpike = new BlockSpike(SpikeTypes.DIAMOND, Material.IRON, SoundType.METAL);
	public static final Block freezingSpike = new BlockSpike(SpikeTypes.FREEZING, Material.ICE, SoundType.STONE);
	public static final Block extraSharpSpike = new BlockSpike(SpikeTypes.EXTRASHARPSPIKE, Material.ROCK, SoundType.STONE);
	public static final Block hotSpike = new BlockSpike(SpikeTypes.HOTSPIKE, Material.IRON, SoundType.METAL);
	public static final Block lootingSpike = new LootingSpike();

	@SubscribeEvent
	public static void onBlockRegister(Register<Block> e) {
		e.getRegistry().registerAll(BLOCKS.toArray(new Block[0]));
		BLOCKS.clear();
	}

	@SubscribeEvent
	public static void onItemRegister(Register<Item> e) {
		e.getRegistry().registerAll(ITEMS.toArray(new Item[0]));
	}

}
