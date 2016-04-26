package com.apmods.swbf2.item;

import com.apmods.swbf2.entity.EntityBullet;
import com.apmods.swbf2.entity.EntitySniperBullet;
import com.apmods.swbf2.main.Battlefront;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemSniperRifle extends ItemBlaster implements IBlasterRifle{
	
	public ItemSniperRifle(String name) {
		super(name);
		// TODO Auto-generated constructor stub
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
    	super.onUpdate(is, world, entity, p_77663_4_, p_77663_5_);
    }
    public boolean canFire(ItemStack is){
		return is.getTagCompound().getInteger("totalammo") > 0 && is.getTagCompound().getInteger("reloadTime") == 0 && is.getTagCompound().getInteger("rof") == 0;
	}
    private int getAmmoForDisplay(ItemStack is){
    	return this.getMaxChamberAmmo() - is.getTagCompound().getInteger("chamberammo");
    }
	@Override
	public int getMaxAmmo() {
		return 35;
	}

	@Override
	public int getMaxChamberAmmo() {
		return 5;
	}

	@Override
	public int getRoF() {
		return 20;
	}
	public String getSound(){
		return "sniper";
	}

	@Override
	public int getReloadTime() {
		return 100;
	}
	
	@Override
	public EntityThrowable getBullet(EntityPlayer player, World world) {
		return new EntitySniperBullet(world, player, 10.0f, 2f);
	}


}
