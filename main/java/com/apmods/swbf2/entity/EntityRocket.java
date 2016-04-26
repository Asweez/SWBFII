package com.apmods.swbf2.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRocket extends EntityBullet implements IProjectile{
	
	public EntityLivingBase shootingEntity;
	
	private float damage = 0;
	
	public EntityRocket(World par1World) {
	       super(par1World);
	       this.setThrowableHeading(motionX, motionY, motionZ, 4.5f, 1F);
	   }
	   public EntityRocket(World par1World, EntityLivingBase entity, float speed) {
	       super(par1World, entity, speed);
	       this.shootingEntity = entity;
	       this.setThrowableHeading(motionX, motionY, motionZ, speed, damage);
	   }
	   public EntityRocket(World par1World, EntityPlayer player, float speed, float offset) {
	       super(par1World, player, speed, offset);
	       this.shootingEntity = player;
	       Vec3 v = player.getLookVec().normalize();
	       damage = 0.5f;
	       this.setPosition( player.posX + v.xCoord * offset,  player.posY + player.eyeHeight + v.yCoord * offset,  player.posZ + v.zCoord * offset);
	       this.setThrowableHeading(motionX, motionY, motionZ, speed, 1.0f);
	   }
	   public EntityRocket(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, int par5)
	   {
	       super(par1World);
	       this.renderDistanceWeight = 10.0D;
	       this.shootingEntity = par2EntityLivingBase;

	       this.posY = par2EntityLivingBase.posY + (double)par2EntityLivingBase.getEyeHeight() - 0.10000000149011612D;
	       double d0 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
	       double d1 = par3EntityLivingBase.getBoundingBox().minY + (double)(par3EntityLivingBase.height / 3.0F) - this.posY;
	       double d2 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
	       double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

	       if (d3 >= 1.0E-7D)
	       {
	           float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
	           float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
	           double d4 = d0 / d3;
	           double d5 = d2 / d3;
	           this.setLocationAndAngles(par2EntityLivingBase.posX + d4, this.posY, par2EntityLivingBase.posZ + d5, f2, f3);
	           float f4 = (float)d3 * 0.2F;
	           this.setThrowableHeading(d0, d1 + (double)f4, d2, par4, 1f);
	       }
	   }
	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if(mop.entityHit != null && mop.entityHit instanceof EntityLivingBase){
			float knockback = 0.3f;
			float f4 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			EntityLivingBase entity = (EntityLivingBase) mop.entityHit;
			if(this.shootingEntity instanceof EntityLivingBase){
				entity.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase) shootingEntity), 2);
			}
//			entity.addVelocity(this.motionX/10/f4, 0.05, this.motionZ/10/f4);
//			entity.knockBack(p_70653_1_, p_70653_2_, p_70653_3_, p_70653_5_);
		}
		if(this.shootingEntity != null){
			if(this.shootingEntity.ridingEntity != null && this.shootingEntity.ridingEntity instanceof EntityIFT){
				if(mop.entityHit != this.shootingEntity.ridingEntity){
					this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2.0f, true);
				}
			}
		}
		else{
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2.0f, true);
		}
		this.setDead();
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
	   
	   public void onUpdate(){
		   super.onUpdate();
		   if(this.ticksExisted > 300){
			   this.setDead();
		   }
	   }
}
