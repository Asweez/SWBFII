package com.apmods.swbf2.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySniperBullet extends EntityBullet implements IProjectile{

	public EntitySniperBullet(World par1World, EntityLivingBase entity,
			float speed, float damage) {
		super(par1World, entity, speed);
		this.setThrowableHeading(motionX, motionY, motionZ, speed, damage);
	}
	public EntitySniperBullet(World par1World) {
		super(par1World);
	}
	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if(mop.entityHit != null && mop.entityHit instanceof EntityLivingBase){
			EntityLivingBase entity = (EntityLivingBase) mop.entityHit;
			if(entity.getEyeHeight()  + entity.posY <= this.posY){
				System.out.println("headshot");
				entity.attackEntityFrom(DamageSource.causeMobDamage(this.getThrower()), 20);
			}
			System.out.println(entity.getEyeHeight() + entity.posY);
			System.out.println(this.posY);
			entity.attackEntityFrom(DamageSource.causeMobDamage(this.getThrower()), 10);
		}
	}
	public float getGravityVelocity(){
		return 0.0f;
		
	}
	@SideOnly(Side.CLIENT)
	   public int getBrightnessForRender(float par1)
	   {
	       return 15728880;
	   }

	   /**
	    * Gets how bright this entity is.
	    */
	   public float getBrightness(float par1)
	   {
	       return 1.0F;
	   }

}
