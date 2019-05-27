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
package com.shinoow.abyssalcraft.common.caps;

import java.util.*;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Tuple;

public class NecromancyCapability implements INecromancyCapability {

	List<Tuple<String, NBTTagCompound>> data = new ArrayList<>();
	Map<String, Integer> sizes = new HashMap<>();

	public static INecromancyCapability getCap(EntityPlayer player){
		return player.getCapability(NecromancyCapabilityProvider.NECROMANCY_CAP, null);
	}

	@Override
	public NBTTagCompound getDataForName(String name) {

		for(Tuple<String, NBTTagCompound> t : data)
			if(t.getFirst().equals(name))
				return t.getSecond();
		return null;
	}

	@Override
	public int getSizeForName(String name){
		return sizes.get(name) != null ? sizes.get(name) : 0;
	}

	@Override
	public void storeData(String name, NBTTagCompound data, int size) {

		if(getDataForName(name) == null){
			if(this.data.size() == 5){
				sizes.remove(this.data.get(0).getFirst());
				this.data.remove(0);
			}
			this.data.add(new Tuple(name, data));
		} else
			for(Tuple<String, NBTTagCompound> t : this.data)
				if(t.getFirst().equals(name)){
					t = new Tuple(name, data);
					break;
				}
		sizes.put(name, size);
	}

	@Override
	public void clearEntry(String name) {
		for(Tuple<String, NBTTagCompound> t : data)
			if(t.getFirst().equals(name)){
				data.remove(t);
				break;
			}
		sizes.remove(name);
	}

	@Override
	public List<Tuple<String, NBTTagCompound>> getData() {

		return data;
	}

	@Override
	public Map<String, Integer> getSizeData() {

		return sizes;
	}

	@Override
	public void copy(INecromancyCapability cap) {
		data = cap.getData();
		sizes = cap.getSizeData();
	}
}
