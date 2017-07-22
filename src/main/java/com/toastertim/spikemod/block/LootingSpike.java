package com.toastertim.spikemod.block;

import com.toastertim.spikemod.Config;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;


public class LootingSpike extends BlockSpike {

	public LootingSpike() {
		super(SpikeTypes.LOOTING, Material.IRON, SoundType.METAL);
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


}
