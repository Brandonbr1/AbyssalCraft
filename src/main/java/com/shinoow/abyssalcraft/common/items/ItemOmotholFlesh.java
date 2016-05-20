/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2016 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.common.util.EntityUtil;

public class ItemOmotholFlesh extends ItemFood {

	public ItemOmotholFlesh(int par1, float par2, boolean par3) {
		super(par1, par2, par3);
		//		GameRegistry.registerItem(this, "omotholflesh");
		setUnlocalizedName("omotholflesh");
		//		setTextureName("abyssalcraft:" + "omotholflesh");
		setCreativeTab(AbyssalCraft.tabFood);
	}

	@Override
	public void onFoodEaten(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		world.playSound(entityPlayer, entityPlayer.getPosition(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		if(EntityUtil.isPlayerCoralium(entityPlayer)){
			entityPlayer.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100));
			entityPlayer.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 300, 1));
			entityPlayer.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200));
		} else {
			entityPlayer.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100));
			entityPlayer.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 400, 1));
			entityPlayer.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 300));
		}

		entityPlayer.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 40));
		entityPlayer.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 40));
	}
}