package com.apmods.swbf2.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.apmods.swbf2.entity.EntityIFT;

public class ItemIFT extends ItemBase{
	public String letter;
	public ItemIFT(String name, String l) {
		super(name);
		letter = l;
		this.setMaxStackSize(1);
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!player.capabilities.isCreativeMode)
		{
			--stack.stackSize;
		}

		if (!world.isRemote)
		{
			EntityIFT IFT = new EntityIFT(world, pos.getX(), pos.getY(), pos.getZ(), player.getName(), letter);
			IFT.rotationYaw = (float)((((MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90) - 90);
			world.spawnEntityInWorld(IFT); 
		}

		if(player.capabilities.isCreativeMode)
		{
			stack.stackSize--;
		}

		return true;
	}
}
