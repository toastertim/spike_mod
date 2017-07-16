package com.toastertim.spikemod.block;

import com.toastertim.spikemod.SpikeMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
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

	public static final AxisAlignedBB SPIKE_BOX = new AxisAlignedBB(0, 0, 0, 16, 16, 16);

	private final SpikeTypes type;

	public BlockSpike(SpikeTypes type, Material m, SoundType s) {
		super(m);
		this.setUnlocalizedName(type.getName());
		this.setCreativeTab(SpikeMod.SPIKE_TAB);
		this.setHardness(1F);
		this.setResistance(1F);
		this.setSoundType(s);
		this.type = type;
		setRegistryName(type.getName());
		SpikeBlocks.BLOCKS.add(this);
		SpikeBlocks.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if (!world.isRemote) {
			if (type.usesPlayer() && entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {
				FakePlayer player = FakePlayerFactory.getMinecraft((WorldServer) world);
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), type.getDamage());
			} else
				entity.attackEntityFrom(DamageSource.CACTUS, type.getDamage());
		}
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
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
