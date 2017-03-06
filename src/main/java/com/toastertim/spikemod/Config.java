package com.toastertim.spikemod;

import com.toastertim.spikemod.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

/**
 * Created by toastertim on 10/9/2016.
 * Updated 3/2/2017
 */
public class Config {
    private static final String CATEGORY_GENERAL = "general";

    public static boolean dropsXP = true;

    public Config(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGenConfig(cfg);
        } catch (Exception e1) {
            SpikeMod.logger.log(Level.ERROR, "Problem loading config file!", e1);
            System.out.println("Spikemod config error" + e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGenConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        dropsXP = cfg.getBoolean(CATEGORY_GENERAL, "xpDrop", dropsXP, "Set to false for to change spike damage to not drop xp");
        //dropsXP = cfg.get(CATEGORY_GENERAL, "xpDrop", dropsXP, "Set to false for to change spike damage to not drop xp").getBoolean();
    }
}

