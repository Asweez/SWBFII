package com.apmods.swbf2.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.apmods.swbf2.entity.EntityDetpack;

public class ItemDetpack extends ItemBase{

	public ItemDetpack(String name) {
		super(name);
		this.setMaxStackSize(6);
	}
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
		EntityDetpack detpack = new EntityDetpack(world, player, 0.3f);
		if(!world.isRemote){
			world.spawnEntityInWorld(detpack);
		}
		ItemStack remote = new ItemStack(ItemManager.detonator);
		remote.setTagCompound(new NBTTagCompound());
		remote.getTagCompound().setInteger("detpackID", detpack.getEntityId());
		remote.getTagCompound().setInteger("detpackStackSize", is.stackSize - 1);
		return remote;
    }

}
