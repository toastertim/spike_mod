package com.toastertim.spikemod.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockIceSpike extends BlockSpike{

	public BlockIceSpike() {
		super(SpikeTypes.FREEZING, Material.ICE, SoundType.GLASS);
	}
	
	@Override
	public void handleSpikeDamage(World world, BlockPos pos, EntityLivingBase entity){
		entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200, 5));
		entity.attackEntityFrom(DamageSource.CACTUS, type.getDamage());
	}

}
