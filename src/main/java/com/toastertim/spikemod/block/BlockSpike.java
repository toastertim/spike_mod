package com.toastertim.spikemod.block;


import static com.toastertim.spikemod.Config.dropsXP;

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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Tim on 10/5/2016.
 */
public class BlockSpike extends Block{

    public static EntityPlayer player;
    public float spikeType;
    public static FakePlayer fake;
    public static EntityLivingBase livingEntity;
    public boolean flag = true;

    public BlockSpike(String name, float value){
        super(Material.ROCK);
        this.setUnlocalizedName(name);
        this.setCreativeTab(SpikeMod.SPIKE_TAB);
        this.setHardness(1F);
        this.setResistance(1F);
        this.setSoundType(SoundType.STONE);
        spikeType = value;
        SpikeBlocks.BLOCKS.add(this);
        SpikeBlocks.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
    }


    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {

        if(dropsXP) {
            if (!(entityIn instanceof EntityPlayer)) {
                if (entityIn instanceof EntityLivingBase) {
                    fake = FakePlayerFactory.getMinecraft((WorldServer) worldIn);
                    player = (EntityPlayer) fake;

                    livingEntity = (EntityLivingBase) entityIn;
                    livingEntity.attackEntityFrom(DamageSource.causePlayerDamage(player), spikeType);

                }
            } else if (entityIn instanceof EntityPlayer) entityIn.attackEntityFrom(DamageSource.GENERIC, spikeType);
        } else entityIn.attackEntityFrom(DamageSource.GENERIC, spikeType);

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    //for the purposes of skyblocks, mobs can spawn on this block
    @Override
    public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return super.canCreatureSpawn(state, world, pos, type);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
