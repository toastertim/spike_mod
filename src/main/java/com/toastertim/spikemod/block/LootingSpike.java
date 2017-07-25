package com.toastertim.spikemod.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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

	private static ItemStack lootStick = new ItemStack(Items.STICK);
	
	public LootingSpike() {
		super(SpikeTypes.LOOTING, Material.IRON, SoundType.METAL);
		lootStick.addEnchantment(Enchantments.LOOTING, 3);
	}

	@Override
	public void handleSpikeDamage(World world, BlockPos pos, EntityLivingBase entity) {
			if (!(entity instanceof EntityPlayer)) {
				FakePlayer player = FakePlayerFactory.getMinecraft((WorldServer) world);
				player.setHeldItem(EnumHand.MAIN_HAND, lootStick);
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), type.getDamage());
				player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
			}
			entity.attackEntityFrom(DamageSource.CACTUS, type.getDamage());
	}


}
