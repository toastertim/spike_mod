package com.toastertim.spikemod.proxy;

import com.toastertim.spikemod.block.SpikeBlocks;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by toastertim on 10/6/2016.
 */

@EventBusSubscriber
public class ClientProxy extends CommonProxy {

	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent e) {
		for (Item k : SpikeBlocks.ITEMS)
			ModelLoader.setCustomModelResourceLocation(k, 0, new ModelResourceLocation(k.getRegistryName(), "inventory"));

	}
}
