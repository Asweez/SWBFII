package com.apmods.swbf2.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.apmods.swbf2.entity.EntityGrenade;

public class ItemGrenade extends ItemBase{

	public ItemGrenade(String name) {
		super(name);
		this.setMaxStackSize(16);
	}
	
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
		EntityGrenade grenade = new EntityGrenade(world, player, 0.7f);
		if(!world.isRemote){

			world.spawnEntityInWorld(grenade);
			
		}
		--is.stackSize;
		return is;
    }

}
