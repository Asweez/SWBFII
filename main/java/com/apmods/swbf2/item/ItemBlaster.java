package com.apmods.swbf2.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.apmods.swbf2.entity.EntityBullet;
import com.apmods.swbf2.main.Battlefront;

public class ItemBlaster extends ItemBase implements IBlasterRifle{
	
	
	public ItemBlaster(String name) {
		super(name);
		this.maxStackSize = 1;
	}
	
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
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
    		EntityBullet b = (EntityBullet) this.getBullet(player, world);
    		if(!world.isRemote){
    			world.spawnEntityInWorld(b);
    			world.playSoundAtEntity(b, Battlefront.MODID + ":" + this.getSound(), 1.0f, 1.0f);
    		}
    	}
    	else if(is.getTagCompound().getInteger("totalammo") <= 0){
    		world.playSoundAtEntity(player, Battlefront.MODID + ":click", 1.0f, 1.0F);
    	}
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
    		}
    		else{
    			if(is.getTagCompound().getInteger("rof") > 0){
    				int rof = is.getTagCompound().getInteger("rof");
    				is.getTagCompound().setInteger("rof", rof - 1);
    			}
    			if(is.getTagCompound().getInteger("reloadTime") > 0){
    				int re = is.getTagCompound().getInteger("reloadTime");
    				if(re == 1){
    					if(is.getTagCompound().getInteger("totalammo") >= this.getMaxChamberAmmo()){
    						is.getTagCompound().setInteger("chamberammo", this.getMaxChamberAmmo());
    					}
    					else{
    						is.getTagCompound().setInteger("chamberammo", is.getTagCompound().getInteger("totalammo"));
    					}
    				}
    				is.getTagCompound().setInteger("reloadTime", re - 1);
    			}
    		}
    	}
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
		return 7;
	}

	@Override
	public int getReloadTime() {
		return 60;
	}
	public boolean canFire(ItemStack is){
		return is.getTagCompound().getInteger("totalammo") > 0 && is.getTagCompound().getInteger("reloadTime") == 0 && is.getTagCompound().getInteger("rof") == 0;
	}
	public String getSound(){
		return "blaster";
	}
    private int getAmmoForDisplay(ItemStack is){
    	return this.getMaxChamberAmmo() - is.getTagCompound().getInteger("chamberammo");
    }

	@Override
	public EntityThrowable getBullet(EntityPlayer player, World world) {
		return new EntityBullet(world, player, 3f);
	}

    
	

}
