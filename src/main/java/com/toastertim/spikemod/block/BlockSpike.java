package com.toastertim.spikemod.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.toastertim.spikemod.SpikeMod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.*;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class BlockSpike extends Block {

	public static final PropertyDirection FACING = PropertyDirection.create("facing");

	//trying to use AABB to do onEntityCollided, unsure yet if there's a better way to do this? S: All of these are invalid, as the single block AABB is from (0,0,0) to (1,1,1), instead of (0,0,0) to (16,16,16).
	/*protected static final AxisAlignedBB AABB_BOTTOM = new AxisAlignedBB(0, 0, 0, 16, 16, 2);
	protected static final AxisAlignedBB AABB_LAYER1 = new AxisAlignedBB(1, 1, 2, 15, 15, 4);
	protected static final AxisAlignedBB AABB_LAYER2 = new AxisAlignedBB(2, 2, 4, 14, 14, 6);
	protected static final AxisAlignedBB AABB_LAYER3 = new AxisAlignedBB(3, 3, 6, 13, 13, 8);
	protected static final AxisAlignedBB AABB_LAYER4 = new AxisAlignedBB(4, 4, 8, 12, 12, 10);
	protected static final AxisAlignedBB AABB_LAYER5 = new AxisAlignedBB(5, 5, 10, 11, 11, 12);
	protected static final AxisAlignedBB AABB_LAYER6 = new AxisAlignedBB(6, 6, 12, 10, 10, 14);
	protected static final AxisAlignedBB AABB_TOP = new AxisAlignedBB(7, 7, 14, 9, 9, 16);*/
	
	private static final double px = 0.0625D; // 1/16, the value of a pixel of space. S: Moved this up here.  Can use this for other AABB stuff anyhow.

	
	//Right.  There's no built-in helper method to rotate these...  *oh boy*
	private static ImmutableList<AxisAlignedBB> UP = ImmutableList.of(new AxisAlignedBB(0, 0, 0, 1, px * 2, 1), 
			new AxisAlignedBB(px, px * 2, px, 1 - px, px * 4, 1 - px), 
			new AxisAlignedBB(px * 2, px * 4, px * 2, 1 - px * 2, px * 6, 1 - px * 2), 
			new AxisAlignedBB(px * 3, px * 6, px * 3, 1 - px * 3, px * 8, 1 - px * 3), 
			new AxisAlignedBB(px * 4, px * 8, px * 4, 1 - px * 4, px * 10, 1 - px * 4), 
			new AxisAlignedBB(px * 5, px * 10, px * 5, 1 - px * 5, px * 12, 1 - px * 5), 
			new AxisAlignedBB(px * 6, px * 12, px * 6, 1 - px * 6, px * 14, 1 - px * 6), 
			new AxisAlignedBB(px * 7, px * 14, px * 7, 1 - px * 7, px * 16, 1 - px * 7));
	private static ImmutableList<AxisAlignedBB> DOWN = ImmutableList.of(UP.get(6), UP.get(5), UP.get(4), UP.get(3), UP.get(2), UP.get(1), UP.get(0));
	private static ImmutableList<AxisAlignedBB> NORTH = ImmutableList.of(rotate(UP.get(6), Axis.Z), rotate(UP.get(5), Axis.Z), rotate(UP.get(4), Axis.Z), rotate(UP.get(3), Axis.Z), rotate(UP.get(2), Axis.Z), rotate(UP.get(1), Axis.Z), rotate(UP.get(0), Axis.Z));
	private static ImmutableList<AxisAlignedBB> SOUTH = UP;
	private static ImmutableList<AxisAlignedBB> WEST = UP;
	private static ImmutableList<AxisAlignedBB> EAST = UP;
	private static ImmutableList<ImmutableList<AxisAlignedBB>> BOXES = ImmutableList.of(DOWN, UP, NORTH, SOUTH, WEST, EAST);
	
	private static List<AxisAlignedBB> getBoxesFromState(IBlockState state){
		return BOXES.get(state.getValue(FACING).ordinal());
	}
	
	public static AxisAlignedBB rotate(AxisAlignedBB oldBB, Axis axis)
	{
		AxisAlignedBB newBB = null;
	 switch (axis) {
	 case X : newBB = new AxisAlignedBB(oldBB.minZ, oldBB.minY, oldBB.maxX * -1, oldBB.maxZ, oldBB.maxY, oldBB.minX * -1);
	 case Y : newBB = new AxisAlignedBB(oldBB.maxX * -1, oldBB.minY, oldBB.maxZ * -1, oldBB.minX * -1, oldBB.maxY, oldBB.minZ * -1);
	 case Z : newBB = new AxisAlignedBB(oldBB.maxZ * -1, oldBB.minY, oldBB.minX, oldBB.minZ * -1, oldBB.maxY, oldBB.maxX);
	 }
	 return newBB;
	}
	
	protected final SpikeTypes type;

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

		//necessary for rotation working properly S: Changed to EnumFacing.UP so old spikes don't flip.
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));

	}
	
	public SpikeTypes getType(){
		return type;
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if (!world.isRemote && entity instanceof EntityLivingBase) handleSpikeDamage(world, pos, (EntityLivingBase) entity);
	}
	
	public void handleSpikeDamage(World world, BlockPos pos, EntityLivingBase entity){
		if (type.usesPlayer() && !(entity instanceof EntityPlayer)) {
			FakePlayer player = FakePlayerFactory.getMinecraft((WorldServer) world);
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), type.getDamage());
		} else if (type.getKillsEntity() == false) {
			if (entity.getHealth() > type.getDamage()) {
				entity.attackEntityFrom(DamageSource.CACTUS, type.getDamage());
			} else if (entity.getHealth() > 1F) {
				entity.attackEntityFrom(DamageSource.CACTUS, 1F);
			}
		} else entity.attackEntityFrom(DamageSource.CACTUS, type.getDamage());
	}
	
	@Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
        return this.getDefaultState().withProperty(FACING, facing);
    }


	/**
	 * Learning to change from onEntityWalk to onEntityCollidedWithBlock
	 *
	 * Lesson 1: Tried to addCollisionBoxToList like a cauldron, that did fucky things. Don't try this at home
	 * This probably has something to do with the RayTrace stuff going on farther down in this class, stuff I did not
	 * add and thus do not have understanding of.
	 *
	 * Lesson 2: Let's try the getCollisionBoundingBox call used in a cactus, but use FULL_BLOCK_AABB instead of customs. This
	 * has not been enough to trigger onEntityCollidedWithBlock damage
	 *
	 * Lesson 3: Let's just try getBoundingBox from the Cauldron code. The problem here is probably some combination of these
	 * or other code I don't know.
	 *
	 * Will have to search for more tutorials about this that are up to date for 1.10+. Until then, this code will remain
	 * commented out so that the spikes still function
	 *
	 */

	//Lesson 2: didn't work
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}
	
	@Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> list, @Nullable Entity entityIn, boolean p_185477_7_)
    {
        for(AxisAlignedBB bb : getBoxesFromState(state)){
        	addCollisionBoxToList(pos, entityBox, list, bb);
        }
    }


	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		this.onEntityWalk(world, pos, entity);
	}

	/**
	 *
	 * BlockState Rotation Code. (Yay!)
	 *
	 * So here's what a learned in this excrutiating learning experience: lifting code from minecraft blocks is the worst way to learn
	 * Some of the stuff i learned from the minecraft code was carried over, but in the end, its community members where I learned the most
	 * McJty's tutorial on multi faced blocks is where I got the final result here. Something Forge Read the Docs doesnt explicitly point out
	 * is about getting facing a little more precisely (see getFacingFromEntity(). Also, the state/meta methods just arent really useful for
	 * state based rotations in any of the tutorials i visited or any of the minecraft code I tried to learn from, namely the Dispenser. Some
	 * of my problems was not knowing which directions to rotate on the x and y axes, and what degrees to get what i wanted, but now I kinda
	 * know what I'm looking for it seems.
	 *
	 * This has been a long two days, I'm leavning this comment here for anyone in the future who gets as hella confused as I did. Enjoy!
	 *
	 */
	/*
	@Override //S: The one issue with doing this here, is that this shouldn't be here.  There's a better method for this.
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)), 2);
	}*/
	

	@Override
	public IBlockState getStateFromMeta(int meta) {
		//im still not sure what getFront(meta & 7) does, specifically the meta & 7, on an integer level S: That's bitshifting math.  I'm not too clear on it either, but it works well for serialization.
		//But since we only have one property, we can just do this.
		return getDefaultState().withProperty(FACING, EnumFacing.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}

	/*** End rotation code***/

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

	//Everything below this line is copypasted from Tinker's Construct. Thx TiC!

	@Override
	public RayTraceResult collisionRayTrace(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end) {
		// basically the same BlockStairs does
		// Raytrace through all AABBs (plate, legs) and return the nearest one
		return raytraceMultiAABB(getBoxesFromState(state), pos, start, end);
	}

	public static RayTraceResult raytraceMultiAABB(List<AxisAlignedBB> aabbs, BlockPos pos, Vec3d start, Vec3d end) {
		List<RayTraceResult> list = Lists.newArrayList();

		for (AxisAlignedBB axisalignedbb : aabbs) {
			list.add(rayTrace2(pos, start, end, axisalignedbb));
		}

		RayTraceResult raytraceresult1 = null;
		double d1 = 0.0D;

		for (RayTraceResult raytraceresult : list) {
			if (raytraceresult != null) {
				double d0 = raytraceresult.hitVec.squareDistanceTo(end);

				if (d0 > d1) {
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
