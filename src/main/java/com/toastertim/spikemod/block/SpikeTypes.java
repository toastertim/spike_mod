package com.toastertim.spikemod.block;

import com.toastertim.spikemod.Config;

public enum SpikeTypes {

	WOODEN("wooden_spike", Config.woodenDamage, false, Config.killingBlow),
	STONE("stone_spike", Config.stoneDamage, false, true),
	IRON("iron_spike", Config.ironDamage, false, true),
	GOLD("golden_spike", Config.goldDamage, Config.playerDamage, true),
	DIAMOND("diamond_spike", Config.diamondDamage, Config.playerDamage, true),
	FREEZING("freezing_spike", Config.freezingDamage, false, true),
	EXTRASHARPSPIKE("extra_sharp_spike", Config.extraSharpDamage, false, true),
	HOTSPIKE("hot_spike", Config.hotSpikeDamage, false, true),
	LOOTING("looting_spike", Config.lootingDamage, true, true);

	private final String name;
	private float damage;
	private boolean usePlayer;
	private boolean killsEntity;

	private SpikeTypes(String name, float damage, boolean usePlayer, boolean killsEntity) {
		this.name = name;
		this.damage = damage;
		this.usePlayer = usePlayer;
		this.killsEntity = killsEntity;
	}

	public boolean usesPlayer() {
		return usePlayer;
	}

	public static void readConfig() {
		for (SpikeTypes s : SpikeTypes.values()) {
			s.setDamage(Config.damages[s.ordinal()]);
		}
		GOLD.setUsePlayer(Config.playerDamage);
		DIAMOND.setUsePlayer(Config.playerDamage);
		WOODEN.setKillsEntity(Config.killingBlow);

	}

	public void setDamage(float k) {
		damage = k;
	}

	public void setUsePlayer(boolean p) {
		usePlayer = p;
	}

	public void setKillsEntity (boolean k) {killsEntity = k;}

	public float getDamage() {
		return damage;
	}

	public String getName() {
		return name;
	}

	public boolean getKillsEntity() {return killsEntity;}







}
