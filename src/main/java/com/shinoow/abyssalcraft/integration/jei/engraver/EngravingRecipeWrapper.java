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
package com.shinoow.abyssalcraft.integration.jei.engraver;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class EngravingRecipeWrapper extends BlankRecipeWrapper {

	@Nonnull
	private final List<List<ItemStack>> input;
	private final ItemStack engraving;
	@Nonnull
	private final List<ItemStack> output;

	@Nullable
	private final String experienceString;

	public EngravingRecipeWrapper(@Nonnull List<ItemStack> input, @Nonnull ItemStack engraving, @Nonnull ItemStack output, float experience) {
		this.input = Collections.singletonList(input);
		this.engraving = engraving;
		this.output = Collections.singletonList(output);

		if (experience > 0.0)
			experienceString = Translator.translateToLocalFormatted("gui.jei.category.smelting.experience", experience);
		else
			experienceString = null;
	}

	@Override
	@Nonnull
	public List<List<ItemStack>> getInputs() {
		return input;
	}

	@Nonnull
	public ItemStack getEngraving(){
		return engraving;
	}

	@Override
	@Nonnull
	public List<ItemStack> getOutputs() {
		return output;
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight) {
		if (experienceString != null) {
			FontRenderer fontRendererObj = minecraft.fontRendererObj;
			fontRendererObj.drawString(experienceString, 69 - fontRendererObj.getStringWidth(experienceString) / 2, 0, Color.gray.getRGB());
		}
	}
}
