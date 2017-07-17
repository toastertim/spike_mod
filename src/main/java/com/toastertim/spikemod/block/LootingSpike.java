package com.toastertim.spikemod.block;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.toastertim.spikemod.Config;
import com.toastertim.spikemod.SpikeMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

/**
 * Created by Tim on 10/5/2016.
 */
public class LootingSpike extends Block {

	public LootingSpike() {
		super(Material.IRON);
		this.setUnlocalizedName("looting_spike");
		this.setCreativeTab(SpikeMod.SPIKE_TAB);
		this.setHardness(1F);
		this.setResistance(1F);
		this.setSoundType(SoundType.METAL);
		setRegistryName("looting_spike");
		SpikeBlocks.BLOCKS.add(this);
		SpikeBlocks.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if (!world.isRemote) {
			if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer)) {
				FakePlayer player = FakePlayerFactory.getMinecraft((WorldServer) world);
				ItemStack onHand = new ItemStack(Items.STICK);
				onHand.addEnchantment(Enchantments.LOOTING, 3);
				player.setHeldItem(EnumHand.MAIN_HAND, onHand);
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), Config.lootingDamage);
				player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
			}

			entity.attackEntityFrom(DamageSource.CACTUS, Config.lootingDamage);
		}
	}

	//for the purposes of skyblocks, mobs can spawn on this block
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
	
	private static final double px = 0.0625D; // 1/16, the value of a pixel of space.
	
	 private static ImmutableList<AxisAlignedBB> SPIKE_BOUNDS = ImmutableList.of(
	     new AxisAlignedBB(0, 0, 0, 1, px*2, 1),
	     new AxisAlignedBB(px, px*2, px, 1-px, px*4, 1-px),
	     new AxisAlignedBB(px*2, px*4, px*2, 1-px*2, px*6, 1-px*2),
	     new AxisAlignedBB(px*3, px*6, px*3, 1-px*3, px*8, 1-px*3),
	     new AxisAlignedBB(px*4, px*8, px*4, 1-px*4, px*10, 1-px*4),
	     new AxisAlignedBB(px*5, px*10, px*5, 1-px*5, px*12, 1-px*5),
	     new AxisAlignedBB(px*6, px*12, px*6, 1-px*6, px*14, 1-px*6),
	     new AxisAlignedBB(px*7, px*14, px*7, 1-px*7, px*16, 1-px*7)
	  );
	
	//Everything below this line is copypasted from Tinker's Construct. Thx TiC!

	  @Override
	  public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end) {
	    // basically the same BlockStairs does
	    // Raytrace through all AABBs (plate, legs) and return the nearest one
	    return raytraceMultiAABB(SPIKE_BOUNDS, pos, start, end);
	  }

	  public static RayTraceResult raytraceMultiAABB(List<AxisAlignedBB> aabbs, BlockPos pos, Vec3d start, Vec3d end) {
	    List<RayTraceResult> list = Lists.newArrayList();

	    for(AxisAlignedBB axisalignedbb : aabbs) {
	      list.add(rayTrace2(pos, start, end, axisalignedbb));
	    }

	    RayTraceResult raytraceresult1 = null;
	    double d1 = 0.0D;

	    for(RayTraceResult raytraceresult : list) {
	      if(raytraceresult != null) {
	        double d0 = raytraceresult.hitVec.squareDistanceTo(end);

	        if(d0 > d1) {
	          raytraceresult1 = raytraceresult;
	          d1 = d0;
	        }
	      }
	    }

	    return raytraceresult1;
	  }

	  // Block.raytrace
	  private static RayTraceResult rayTrace2(BlockPos pos, Vec3d start, Vec3d end, AxisAlignedBB boundingBox) {
	    Vec3d vec3d = start.subtract((double) pos.getX(), (double) pos.getY(), (double) pos.getZ());
	    Vec3d vec3d1 = end.subtract((double) pos.getX(), (double) pos.getY(), (double) pos.getZ());
	    RayTraceResult raytraceresult = boundingBox.calculateIntercept(vec3d, vec3d1);
	    return raytraceresult == null ? null : new RayTraceResult(raytraceresult.hitVec.addVector((double) pos.getX(), (double) pos.getY(), (double) pos.getZ()), raytraceresult.sideHit, pos);
	  }
	
}
