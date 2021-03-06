package com.apmods.swbf2.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.apmods.swbf2.entity.EntityBowcasterBullet;
import com.apmods.swbf2.main.Battlefront;

public class ItemWookieBowcaster extends ItemBlaster implements IBlasterRifle{

	public ItemWookieBowcaster(String name) {
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
	    		EntityBowcasterBullet b0 = new EntityBowcasterBullet(world, player, 3f);
	    		EntityBowcasterBullet b1 = new EntityBowcasterBullet(world, player, 3f, 7 );
	    		EntityBowcasterBullet b2 = new EntityBowcasterBullet(world, player, 3f, 14);
	    		EntityBowcasterBullet b3 = new EntityBowcasterBullet(world, player, 3f, 21);
	    		EntityBowcasterBullet b4 = new EntityBowcasterBullet(world, player, 3f, 7);
	    		EntityBowcasterBullet b5 = new EntityBowcasterBullet(world, player, 3f, 14);
	    		EntityBowcasterBullet b6 = new EntityBowcasterBullet(world, player, 3f, 21);
	    		if(!world.isRemote){
	    			world.spawnEntityInWorld(b0);
	    			world.spawnEntityInWorld(b1);
	    			world.spawnEntityInWorld(b2);
	    			world.spawnEntityInWorld(b3);
	    			world.spawnEntityInWorld(b4);
	    			world.spawnEntityInWorld(b5);
	    			world.spawnEntityInWorld(b6);

	    			world.playSoundAtEntity(b0, Battlefront.MODID + ":" + this.getSound(), 1.0f, 1.0f);
	    		}
	    	}
	    	else if(is.getTagCompound().getInteger("totalammo") <= 0){
	    		world.playSoundAtEntity(player, Battlefront.MODID + ":click", 1.0f, 1.0F);
	    	}
	        return is;
	    }
	
	@Override
	public int getMaxAmmo() {
		return 200;
	}

	@Override
	public int getMaxChamberAmmo() {
		return 50;
	}

	@Override
	public int getRoF() {
		return 10;
	}

	@Override
	public int getReloadTime() {
		return 80;
	}
	public String getSound(){
		return "blaster";
	}

}
