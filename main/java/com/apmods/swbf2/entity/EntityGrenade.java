package com.apmods.swbf2.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityGrenade extends EntityBattlefrontProjectile implements IProjectile
{
    private int field_145791_d = -1;
    private int field_145792_e = -1;
    private int field_145789_f = -1;
    private Block field_145790_g;
	private EntityLivingBase shootingEntity;
    
    private static final String __OBFID = "CL_00001715";

    public EntityGrenade(World p_i1753_1_)
    {
        super(p_i1753_1_);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
    }

    public EntityGrenade(World p_i1754_1_, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_)
    {
        super(p_i1754_1_);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
        this.setPosition(p_i1754_2_, p_i1754_4_, p_i1754_6_);
    }

    public EntityGrenade(World p_i1755_1_, EntityLivingBase p_i1755_2_, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_)
    {
        super(p_i1755_1_);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = p_i1755_2_;

        this.posY = p_i1755_2_.posY + (double)p_i1755_2_.getEyeHeight() - 0.10000000149011612D;
        double d0 = p_i1755_3_.posX - p_i1755_2_.posX;
        double d1 = p_i1755_3_.getBoundingBox().minY + (double)(p_i1755_3_.height / 3.0F) - this.posY;
        double d2 = p_i1755_3_.posZ - p_i1755_2_.posZ;
        double d3 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7D)
        {
            float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(p_i1755_2_.posX + d4, this.posY, p_i1755_2_.posZ + d5, f2, f3);
            float f4 = (float)d3 * 0.2F;
            this.setThrowableHeading(d0, d1 + (double)f4, d2, p_i1755_4_, p_i1755_5_);
        }
    }

    public EntityGrenade(World p_i1756_1_, EntityLivingBase p_i1756_2_, float p_i1756_3_)
    {
        super(p_i1756_1_);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = p_i1756_2_;


        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(p_i1756_2_.posX, p_i1756_2_.posY + (double)p_i1756_2_.getEyeHeight(), p_i1756_2_.posZ, p_i1756_2_.rotationYaw, p_i1756_2_.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, p_i1756_3_ * 1.5F, 1.0F);
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
     */
    public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_)
    {
        float f2 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
        p_70186_1_ /= (double)f2;
        p_70186_3_ /= (double)f2;
        p_70186_5_ /= (double)f2;
        p_70186_1_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_3_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_5_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_1_ *= (double)p_70186_7_;
        p_70186_3_ *= (double)p_70186_7_;
        p_70186_5_ *= (double)p_70186_7_;
        this.motionX = p_70186_1_;
        this.motionY = p_70186_3_;
        this.motionZ = p_70186_5_;
        float f3 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70186_3_, (double)f3) * 180.0D / Math.PI);
    }

    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_)
    {
        this.setPosition(p_70056_1_, p_70056_3_, p_70056_5_);
        this.setRotation(p_70056_7_, p_70056_8_);
    }

    /**
     * Sets the velocity to the args. Args: x, y, z
     */
    @SideOnly(Side.CLIENT)
    public void setVelocity(double p_70016_1_, double p_70016_3_, double p_70016_5_)
    {
        this.motionX = p_70016_1_;
        this.motionY = p_70016_3_;
        this.motionZ = p_70016_5_;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(p_70016_1_ * p_70016_1_ + p_70016_5_ * p_70016_5_);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70016_1_, p_70016_5_) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70016_3_, (double)f) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        if(this.ticksExisted == 80){
        	if(!this.worldObj.isRemote){
        		this.setDead();
        		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 3f, true);
        	}
        }
        
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        p_70014_1_.setShort("xTile", (short)this.field_145791_d);
        p_70014_1_.setShort("yTile", (short)this.field_145792_e);
        p_70014_1_.setShort("zTile", (short)this.field_145789_f);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        this.field_145791_d = p_70037_1_.getShort("xTile");
        this.field_145792_e = p_70037_1_.getShort("yTile");
        this.field_145789_f = p_70037_1_.getShort("zTile");
        
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    /**
     * If returns false, the item will not inflict any damage against entities.
     */
    public boolean canAttackWithItem()
    {
        return false;
    }

    /**
     * Whether the arrow has a stream of critical hit particles flying behind it.
     */
    public void setIsCritical(boolean p_70243_1_)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70243_1_)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
        }
    }

    /**
     * Whether the arrow has a stream of critical hit particles flying behind it.
     */
    public boolean getIsCritical()
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);
        return (b0 & 1) != 0;
    }
}