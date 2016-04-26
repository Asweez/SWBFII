package com.apmods.swbf2.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.apmods.swbf2.entity.EntityDetpack;

public class ItemRemoteDetonator extends ItemBase{

	public ItemRemoteDetonator(String name) {
		super(name);
		this.setMaxStackSize(1);
		this.setCreativeTab(null);
	}
	public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		if(is.getTagCompound() == null){
			is.setTagCompound(new NBTTagCompound());
			is.getTagCompound().setInteger("detpackID", 0);
		}
	}
	
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
		Entity entity = world.getEntityByID(is.getTagCompound().getInteger("detpackID"));
		if(entity instanceof EntityDetpack){
			EntityDetpack detpack = (EntityDetpack) entity;
			detpack.detonate();
		}
		ItemStack is1 = new ItemStack(ItemManager.detpack);
		is1.stackSize = is.getTagCompound().getInteger("detpackStackSize");
		return is1;
    }

}
