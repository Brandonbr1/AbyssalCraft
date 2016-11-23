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
package com.shinoow.abyssalcraft.integration.jei.ritual;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;
import com.shinoow.abyssalcraft.api.APIUtils;
import com.shinoow.abyssalcraft.api.item.ACItems;
import com.shinoow.abyssalcraft.integration.jei.AbyssalCraftRecipeCategoryUid;

public class RitualRecipeCategory implements IRecipeCategory {

	private static final int pedestal1 = 0;
	private static final int pedestal2 = 1;
	private static final int pedestal3 = 2;
	private static final int pedestal4 = 3;
	private static final int pedestal5 = 4;
	private static final int pedestal6 = 5;
	private static final int pedestal7 = 6;
	private static final int pedestal8 = 7;
	private static final int sacrifice = 8;
	private static final int reward = 9;
	private static final int necronomicon = 10;

	@Nonnull
	private final IDrawable background;
	@Nonnull
	private final IDrawable slotDrawable;
	@Nonnull
	private final String localizedName;

	public RitualRecipeCategory(IGuiHelper guiHelper){
		ResourceLocation location = new ResourceLocation("abyssalcraft", "textures/gui/container/ritual_NEI.png");
		background = guiHelper.createDrawable(location, 5, 11, 166, 140);
		localizedName = I18n.translateToLocal("container.abyssalcraft.rituals.nei");

		slotDrawable = guiHelper.getSlotDrawable();
	}

	@Override
	public String getUid() {

		return AbyssalCraftRecipeCategoryUid.RITUAL;
	}

	@Override
	public String getTitle() {

		return localizedName;
	}

	@Override
	public IDrawable getBackground() {

		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {}

	@Override
	public void drawAnimations(Minecraft minecraft) {}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper) {
		if(recipeWrapper instanceof RitualRecipeWrapper){
			IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
			RitualRecipeWrapper wrapper = (RitualRecipeWrapper)recipeWrapper;
			int xBoost = 14, yBoost = -25;

			itemStacks.init(pedestal1, false, 58 + xBoost, 30 + yBoost);
			itemStacks.init(pedestal2, false, 84 + xBoost, 40 + yBoost);
			itemStacks.init(pedestal3, false, 94 + xBoost, 66 + yBoost);
			itemStacks.init(pedestal4, false, 84 + xBoost, 92 + yBoost);
			itemStacks.init(pedestal5, false, 58 + xBoost, 103 + yBoost);
			itemStacks.init(pedestal6, false, 32 + xBoost, 92 + yBoost);
			itemStacks.init(pedestal7, false, 22 + xBoost, 66 + yBoost);
			itemStacks.init(pedestal8, false, 32 + xBoost, 40 + yBoost);
			itemStacks.init(sacrifice, false, 58 + xBoost, 66 + yBoost);
			itemStacks.init(necronomicon, false, 0 + xBoost, 133 + yBoost);
			itemStacks.init(reward, false, 58 + xBoost, 139 + yBoost);

			itemStacks.setFromRecipe(pedestal1, list(wrapper.getOfferings()[0]) ? getList(wrapper.getOfferings()[0]) : Collections.singletonList(APIUtils.convertToStack(wrapper.getOfferings()[0])));
			itemStacks.setFromRecipe(pedestal2, list(wrapper.getOfferings()[1]) ? getList(wrapper.getOfferings()[1]) : Collections.singletonList(APIUtils.convertToStack(wrapper.getOfferings()[1])));
			itemStacks.setFromRecipe(pedestal3, list(wrapper.getOfferings()[2]) ? getList(wrapper.getOfferings()[2]) : Collections.singletonList(APIUtils.convertToStack(wrapper.getOfferings()[2])));
			itemStacks.setFromRecipe(pedestal4, list(wrapper.getOfferings()[3]) ? getList(wrapper.getOfferings()[3]) : Collections.singletonList(APIUtils.convertToStack(wrapper.getOfferings()[3])));
			itemStacks.setFromRecipe(pedestal5, list(wrapper.getOfferings()[4]) ? getList(wrapper.getOfferings()[4]) : Collections.singletonList(APIUtils.convertToStack(wrapper.getOfferings()[4])));
			itemStacks.setFromRecipe(pedestal6, list(wrapper.getOfferings()[5]) ? getList(wrapper.getOfferings()[5]) : Collections.singletonList(APIUtils.convertToStack(wrapper.getOfferings()[5])));
			itemStacks.setFromRecipe(pedestal7, list(wrapper.getOfferings()[6]) ? getList(wrapper.getOfferings()[6]) : Collections.singletonList(APIUtils.convertToStack(wrapper.getOfferings()[6])));
			itemStacks.setFromRecipe(pedestal8, list(wrapper.getOfferings()[7]) ? getList(wrapper.getOfferings()[7]) : Collections.singletonList(APIUtils.convertToStack(wrapper.getOfferings()[7])));
			itemStacks.setFromRecipe(sacrifice, APIUtils.convertToStack(wrapper.getSacrifice()));
			itemStacks.setFromRecipe(necronomicon, getItem(wrapper.getBookType()));
			itemStacks.setFromRecipe(reward, wrapper.getOutputs());
		}
	}

	private ItemStack getItem(int par1){
		switch(par1){
		case 0:
			return new ItemStack(ACItems.necronomicon);
		case 1:
			return new ItemStack(ACItems.abyssal_wasteland_necronomicon);
		case 2:
			return new ItemStack(ACItems.dreadlands_necronomicon);
		case 3:
			return new ItemStack(ACItems.omothol_necronomicon);
		case 4:
			return new ItemStack(ACItems.abyssalnomicon);
		default:
			return new ItemStack(ACItems.necronomicon);
		}
	}

	private boolean list(Object obj){
		return obj == null ? false : obj instanceof ItemStack[] || obj instanceof String || obj instanceof List;
	}

	private List<ItemStack> getList(Object obj){
		if(obj instanceof ItemStack[])
			return Lists.newArrayList((ItemStack[])obj);
		if(obj instanceof String)
			return OreDictionary.getOres((String)obj);
		if(obj instanceof List)
			return (List)obj;
		return Collections.emptyList();
	}
}
