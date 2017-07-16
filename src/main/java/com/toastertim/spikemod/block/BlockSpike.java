package com.toastertim.spikemod.block;

import static com.toastertim.spikemod.Config.playerDamage;

import com.toastertim.spikemod.SpikeMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

/**
 * Created by Tim on 10/5/2016.
 */
public class BlockSpike extends Block {
	
	public float damage;

	public BlockSpike(String name, float damage, Material m, SoundType s) {
		super(m);
		this.setUnlocalizedName(name);
		this.setCreativeTab(SpikeMod.SPIKE_TAB);
		this.setHardness(1F);
		this.setResistance(1F);
		this.setSoundType(s);
		this.damage = damage;
		setRegistryName(name);
		SpikeBlocks.BLOCKS.add(this);
		SpikeBlocks.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {

		if (playerDamage)
			if (entity instanceof EntityLivingBase) {
				FakePlayer player = FakePlayerFactory.getMinecraft((WorldServer) world);
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);

		} else
			entity.attackEntityFrom(DamageSource.CACTUS, damage);
	}

	
	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state){
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}
