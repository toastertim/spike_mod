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
    public static float woodenDamage = 1.5F;
    public static float stoneDamage = 2.0F;
    public static float ironDamage = 2.5F;
    public static float goldDamage = 3.0F;
    public static float diamondDamage = 3.5F;
    public static float lootingDamage = 4.0F;
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
        woodenDamage =cfg.getFloat("Wooden Spike Damage", CATEGORY_GENERAL, woodenDamage, 0.0F, 10.0F, "Change damage of wooden spike");
        stoneDamage =cfg.getFloat("Stone Spike Damage", CATEGORY_GENERAL, stoneDamage, 0.0F, 10.0F, "Change damage of stone spike");
        ironDamage =cfg.getFloat("Iron Spike Damage", CATEGORY_GENERAL, ironDamage, 0.0F, 10.0F, "Change damage of iron spike");
        goldDamage =cfg.getFloat("Gold Spike Damage", CATEGORY_GENERAL, goldDamage, 0.0F, 10.0F, "Change damage of gold spike");
        diamondDamage =cfg.getFloat("Diamond Spike Damage", CATEGORY_GENERAL, diamondDamage, 0.0F, 20.0F, "Change damage of diamond spike");
        lootingDamage =cfg.getFloat("Looting Spike Damage", CATEGORY_GENERAL, lootingDamage, 0.0F, 10.0F, "Change damage of looting spike");

        if (cfg.hasChanged()) {
            cfg.save();
        }
    }
    public static ConfigCategory getCategory(String category)
    {
        return cfg.getCategory(category);
    }
}

