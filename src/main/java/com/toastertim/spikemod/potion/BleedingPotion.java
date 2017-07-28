package com.toastertim.spikemod.potion;


import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class BleedingPotion extends Potion{

	public BleedingPotion() {
		super(true, Color.RED.getRGB());
		setRegistryName("bleeding");
		setPotionName("effect.spikemod.bleeding");
	}
	
	private static ItemStack sword = new ItemStack(Items.DIAMOND_SWORD);
	
	@Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		mc.getRenderItem().renderItemIntoGUI(sword, x + 6, y + 7);
	}
	
	@Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) { 
		renderInventoryEffect(x - 2, y - 3, effect, mc);
	}
	
	@Override //This runs whenever isReady returns true in a given tick.  (I think)
	public void performEffect(EntityLivingBase entity, int amplifier) {
		entity.attackEntityFrom(DamageSource.CACTUS, 5 * amplifier);//Probably want a custom DamageSource for a custom death message.
	}//But yeah, do damage in here.  It does stuff.
	
	@Override //When, based on duration, the potion actually does anything, I'm not sure what unit duration is in, ticks or seconds. (TICKS. YEP. TICKS.)
	public boolean isReady(int duration, int amplifier) {
		return duration % 100 == 0;
	}//Make this faster to make more calls to performEffect.

}
