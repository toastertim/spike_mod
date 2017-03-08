package com.toastertim.spikemod;

import com.toastertim.spikemod.block.SpikeBlocks;
import com.toastertim.spikemod.crafting.SpikeRecipes;
import com.toastertim.spikemod.handler.ConfigEventHandler;
import com.toastertim.spikemod.proxy.CommonProxy;
import com.toastertim.spikemod.tab.SpikeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by toastertim on 10/6/2016.
 * updated 3/2/2017
 */

@Mod(modid = SpikeMod.MODID, version = SpikeMod.VERSION, name = SpikeMod.NAME)
public class SpikeMod
{
    public static final String MODID = "spikemod";
    public static final String VERSION = "1.11.2-1.2";
    public static final String NAME = "Spike Mod";

    @SidedProxy(clientSide = "com.toastertim.spikemod.proxy.ClientProxy", serverSide = "com.toastertim.spikemod.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static SpikeMod instance;

    public static SpikeTab spikeTab;

    public static Logger logger;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        Config.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(ConfigEventHandler.class);
        logger = event.getModLog();
        spikeTab = new SpikeTab(CreativeTabs.getNextID(), "spike_tab");
        SpikeBlocks.preInit();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        SpikeRecipes.addRecipes();
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
}
