/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2019 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.network.server;

import java.io.IOException;

import com.shinoow.abyssalcraft.api.AbyssalCraftAPI;
import com.shinoow.abyssalcraft.api.entity.EntityUtil;
import com.shinoow.abyssalcraft.api.entity.IOmotholEntity;
import com.shinoow.abyssalcraft.common.network.AbstractMessage.AbstractServerMessage;
import com.shinoow.abyssalcraft.lib.ACLib;
import com.shinoow.abyssalcraft.lib.util.items.IStaffOfRending;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;

public class StaffOfRendingMessage extends AbstractServerMessage<StaffOfRendingMessage> {

	int id;
	EnumHand hand;

	public StaffOfRendingMessage(){}

	public StaffOfRendingMessage(int id, EnumHand hand){
		this.id = id;
		this.hand = hand;
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {

		id = ByteBufUtils.readVarInt(buffer, 5);
		hand = ByteBufUtils.readVarInt(buffer, 5) == 0 ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {

		ByteBufUtils.writeVarInt(buffer, id, 5);
		ByteBufUtils.writeVarInt(buffer, hand == EnumHand.MAIN_HAND ? 0 : 1, 5);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		if(player.getHeldItem(hand) == null) return;
		ItemStack stack = player.getHeldItem(hand);
		Entity e = player.world.getEntityByID(id);
		if(e == null) return;

		if(stack.getItem() instanceof IStaffOfRending)
			if(e instanceof EntityLiving){
				IStaffOfRending staff = (IStaffOfRending)stack.getItem();
				EntityLiving target = (EntityLiving)e;
				if(target.getCreatureAttribute() == AbyssalCraftAPI.SHADOW && target.isNonBoss()){
					if(!target.isDead)
						if(target.attackEntityFrom(DamageSource.causePlayerDamage(player), staff.getDrainAmount(stack)))
							staff.increaseEnergy(stack, "Shadow");
				} else if(player.world.provider.getDimension() == ACLib.abyssal_wasteland_id && EntityUtil.isCoraliumPlagueCarrier(target) &&
						target.isNonBoss()){
					if(!target.isDead)
						if(target.attackEntityFrom(DamageSource.causePlayerDamage(player), staff.getDrainAmount(stack)))
							staff.increaseEnergy(stack, "Abyssal");
				} else if(player.world.provider.getDimension() == ACLib.dreadlands_id && EntityUtil.isDreadPlagueCarrier(target) &&
						target.isNonBoss()){
					if(!target.isDead)
						if(target.attackEntityFrom(DamageSource.causePlayerDamage(player), staff.getDrainAmount(stack)))
							staff.increaseEnergy(stack, "Dread");
				} else if(player.world.provider.getDimension() == ACLib.omothol_id && target instanceof IOmotholEntity &&
						target.getCreatureAttribute() != AbyssalCraftAPI.SHADOW && target.isNonBoss())
					if(!target.isDead)
						if(target.attackEntityFrom(DamageSource.causePlayerDamage(player), staff.getDrainAmount(stack)))
							staff.increaseEnergy(stack, "Omothol");
			} else if(e instanceof MultiPartEntityPart && ((MultiPartEntityPart) e).parent instanceof EntityLiving) {
				IStaffOfRending staff = (IStaffOfRending)stack.getItem();
				MultiPartEntityPart target = (MultiPartEntityPart)e;
				EntityLiving parent = (EntityLiving) target.parent;
				if(parent.getCreatureAttribute() == AbyssalCraftAPI.SHADOW && parent.isNonBoss()){
					if(!target.isDead)
						if(target.attackEntityFrom(DamageSource.causePlayerDamage(player), staff.getDrainAmount(stack)))
							staff.increaseEnergy(stack, "Shadow");
				} else if(player.world.provider.getDimension() == ACLib.abyssal_wasteland_id && EntityUtil.isCoraliumPlagueCarrier(parent) &&
						parent.isNonBoss()){
					if(!target.isDead)
						if(target.attackEntityFrom(DamageSource.causePlayerDamage(player), staff.getDrainAmount(stack)))
							staff.increaseEnergy(stack, "Abyssal");
				} else if(player.world.provider.getDimension() == ACLib.dreadlands_id && EntityUtil.isDreadPlagueCarrier(parent) &&
						parent.isNonBoss()){
					if(!target.isDead)
						if(target.attackEntityFrom(DamageSource.causePlayerDamage(player), staff.getDrainAmount(stack)))
							staff.increaseEnergy(stack, "Dread");
				} else if(player.world.provider.getDimension() == ACLib.omothol_id && parent instanceof IOmotholEntity &&
						parent.getCreatureAttribute() != AbyssalCraftAPI.SHADOW && parent.isNonBoss())
					if(!target.isDead)
						if(target.attackEntityFrom(DamageSource.causePlayerDamage(player), staff.getDrainAmount(stack)))
							staff.increaseEnergy(stack, "Omothol");
			}
	}
}
