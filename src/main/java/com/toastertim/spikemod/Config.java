package com.toastertim.spikemod;

import java.io.File;

import com.toastertim.spikemod.block.SpikeTypes;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;


public class Config {
	private static final String CATEGORY_GENERAL = "general";

	public static boolean playerDamage = true;
	public static float woodenDamage = 3F;
	public static float stoneDamage = 4F;
	public static float ironDamage = 7F;
	public static float goldDamage = 4F;
	public static float diamondDamage = 8F;
	public static float freezingDamage = 0F;
	public static float witheringDamage = 2F;
	public static float flamingDamage = 3F;
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
		woodenDamage = cfg.getFloat("Wooden Spike Damage", "Damage", woodenDamage, 0.0F, 100.0F, "Damage of the wooden spike, 1.0 = 0.5 hearts.");
		stoneDamage = cfg.getFloat("Stone Spike Damage", "Damage", stoneDamage, 0.0F, 100.0F, "Damage of the stone spike, 1.0 = 0.5 hearts.");
		ironDamage = cfg.getFloat("Iron Spike Damage", "Damage", ironDamage, 0.0F, 100.0F, "Damage of the iron spike, 1.0 = 0.5 hearts.");
		goldDamage = cfg.getFloat("Gold Spike Damage", "Damage", goldDamage, 0.0F, 100.0F, "Damage of the gold spike, 1.0 = 0.5 hearts.");
		diamondDamage = cfg.getFloat("Diamond Spike Damage", "Damage", diamondDamage, 0.0F, 100.0F, "Damage of the diamond spike, 1.0 = 0.5 hearts.");
		freezingDamage = cfg.getFloat("Freezing Spike Damage", "Damage", freezingDamage, 0.0F, 100.0F, "Damage of the freezing spike, 1.0 = 0.5 hearts. This spike also inflicts Slowness VI");
		witheringDamage = cfg.getFloat("Withering Spike Damage", "Damage", witheringDamage, 0.0F, 100.0F, "Base damage of the withering spike, 1.0 = 0.5 hearts. This spike also inflicts Wither II. ");
		flamingDamage = cfg.getFloat("Flaming Spike Base Damage", "Damage", flamingDamage, 0.0F, 100.0F, "Base damage of the flaming spike, 1.0 = 0.5 hearts. This spike sets the target on fire.");
		lootingDamage = cfg.getFloat("Looting Spike Damage", "Damage", lootingDamage, 0.0F, 100.0F, "Damage of the looting spike, 1.0 = 0.5 hearts.");
		killingBlow = cfg.getBoolean("Kills Entity", CATEGORY_GENERAL, killingBlow, "Controls if the Wooden Spike deals killing blow");

		if (cfg.hasChanged()) {
			cfg.save();
		}

		damages = new float[] { woodenDamage, stoneDamage, ironDamage, goldDamage, diamondDamage, freezingDamage, witheringDamage, flamingDamage, lootingDamage };
		SpikeTypes.readConfig();
	}

	public static ConfigCategory getCategory(String category) {
		return cfg.getCategory(category);
	}
}
