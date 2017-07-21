package com.toastertim.spikemod;

import java.io.File;

import com.toastertim.spikemod.block.SpikeTypes;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by toastertim on 10/9/2016.
 * Updated 3/2/2017
 */
public class Config {
	private static final String CATEGORY_GENERAL = "general";

	public static boolean playerDamage = true;
	public static float woodenDamage = 3F;
	public static float stoneDamage = 4F;
	public static float ironDamage = 7F;
	public static float goldDamage = 4F;
	public static float diamondDamage = 8F;
	public static float freezingDamage = 0F;
	public static float extraSharpDamage = 2F;
	public static float lootingDamage = 12F;
	public static boolean killingBlow = false;
	public static Configuration cfg;

	public static float[] damages;

	public Config() {

	}

	public static void init(File configFile) {
		if (cfg == null) {
			cfg = new Configuration(configFile);
			initConfig();
		}
	}

	public static void initConfig() {
		playerDamage = cfg.getBoolean("Use Player Damage", CATEGORY_GENERAL, playerDamage, "Controls if a diamond spike uses player damage.");
		woodenDamage = cfg.getFloat("Wooden Spike Damage", "Damage", woodenDamage, 0.0F, 100.0F, "Damage of wooden spike, 1.0 = 0.5 hearts.");
		stoneDamage = cfg.getFloat("Stone Spike Damage", "Damage", stoneDamage, 0.0F, 100.0F, "Damage of stone spike, 1.0 = 0.5 hearts.");
		ironDamage = cfg.getFloat("Iron Spike Damage", "Damage", ironDamage, 0.0F, 100.0F, "Damage of iron spike, 1.0 = 0.5 hearts.");
		goldDamage = cfg.getFloat("Gold Spike Damage", "Damage", goldDamage, 0.0F, 100.0F, "Damage of gold spike, 1.0 = 0.5 hearts.");
		diamondDamage = cfg.getFloat("Diamond Spike Damage", "Damage", diamondDamage, 0.0F, 100.0F, "Damage of diamond spike, 1.0 = 0.5 hearts.");
		freezingDamage = cfg.getFloat("Freezing Spike Damage", "Damage", freezingDamage, 0.0F, 100.0F, "Damage of freezing spike, 1.0 = 0.5 hearts.");
		extraSharpDamage = cfg.getFloat("Extra Sharp Spike Damage", "Damage", extraSharpDamage, 0.0F, 100.0F, "Base damage of extra sharp spike. Also deals ticking damage as the entity 'bleeds out' over the spike.");
		lootingDamage = cfg.getFloat("Looting Spike Damage", "Damage", lootingDamage, 0.0F, 100.0F, "Damage of looting spike, 1.0 = 0.5 hearts.");
		killingBlow = cfg.getBoolean("Kills Entity", CATEGORY_GENERAL, killingBlow, "Controls if Wooden Spike deals killing blow");

		if (cfg.hasChanged()) {
			cfg.save();
		}

		damages = new float[] { woodenDamage, stoneDamage, ironDamage, goldDamage, diamondDamage, freezingDamage, extraSharpDamage, lootingDamage };
		SpikeTypes.readConfig();
	}

	public static ConfigCategory getCategory(String category) {
		return cfg.getCategory(category);
	}
}
