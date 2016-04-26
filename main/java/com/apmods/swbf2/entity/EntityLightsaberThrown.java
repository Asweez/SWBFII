package com.apmods.swbf2.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

import com.apmods.swbf2.item.ItemManager;

public class EntityLightsaberThrown extends EntityThrowable implements IProjectile {

	private int lightsaber, timer;
	private boolean hasHit;

	public EntityLightsaberThrown(World world){
		super(world);
	}

	public EntityLightsaberThrown(World p_i1777_1_, EntityLivingBase p_i1777_2_, int item) {
		super(p_i1777_1_, p_i1777_2_);
		this.setLightsaber(item);
		this.timer = 45;
		hasHit = false;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 1.5f, 1.0F);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(13, Integer.valueOf(0));

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		this.setLightsaber(nbt.getInteger("lightsaber"));
		this.hasHit = nbt.getBoolean("hasHit");
		

	}
	
	public void onUpdate(){
		super.onUpdate();
		if(ticksExisted >= 50){
			if(this.getPlayerThrown() != null){
				if(this.getLightsaber() != null){
					this.getPlayerThrown().inventory.addItemStackToInventory(new ItemStack(this.getLightsaber()));
					this.setDead();
				}
			}
		}
		 if(ticksExisted == 20){
			this.hasHit = true;
		}
		 if(ticksExisted > 20){
			 this.calculateVelocityTowardsEntity(getPlayerThrown());
				this.hasHit = false;
			}
		if(this.hasHit){
			this.calculateVelocityTowardsEntity(getPlayerThrown());
			this.hasHit = false;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("lightsaber", this.getLightsaberInt());
		nbt.setBoolean("hasHit", this.hasHit);

	}
	@Override
	protected void onImpact(MovingObjectPosition mop) {
		
		if(this.getPlayerThrown() != null){
			if(mop.entityHit != null){
				if(mop.entityHit != this.getPlayerThrown()){
					mop.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(getPlayerThrown()), 10.0f);
				}
				else{
					this.getPlayerThrown().inventory.addItemStackToInventory(new ItemStack(this.getLightsaber()));
					this.setDead();
				}
			}
			if (mop.typeOfHit == MovingObjectType.BLOCK && this.worldObj.getBlockState(mop.getBlockPos()).getBlock() != Blocks.air){
				this.calculateVelocityTowardsEntity(getPlayerThrown());
				this.hasHit = false;
			}
		}
		
	}
	
	private EntityPlayer getPlayerThrown(){
		if(this.getThrower() instanceof EntityPlayer){
			return (EntityPlayer) this.getThrower();
		}
		else{
			return null;
		}
	}
	
	public Item getLightsaberFromInt(int i){
		Item[] lightsaber = {ItemManager.lightsaberblue, ItemManager.lightsabergreen, ItemManager.lightsaberred, ItemManager.lightsaberpurple, ItemManager.lightsaberorange, ItemManager.lightsaberyellow};
		if(i < lightsaber.length){
			return lightsaber[i];
		}
		else{
			return ItemManager.lightsaberblue;
		}
	}
	public Item getLightsaber(){
		return this.getLightsaberFromInt(this.getLightsaberInt());
	}
	
	public static int getIntForLightsaber(Item lightsaber){
		if(lightsaber == ItemManager.lightsaberblue){
			return 0;
		}
		else if(lightsaber == ItemManager.lightsabergreen){
			return 1;
		}
		else if(lightsaber == ItemManager.lightsaberred){
			return 2;
		}
		else if(lightsaber == ItemManager.lightsaberpurple){
			return 3;
		}
		else if(lightsaber == ItemManager.lightsaberorange){
			return 4;
		}
		else if(lightsaber == ItemManager.lightsaberyellow){
			return 5;
		}
		else return 0;
	}
	
	public void setLightsaber(int i){
		this.dataWatcher.updateObject(13, Integer.valueOf(i));
	}
	
	public int getLightsaberInt(){
		return this.dataWatcher.getWatchableObjectInt(13);
	}
	
	public float getGravityVelocity(){
		return 0.0f;
		
	}
	public void calculateVelocityTowardsEntity(EntityLivingBase entity){
		if(entity != null){
			double d = entity.posX - this.posX;
			double d1 = entity.posY - this.posY;
			double d2 = entity.posZ - this.posZ;
			
			this.setThrowableHeading(d, d1, d2, 1.5f, 1.0f);
		}
	}

}
