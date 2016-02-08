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
package com.shinoow.abyssalcraft.common.world.gen;

import java.util.Random;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.common.blocks.tile.TEDirectional;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenShoggothMonolith extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {

		while(world.isAirBlock(pos) && pos.getY() > 2)
			pos = pos.down();

		//	if(world.getBlock(x, y, z).getMaterial() != Material.grass ||
		//	world.getBlock(x, y, z).getMaterial() != Material.ground)
		if(world.getBlockState(pos) != AbyssalCraft.shoggothBlock.getDefaultState())
			return false;
		else {

			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			//	for(int x1 = 0; x1 < 7; x1++)
			//	for(int z1 = 0; z1 < 7; z1++)
			//	for(int x2 = 0; x2 < 7; x2++)
			//	for(int z2 = 0; z2 < 7; z2++)
			//	for(int y1 = -1; y1 < 4; y1++){
			//	if(world.getBlock(x + x1, y + y1, z + z1).getMaterial() == Material.grass ||
			//	world.getBlock(x + x1, y + y1, z + z1).getMaterial() == Material.ground)
			//	func_150515_a(world,x + x1, y + y1, z + z1, AbyssalCraft.shoggothBlock);
			//	if(world.getBlock(x - x2, y + y1, z - z2).getMaterial() == Material.grass ||
			//	world.getBlock(x - x2, y + y1, z - z2).getMaterial() == Material.ground)
			//	func_150515_a(world,x - x2, y + y1, z - z2, AbyssalCraft.shoggothBlock);
			//	if(world.getBlock(x + x1, y + y1, z - z2).getMaterial() == Material.grass ||
			//	world.getBlock(x + x1, y + y1, z - z2).getMaterial() == Material.ground)
			//	func_150515_a(world,x + x1, y + y1, z - z2, AbyssalCraft.shoggothBlock);
			//	if(world.getBlock(x - x2, y + y1, z  + z1).getMaterial() == Material.grass ||
			//	world.getBlock(x - x2, y + y1, z  + z1).getMaterial() == Material.ground)
			//	func_150515_a(world,x - x2, y + y1, z  + z1, AbyssalCraft.shoggothBlock);
			//	}

			int max = rand.nextInt(8) + 5;
			for(int i = 0; i < max; i++){
				setBlockAndNotifyAdequately(world, new BlockPos(x, y + i, z), AbyssalCraft.monolithStone.getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + i, z), AbyssalCraft.monolithStone.getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x -1, y + i, z), AbyssalCraft.monolithStone.getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x, y + i, z + 1), AbyssalCraft.monolithStone.getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x, y + i, z -1), AbyssalCraft.monolithStone.getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + i, z + 1), AbyssalCraft.monolithStone.getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x -1, y + i, z -1), AbyssalCraft.monolithStone.getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x + 1, y + i, z -1), AbyssalCraft.monolithStone.getDefaultState());
				setBlockAndNotifyAdequately(world, new BlockPos(x -1, y + i, z + 1), AbyssalCraft.monolithStone.getDefaultState());
			}
			setBlockAndNotifyAdequately(world, pos, AbyssalCraft.shoggothBiomass.getDefaultState());
			setBlockAndNotifyAdequately(world, new BlockPos(x, y + max, z), getStatue(rand).getDefaultState());
			TileEntity te = world.getTileEntity(new BlockPos(x, y + max, z));

			if(te != null && te instanceof TEDirectional)
				((TEDirectional) te).setDirection(rand.nextInt(3));

			world.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "random.anvil_use", 2, world.rand.nextFloat() * 0.1F * 0.9F);

			return true;
		}
	}

	private Block getStatue(Random rand){
		switch(rand.nextInt(7)){
		case 0:
			return AbyssalCraft.cthulhuStatue;
		case 1:
			return AbyssalCraft.hasturStatue;
		case 2:
			return AbyssalCraft.jzaharStatue;
		case 3:
			return AbyssalCraft.azathothStatue;
		case 4:
			return AbyssalCraft.nyarlathotepStatue;
		case 5:
			return AbyssalCraft.yogsothothStatue;
		case 6:
			return AbyssalCraft.shubniggurathStatue;
		default:
			return getStatue(rand);
		}
	}
}
