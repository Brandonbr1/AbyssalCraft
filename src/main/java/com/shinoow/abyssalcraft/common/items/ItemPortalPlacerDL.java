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

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

import com.shinoow.abyssalcraft.AbyssalCraft;

public class ItemPortalPlacerDL extends Item {

	public ItemPortalPlacerDL() {
		super();
		maxStackSize = 1;
		setUnlocalizedName("gatewaykeydl");
		setCreativeTab(AbyssalCraft.tabTools);
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack) {

		return EnumChatFormatting.DARK_RED + StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
	}

	@Override
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack par1ItemStack, EntityPlayer entityplayer, List list, boolean is){
		list.add(StatCollector.translateToLocal("tooltip.portalplacerdl.1"));
		list.add(StatCollector.translateToLocal("tooltip.portalplacerdl.2"));
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
		if(!world.isRemote){
			if(player.dimension == AbyssalCraft.configDimId1 || player.dimension == AbyssalCraft.configDimId2)
			{
				int direction = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

				if(direction == 1 || direction == 3)
				{
					for(int y = 1; y < 5; y++)
						for (int z = -1; z < 2; z++)
							if(!world.isAirBlock(pos.add(0, y, z)))
								return false;

					world.setBlockState(pos.add(0, 1, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(0, 1, 1), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(0, 1, 2), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(0, 1, -1), AbyssalCraft.dreadstone.getDefaultState());

					world.setBlockState(pos.add(0, 2, -1), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(0, 3, -1), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(0, 4, -1), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(0, 5, -1), AbyssalCraft.dreadstone.getDefaultState());

					world.setBlockState(pos.add(0, 2, 2), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(0, 3, 2), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(0, 4, 2), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(0, 5, 2), AbyssalCraft.dreadstone.getDefaultState());

					world.setBlockState(pos.add(0, 5, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(0, 5, 1), AbyssalCraft.dreadstone.getDefaultState());

					world.setBlockState(pos.add(0, 2, 1), AbyssalCraft.dreadfire.getDefaultState());
				}
				else
				{
					for(int y = 1; y < 5; y++)
						for (int x = -1; x < 2; x++)
							if(!world.isAirBlock(pos.add(x, y, 0)))
								return false;

					world.setBlockState(pos.add(0, 1, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(1, 1, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(2, 1, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(-1, 1, 0), AbyssalCraft.dreadstone.getDefaultState());

					world.setBlockState(pos.add(-1, 2, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(-1, 3, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(-1, 4, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(-1, 5, 0), AbyssalCraft.dreadstone.getDefaultState());

					world.setBlockState(pos.add(2, 2, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(2, 3, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(2, 4, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(2, 5, 0), AbyssalCraft.dreadstone.getDefaultState());

					world.setBlockState(pos.add(0, 5, 0), AbyssalCraft.dreadstone.getDefaultState());
					world.setBlockState(pos.add(1, 5, 0), AbyssalCraft.dreadstone.getDefaultState());

					world.setBlockState(pos.add(1, 2, 0), AbyssalCraft.dreadfire.getDefaultState());
				}
				return true;
			}
		} else if(player.dimension == 0 || player.dimension == AbyssalCraft.configDimId3 || player.dimension == AbyssalCraft.configDimId4)
		{
			FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.portalplacer.error.2")));
			return false;
		} else if(player.dimension == AbyssalCraft.configDimId1 || player.dimension == AbyssalCraft.configDimId2){}
		else {
			FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(StatCollector.translateToLocal("message.portalplacer.error.1")));
			return false;
		}
		return false;
	}
}