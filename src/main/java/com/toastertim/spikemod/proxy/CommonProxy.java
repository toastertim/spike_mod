package com.toastertim.spikemod.proxy;

import com.toastertim.spikemod.Config;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

/**
 * Created by toastertim on 10/6/2016.
 */
public class CommonProxy {

    public static Configuration config;

    public void preInit(FMLPreInitializationEvent event){
       /* Doesnt make config file
        File directory = event.getModConfigurationDirectory();
        File configFile = new File(directory.getPath(), "spikemod.cfg");
        config = new Configuration(configFile);
        Config.readConfig(); */

       //Doesnt work either??
        File configFile = new File(Loader.instance().getConfigDir(), "SpikeMod.cfg");
        config = new Configuration(configFile);
        Config.readConfig();
    }

    public void init(FMLInitializationEvent event){

    }

    public void postInit(FMLPostInitializationEvent event){
        if(config.hasChanged()) config.save();
    }
}
