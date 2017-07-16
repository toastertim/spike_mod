package com.toastertim.spikemod.block;

import com.toastertim.spikemod.Config;

public enum SpikeTypes {

	WOODEN("wooden_spike", Config.woodenDamage, false),
	STONE("stone_spike", Config.stoneDamage, false),
	IRON("iron_spike", Config.ironDamage, false),
	GOLD("golden_spike", Config.goldDamage, Config.playerDamage),
	DIAMOND("diamond_spike", Config.diamondDamage, Config.playerDamage);

	private final String name;
	private float damage;
	private final boolean usePlayer;

	private SpikeTypes(String name, float damage, boolean usePlayer) {
		this.name = name;
		this.damage = damage;
		this.usePlayer = usePlayer;
	}

	public boolean usesPlayer() {
		return usePlayer;
	}

	public void setDamage(float k) {
		damage = k;
	}

	public float getDamage() {
		return damage;
	}

	public String getName() {
		return name;
	}

}
