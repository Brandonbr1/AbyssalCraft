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
package com.shinoow.abyssalcraft.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.shinoow.abyssalcraft.client.model.entity.ModelShadowBeast;
import com.shinoow.abyssalcraft.common.entity.EntityShadowBeast;

@SideOnly(Side.CLIENT)
public class RenderShadowBeast extends RenderLiving {

	private static final ResourceLocation mobTexture = new ResourceLocation("abyssalcraft:textures/model/elite/ShadowBeast.png");

	public RenderShadowBeast(RenderManager manager)
	{
		super(manager, new ModelShadowBeast(), 0.5F);
	}

	public void doRender(EntityShadowBeast entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return mobTexture;
	}
}
