/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2021 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.common.blocks.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * Some sort of universal metadata itemblock thingy
 * @deprecated should be removed post-flattening
 *
 */
@Deprecated
public class ItemMetadataBlock extends ItemBlockAC {

	private static final String[] subNames = {
			"0", "1",  "2", "3", "4", "5", "6", "7",
			"8", "9", "10", "11", "12", "13", "14", "15"};

	public ItemMetadataBlock(Block b) {
		super(b);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return getUnlocalizedName() + "." + subNames[stack.getItemDamage()];
	}
}
