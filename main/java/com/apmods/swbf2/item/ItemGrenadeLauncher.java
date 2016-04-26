package com.apmods.swbf2.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.apmods.swbf2.entity.EntityBullet;
import com.apmods.swbf2.entity.EntityGrenade;
import com.apmods.swbf2.entity.EntityRocket;
import com.apmods.swbf2.main.Battlefront;

public class ItemGrenadeLauncher extends ItemBlaster{

	public ItemGrenadeLauncher(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	 public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
	    {
	    	if(this.canFire(is)){
	    		is.getTagCompound().setInteger("rof", this.getRoF());
	    		int cb1 = is.getTagCompound().getInteger("chamberammo");
	    		int tb1 = is.getTagCompound().getInteger("totalammo");
	    		is.getTagCompound().setInteger("chamberammo", cb1 - 1);
	    		is.getTagCompound().setInteger("totalammo", tb1 - 1);
	    		int cb = is.getTagCompound().getInteger("chamberammo");
	    		int tb = is.getTagCompound().getInteger("totalammo");
	    		if(cb <= 0){
	    			is.getTagCompound().setInteger("reloadTime", this.getReloadTime());
	    		}
	    		EntityGrenade g = new EntityGrenade(world, player, 0.9f);
	    		if(!world.isRemote){
	    			world.spawnEntityInWorld(g);
	    			world.playSoundAtEntity(g, Battlefront.MODID + ":" + this.getSound(), 1.0f, 1.0f);
	    		}
	    	}
	    	else if(is.getTagCompound().getInteger("totalammo") <= 0){
	    		world.playSoundAtEntity(player, Battlefront.MODID + ":click", 1.0f, 1.0F);
	    	}
	        return is;
	    }
	
	@Override
	public int getMaxAmmo() {
		return 8;
	}

	@Override
	public int getMaxChamberAmmo() {
		return 2;
	}

	@Override
	public int getRoF() {
		return 70;
	}

	@Override
	public int getReloadTime() {
		return 180;
	}
	@Override
	public EntityThrowable getBullet(EntityPlayer player, World world) {
		return new EntityRocket(world, player, 1f);
	}
	public String getSound(){
		return "whoosh";
	}

}
