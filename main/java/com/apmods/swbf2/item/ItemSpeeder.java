package com.apmods.swbf2.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.apmods.swbf2.entity.EntitySpeeder;

public class ItemSpeeder extends ItemBase{
	public ItemSpeeder(String name) {
		super(name);
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
			EntitySpeeder speeder = new EntitySpeeder(world, pos.getX(), pos.getY() + 1, pos.getZ(), player.getName());
			speeder.rotationYaw = (float)((((MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90) - 90);
			world.spawnEntityInWorld(speeder); 
		}

		if(player.capabilities.isCreativeMode)
		{
			stack.stackSize--;
		}

		return true;
	}
}
