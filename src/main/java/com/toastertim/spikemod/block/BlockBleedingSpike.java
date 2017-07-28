package com.toastertim.spikemod.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBleedingSpike extends BlockSpike{

	public BlockBleedingSpike() {
		super(SpikeTypes.BLEEDING, Material.ANVIL, SoundType.ANVIL);
	}
	
	@Override
	public void handleSpikeDamage(World world, BlockPos pos, EntityLivingBase entity){
		entity.addPotionEffect(new PotionEffect(SpikeBlocks.BLEEDING, 200, 0));
		entity.attackEntityFrom(DamageSource.CACTUS, type.getDamage());
	}

}
