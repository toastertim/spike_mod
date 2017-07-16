package com.toastertim.spikemod.block;

import static com.toastertim.spikemod.Config.diamondDamage;
import static com.toastertim.spikemod.Config.goldDamage;
import static com.toastertim.spikemod.Config.ironDamage;
import static com.toastertim.spikemod.Config.lootingDamage;
import static com.toastertim.spikemod.Config.stoneDamage;
import static com.toastertim.spikemod.Config.woodenDamage;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
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
	
    public static final Block woodenSpike = new BlockSpike("wooden_spike", woodenDamage);
    public static final Block stoneSpike = new BlockSpike("stone_spike", stoneDamage);
    public static final Block ironSpike = new BlockSpike("iron_spike", ironDamage);
    public static final Block goldSpike = new BlockSpike("golden_spike", goldDamage);
    public static final Block diamondSpike = new BlockSpike("diamond_spike", diamondDamage);
    public static final Block lootingSpike = new LootingSpike("looting_spike", lootingDamage);

    @SubscribeEvent
    public static void onBlockRegister(Register<Block> e){
    	e.getRegistry().registerAll(BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onItemRegister(Register<Item> e){
    	e.getRegistry().registerAll(ITEMS.toArray(new Item[0]));
    }
    
    
    
}
