package com.toastertim.spikemod.proxy;

import com.toastertim.spikemod.block.SpikeBlocks;

import net.minecraft.block.Block;
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
    public static void registerRenders(ModelRegistryEvent e){
        registerRender(SpikeBlocks.woodenSpike);
        registerRender(SpikeBlocks.ironSpike);
        registerRender(SpikeBlocks.stoneSpike);
        registerRender(SpikeBlocks.goldSpike);
        registerRender(SpikeBlocks.diamondSpike);
        registerRender(SpikeBlocks.lootingSpike);

    }

    public static void registerRender(Block block){
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));

    }
}
