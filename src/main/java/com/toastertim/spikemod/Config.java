package com.toastertim.spikemod;

import com.toastertim.spikemod.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.io.File;

/**
 * Created by toastertim on 10/9/2016.
 * Updated 3/2/2017
 */
public class Config {
    private static final String CATEGORY_GENERAL = "general";

    public static boolean dropsXP = true;
    public static Configuration cfg;

    public Config(){

    }

    public static void init(File configFile)
    {
        if (cfg == null) {
            cfg = new Configuration(configFile);
            initConfig();
        }
    }

    public static void initConfig() {
        dropsXP = cfg.getBoolean(CATEGORY_GENERAL, "xpDrop", dropsXP, "Set to false for to change spike damage to not drop xp, does not include looting spikes");
        if (cfg.hasChanged()) {
            cfg.save();
        }
    }
    public static ConfigCategory getCategory(String category)
    {
        return cfg.getCategory(category);
    }
}

