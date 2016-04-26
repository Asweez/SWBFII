package com.apmods.swbf2.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityShotgunBullet extends EntityBullet{
	public EntityShotgunBullet (World world){
		super(world);
	}
	public EntityShotgunBullet(World par1World, EntityLivingBase entity, float speed,
			float spread, int n) {
		super(par1World, entity, speed);
		if (n > 1) {
			setLocationAndAngles(entity.posX, entity.posY + (double) entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);
			float rotFactor = (float)(n / 2) * spread;
			rotationYaw += rotFactor * (n % 2 == 0 ? 1 : -1);
			posX -= (double)(MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
			posY -= 0.10000000149011612D;
			posZ -= (double)(MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
			setPosition(posX, posY, posZ);
			float f = 0.4F;
			motionX = (double)(-MathHelper.sin(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f);
			motionZ = (double)(MathHelper.cos(rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f);
			motionY = (double)(-MathHelper.sin((rotationPitch + this.getInaccuracy()) / 180.0F * (float)Math.PI) * f);
		}
		setThrowableHeading(motionX, motionY, motionZ, speed, 1.0F);
	}
	
	public void onUpdate(){
		super.onUpdate();
		if(this.ticksExisted > 100){
			this.setDead();
		}
	}
	
	protected void onImpact(MovingObjectPosition mop){
		super.onImpact(mop);
	}
	
	

}
