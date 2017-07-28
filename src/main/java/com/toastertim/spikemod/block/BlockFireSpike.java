package com.toastertim.spikemod.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFireSpike extends BlockSpike{

	public BlockFireSpike() {
		super(SpikeTypes.FLAMING, Material.IRON, SoundType.METAL);
	}
	
	@Override
	public void handleSpikeDamage(World world, BlockPos pos, EntityLivingBase entity){
		entity.setFire(5);
		entity.attackEntityFrom(DamageSource.IN_FIRE, type.getDamage());
	}
	
	//S: Honestly, not sure the magma draws well on the spike model. Texture here might need work, but I'm no artist.

}
