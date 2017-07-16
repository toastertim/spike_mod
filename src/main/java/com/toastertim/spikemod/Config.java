package com.toastertim.spikemod;

import java.io.File;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

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
        dropsXP = cfg.getBoolean("Do XP Drop", CATEGORY_GENERAL, dropsXP, "Set to false for to change spike damage to not drop xp, does not include looting spikes");
        woodenDamage =cfg.getFloat("Wooden Spike Damage", "Damage", woodenDamage, 0.0F, 100.0F, "Change damage of wooden spike, 1.0 ~= 0.5 hearts.");
        stoneDamage =cfg.getFloat("Stone Spike Damage", "Damage", stoneDamage, 0.0F, 100.0F, "Change damage of stone spike, 1.0 ~= 0.5 hearts.");
        ironDamage =cfg.getFloat("Iron Spike Damage", "Damage", ironDamage, 0.0F, 100.0F, "Change damage of iron spike, 1.0 ~= 0.5 hearts.");
        goldDamage =cfg.getFloat("Gold Spike Damage", "Damage", goldDamage, 0.0F, 100.0F, "Change damage of gold spike, 1.0 ~= 0.5 hearts.");
        diamondDamage =cfg.getFloat("Diamond Spike Damage", "Damage", diamondDamage, 0.0F, 100.0F, "Change damage of diamond spike, 1.0 ~= 0.5 hearts.");
        lootingDamage =cfg.getFloat("Looting Spike Damage", "Damage", lootingDamage, 0.0F, 100.0F, "Change damage of looting spike, 1.0 ~= 0.5 hearts.");

        if (cfg.hasChanged()) {
            cfg.save();
        }
    }
    public static ConfigCategory getCategory(String category)
    {
        return cfg.getCategory(category);
    }
}

