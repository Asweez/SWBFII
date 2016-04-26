package com.apmods.swbf2.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.apmods.swbf2.entity.EntityBullet;
import com.apmods.swbf2.main.Battlefront;

public class ItemMinigun extends ItemBase implements IBlasterRifle{

	/**The max amount of bullets this gun can hold*/
	public static final int maxAmmo = 10000;
	/**The max amount of bullets a chamber can hold*/
	public static final int chamberBullets = 50;
	/**Ticks in between shots*/
	public static final int RoF = 1;
	/**Ticks to reload*/
	public static final int reloadTime = 60;
	
	
	public ItemMinigun(String name) {
		super(name);
		this.maxStackSize = 1;
		this.setItemTooltip("Infinite Ammo");
	}
	
	public void onUsingTick(ItemStack is, EntityPlayer player, int count)
    {
		World world = player.worldObj;
			is.getTagCompound().setInteger("warmUp", 100 - count+1);
		if(this.canFire(is)){
			if(count % 3 == 0){
	    		is.getTagCompound().setInteger("rof", this.getRoF());
	    		int cb1 = is.getTagCompound().getInteger("chamberammo");
	    		int tb1 = is.getTagCompound().getInteger("totalammo");
	    		is.getTagCompound().setInteger("chamberammo", cb1 - 1);
	    		int cb = is.getTagCompound().getInteger("chamberammo");
	    		int tb = is.getTagCompound().getInteger("totalammo");
	    		if(cb <= 0){
	    			is.getTagCompound().setInteger("reloadTime", this.getReloadTime());
	    		}
	    		EntityBullet b0 = new EntityBullet(world, player, 3f, 4f);
	    		EntityBullet b1 = new EntityBullet(world, player, 3f, 1f);
	    		EntityBullet b2 = new EntityBullet(world, player, 3f, 2.5f);
	    		if(!world.isRemote){
	    			world.spawnEntityInWorld(b0);
	    			world.spawnEntityInWorld(b1);
	    			world.spawnEntityInWorld(b2);
	    			world.playSoundAtEntity(b0, Battlefront.MODID + ":chaingun", 1.0f, 1.0f);
	    		}
			}
		}
		if(is.getTagCompound().getInteger("chamberammo") == 0){
			player.stopUsingItem();
		}
    }
	public void onPlayerStoppedUsing(ItemStack is, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
		is.getTagCompound().setInteger("warmUp", 0);
	}
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
    	player.setItemInUse(is, this.getMaxItemUseDuration(is));
        return is;
    }
    
    public boolean showDurabilityBar(ItemStack stack)
    {
    	if(stack.getTagCompound() != null){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    /**
     * Queries the percentage of the 'Durability' bar that should be drawn.
     * 
     * @param stack The current ItemStack
     * @return 1.0 for 100% 0 for 0%
     */
    public double getDurabilityForDisplay(ItemStack stack)
    {
    	double f = (double)this.getAmmoForDisplay(stack) / (double)this.getMaxChamberAmmo();
        return f;
    }
	/**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack is, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
    	if(entity instanceof EntityPlayer){
    		EntityPlayer player = (EntityPlayer) entity;
    		if(is.getTagCompound() == null){
    			is.setTagCompound(new NBTTagCompound());
    			is.getTagCompound().setInteger("chamberammo", this.getMaxChamberAmmo());
    			is.getTagCompound().setInteger("totalammo", this.getMaxAmmo());
    			is.getTagCompound().setInteger("rof", 0);
    			is.getTagCompound().setInteger("reloadTime", 0);
    			is.getTagCompound().setInteger("warmUp", 0);
    		}
    		else{
    			if(is.getTagCompound().getInteger("rof") > 0){
    				int rof = is.getTagCompound().getInteger("rof");
    				is.getTagCompound().setInteger("rof", rof - 1);
    			}
    			if(is.getTagCompound().getInteger("reloadTime") > 0){
    				int re = is.getTagCompound().getInteger("reloadTime");
    				if(re == 1){
    					is.getTagCompound().setInteger("chamberammo", this.getMaxChamberAmmo());
    				}
    				is.getTagCompound().setInteger("reloadTime", re - 1);
    			}
//    			if(is.getTagCompound().getInteger("warmUp") > 0){
//    				int wu = is.getTagCompound().getInteger("warmUp");
//    				is.getTagCompound().setInteger("warmUp", wu - 1);
////    				if(wu == 1){
////    					player.setItemInUse(is, this.getMaxItemUseDuration(is));
////    				}
//    			}
    		}
    	}
    }

	@Override
	public int getMaxAmmo() {
		return this.maxAmmo;
	}

	@Override
	public int getMaxChamberAmmo() {
		return this.chamberBullets;
	}

	@Override
	public int getRoF() {
		return this.RoF;
	}

	@Override
	public int getReloadTime() {
		return this.reloadTime;
	}
	public boolean canFire(ItemStack is){
		return is.getTagCompound().getInteger("totalammo") > 0 && is.getTagCompound().getInteger("reloadTime") == 0 && is.getTagCompound().getInteger("rof") == 0 && is.getTagCompound().getInteger("warmUp") >= 30;
	}
    private int getAmmoForDisplay(ItemStack is){
    	return this.getMaxChamberAmmo() - is.getTagCompound().getInteger("chamberammo");
    }

	@Override
	public EntityThrowable getBullet(EntityPlayer player, World world) {
		return new EntityBullet(world, player, 3f, 2f);
	}
	public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 100;
    }

	@Override
	public String getSound() {
		return "chaingun";
	}

    
	

}
