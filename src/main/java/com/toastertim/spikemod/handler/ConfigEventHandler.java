package com.toastertim.spikemod.handler;

import com.toastertim.spikemod.Config;
import com.toastertim.spikemod.SpikeMod;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Tim on 3/7/2017.
 */
public class ConfigEventHandler {
    @SubscribeEvent
    public static void onConfigurationChangedEvent(OnConfigChangedEvent event){
        if(event.getModID().equalsIgnoreCase(SpikeMod.MODID)){
            Config.initConfig();
        }
    }

}
