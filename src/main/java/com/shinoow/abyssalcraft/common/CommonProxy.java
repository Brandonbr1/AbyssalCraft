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
package com.shinoow.abyssalcraft.common;

import com.shinoow.abyssalcraft.client.gui.*;
import com.shinoow.abyssalcraft.client.gui.necronomicon.GuiNecronomicon;
import com.shinoow.abyssalcraft.common.blocks.tile.*;
import com.shinoow.abyssalcraft.common.inventory.*;
import com.shinoow.abyssalcraft.common.items.ItemNecronomicon;
import com.shinoow.abyssalcraft.lib.ACLib;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);

		if(entity != null)
			switch(ID) {
			case ACLib.crystallizerGuiID:
				if (entity instanceof TileEntityCrystallizer)
					return new ContainerCrystallizer(player.inventory, (TileEntityCrystallizer) entity);
				break;
			case ACLib.transmutatorGuiID:
				if (entity instanceof TileEntityTransmutator)
					return new ContainerTransmutator(player.inventory, (TileEntityTransmutator) entity);
				break;
			case ACLib.engraverGuiID:
				if (entity instanceof TileEntityEngraver)
					return new ContainerEngraver(player.inventory, (TileEntityEngraver) entity);
				break;
			case ACLib.materializerGuiID:
				if (entity instanceof TileEntityMaterializer)
					return new ContainerMaterializer(player.inventory, (TileEntityMaterializer) entity);
				break;
			case ACLib.energycontainerGuiID:
				if (entity instanceof TileEntityEnergyContainer)
					return new ContainerEnergyContainer(player.inventory, (TileEntityEnergyContainer) entity);
				break;
			case ACLib.rendingPedestalGuiID:
				if (entity instanceof TileEntityRendingPedestal)
					return new ContainerRendingPedestal(player.inventory, (TileEntityRendingPedestal) entity);
				break;
			case ACLib.stateTransformerGuiID:
				if (entity instanceof TileEntityStateTransformer)
					return new ContainerStateTransformer(player.inventory, (TileEntityStateTransformer)entity);
				break;
			case ACLib.energyDepositionerGuiID:
				if (entity instanceof TileEntityEnergyDepositioner)
					return new ContainerEnergyDepositioner(player.inventory, (TileEntityEnergyDepositioner)entity);
				break;
			}
		if(!stack.isEmpty())
			switch(ID){
			case ACLib.crystalbagGuiID:
				return new ContainerCrystalBag(player.inventory, new InventoryCrystalBag(stack));
			case ACLib.necronomiconspellbookGuiID:
				if(stack.getItem() instanceof ItemNecronomicon && ((ItemNecronomicon)stack.getItem()).isOwner(player, stack))
					return new ContainerSpellbook(player, stack);
			}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);

		if(entity != null)
			switch(ID) {
			case ACLib.crystallizerGuiID:
				if (entity instanceof TileEntityCrystallizer)
					return new GuiCrystallizer(player.inventory, (TileEntityCrystallizer) entity);
				break;
			case ACLib.transmutatorGuiID:
				if (entity instanceof TileEntityTransmutator)
					return new GuiTransmutator(player.inventory, (TileEntityTransmutator) entity);
				break;
			case ACLib.engraverGuiID:
				if (entity instanceof TileEntityEngraver)
					return new GuiEngraver(player.inventory, (TileEntityEngraver) entity);
				break;
			case ACLib.materializerGuiID:
				if (entity instanceof TileEntityMaterializer)
					return new GuiMaterializer(player.inventory, (TileEntityMaterializer) entity);
				break;
			case ACLib.energycontainerGuiID:
				if (entity instanceof TileEntityEnergyContainer)
					return new GuiEnergyContainer(player.inventory, (TileEntityEnergyContainer) entity);
				break;
			case ACLib.rendingPedestalGuiID:
				if (entity instanceof TileEntityRendingPedestal)
					return new GuiRendingPedestal(player.inventory, (TileEntityRendingPedestal) entity);
				break;
			case ACLib.stateTransformerGuiID:
				if (entity instanceof TileEntityStateTransformer)
					return new GuiStateTransformer(player.inventory, (TileEntityStateTransformer) entity);
				break;
			case ACLib.energyDepositionerGuiID:
				if (entity instanceof TileEntityEnergyDepositioner)
					return new GuiEnergyDepositioner(player.inventory, (TileEntityEnergyDepositioner)entity);
				break;
			}
		if(!stack.isEmpty())
			switch(ID){
			case ACLib.necronmiconGuiID:
				if(stack.getItem() instanceof ItemNecronomicon && ((ItemNecronomicon)stack.getItem()).isOwner(player, stack))
					return GuiNecronomicon.currentNecro.withBookType(((ItemNecronomicon)stack.getItem()).getBookType());
				break;
			case ACLib.crystalbagGuiID:
				return new GuiCrystalBag(new ContainerCrystalBag(player.inventory, new InventoryCrystalBag(stack)));
			case ACLib.necronomiconspellbookGuiID:
				if(stack.getItem() instanceof ItemNecronomicon && ((ItemNecronomicon)stack.getItem()).isOwner(player, stack))
					return new GuiSpellbook(new ContainerSpellbook(player, stack));
			}
		return null;
	}

	public void preInit() {}

	public void init() {}

	public void postInit() {}

	public ModelBiped getArmorModel(int id){
		return null;
	}

	/**
	 * Returns a side-appropriate EntityPlayer for use during message handling
	 */
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().player;
	}

	/**
	 * Returns the current thread based on side during message handling,
	 * used for ensuring that the message is being handled by the main thread
	 */
	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return ctx.getServerHandler().player.getServer();
	}

	public void spawnParticle(String particleName, double posX, double posY, double posZ, double velX, double velY, double velZ) {}
}
