package com.apmods.swbf2.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.apmods.swbf2.item.ItemManager;

public class EntitySpeeder extends Entity implements IEntityAdditionalSpawnData, IInventory
{
	public double speed;
	public double accelaration;
	public double maxSpeed;


	public String owner;


	private ItemStack[] invItems = new ItemStack[9];

	public EntitySpeeder(World world)
	{
		super(world);
		this.preventEntitySpawning = true;
		this.setSize(0.6F, 1F);
		this.stepHeight = 1.1F;
		this.accelaration = 0.09D;
		this.maxSpeed = 0.8D;
	}
	
	/**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return this.getBoundingBox();
    }

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return false;
	}

	protected void entityInit()
	{
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
		this.dataWatcher.addObject(19, new Float(0.0F));
		this.dataWatcher.addObject(20, new Float(this.speed));
		this.dataWatcher.addObject(21, new Float(this.rotationYaw));
		this.dataWatcher.addObject(22, new Float(this.motionX));
		this.dataWatcher.addObject(23, new Float(this.motionY));
		this.dataWatcher.addObject(24, new Float(this.motionZ));
		this.dataWatcher.addObject(26, new Float(this.speed));
	}
	

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return false;
	}

	public EntitySpeeder(World world, int x, int y, int z, String owner)
	{
		this(world);
		this.setPosition(x, y + (double)this.getYOffset(), z);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
		this.owner = owner;
	}

	/**
	 * Returns the Y offset from the entity's position for any entity riding this one.
	 */
	public double getMountedYOffset()
	{
		return (double)this.height * 0.0D - 0.00100001192092896D;
	}
	
	public double getYOffset(){
		return this.height/2;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource damageSource, float damage)
	{
		if (this.isEntityInvulnerable(damageSource))
		{
			return false;
		}
		else if (!this.worldObj.isRemote && !this.isDead)
		{
			this.setForwardDirection(-this.getForwardDirection());
			this.setTimeSinceHit(10);
			this.setDamageTaken(this.getDamageTaken() + damage * 6.0F);
			this.setBeenAttacked();
			boolean isCreativeMode = damageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)damageSource.getEntity()).capabilities.isCreativeMode;

			if(!isCreativeMode)
			{
				if(damageSource.getEntity() instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer) damageSource.getEntity();
					if(!player.getDisplayNameString().equals(owner))
					{
						player.addChatMessage(new ChatComponentText("Your name isn't " + owner + "!"));
						return false;
					}
				}
			}

			if (isCreativeMode || this.getDamageTaken() > 40.0F)
			{
				if (this.riddenByEntity != null)
				{
					this.riddenByEntity.mountEntity(this);
				}

				if (!isCreativeMode)
				{
					this.dropItem(ItemManager.speeder, 1);

					

					for (int i = 0; i < this.getSizeInventory(); ++i)
					{
						ItemStack itemstack = this.getStackInSlot(i);

						if (itemstack != null)
						{
							float f = this.rand.nextFloat() * 0.8F + 0.1F;
							float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
							float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

							while (itemstack.stackSize > 0)
							{
								int j = this.rand.nextInt(21) + 10;

								if (j > itemstack.stackSize)
								{
									j = itemstack.stackSize;
								}

								itemstack.stackSize -= j;
								EntityItem entityitem = new EntityItem(this.worldObj, this.posX + (double)f, this.posY + (double)f1, this.posZ + (double)f2, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));
								float f3 = 0.05F;
								entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
								entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
								entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
								this.worldObj.spawnEntityInWorld(entityitem);
							}
						}
					}
				}

				this.setDead();
			}

			return true;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
	 */
	@SideOnly(Side.CLIENT)
	public void performHurtAnimation()
	{
		this.setForwardDirection(-this.getForwardDirection());
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int par9)
	{
		this.setPositionAndRotation(x, y + 0.026d, z, yaw, pitch);
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();

		
		
		if (this.getTimeSinceHit() > 0)
		{
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}

		if (this.getDamageTaken() > 0.0F)
		{
			this.setDamageTaken(this.getDamageTaken() - 1.0F);
		}

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if(this.worldObj.isRemote)
		{
			this.speed = this.dataWatcher.getWatchableObjectFloat(20);
			this.rotationYaw = this.dataWatcher.getWatchableObjectFloat(21);
			this.motionX = this.dataWatcher.getWatchableObjectFloat(22);
			this.motionY = this.dataWatcher.getWatchableObjectFloat(23);
			this.motionZ = this.dataWatcher.getWatchableObjectFloat(24);
			this.speed = this.dataWatcher.getWatchableObjectFloat(26);
		}

		double cos = Math.cos(this.rotationYaw * Math.PI / 180.0f);
		double sin = Math.sin(this.rotationYaw * Math.PI / 180.0f);

		this.motionX = -sin * speed;
		this.motionZ = cos * speed;
		this.motionY = -0.5431232;

		if(this.riddenByEntity != null)
		{
			if(this.riddenByEntity instanceof EntityLivingBase)
			{
				EntityLivingBase entity = (EntityLivingBase) this.riddenByEntity;

				if(this.worldObj.isRemote)
				{	
				}

				if(!this.isInWater())
				{
					if(this.speed > 0.1)
					{
						this.worldObj.spawnParticle(EnumParticleTypes.CLOUD, posX + (rand.nextDouble() - 0.5D) * (double)width, (posY + rand.nextDouble() * (double)height) - 0.25D, posZ + (rand.nextDouble() - 0.5D) * (double)width, 0, 0, 0);
					}
				}

								
						this.speed += entity.moveForward * this.accelaration;

						if(speed > maxSpeed)
						{
							speed = maxSpeed;
						}
						if(speed < -maxSpeed)
						{
							speed = -maxSpeed;
						}
				

				if(entity.moveForward <= 0)
				{
					this.speed *= 0.8500;
				}

				this.rotationYaw = MathHelper.wrapAngleTo180_float(entity.getRotationYawHead());
			}
		}
		else
		{
			this.speed *= 0.8500;
		}


		this.setRotation(this.rotationYaw, this.rotationPitch);
		this.moveEntity(motionX, motionY, motionZ);

		if(!this.worldObj.isRemote)
		{
			this.dataWatcher.updateObject(20, new Float(this.speed));
			this.dataWatcher.updateObject(21, new Float(this.rotationYaw));
			this.dataWatcher.updateObject(22, new Float(this.motionX));
			this.dataWatcher.updateObject(23, new Float(this.motionY));
			this.dataWatcher.updateObject(24, new Float(this.motionZ));
			this.dataWatcher.updateObject(26, new Float(this.speed));
		}
	}

	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			double dZ = Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * -0.1D;
			double dX = -Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * -0.1D;
			this.riddenByEntity.setPosition(this.posX + dX, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + dZ);
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setString("owner", owner);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.invItems.length; ++i)
		{
			if (this.invItems[i] != null)
			{
				NBTTagCompound slotTag = new NBTTagCompound();
				slotTag.setByte("Slot", (byte)i);
				this.invItems[i].writeToNBT(slotTag);
				nbttaglist.appendTag(slotTag);
			}
		}

		nbt.setTag("Items", nbttaglist);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound nbt) 
	{
		this.owner = nbt.getString("owner");

		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.invItems = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound slotTag = nbttaglist.getCompoundTagAt(i);
			int j = slotTag.getByte("Slot") & 255;

			if (j >= 0 && j < this.invItems.length)
			{
				this.invItems[j] = ItemStack.loadItemStackFromNBT(slotTag);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	/**
	 * First layer of player interaction
	 */
	public boolean interactFirst(EntityPlayer player)
	{
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != player)
		{
			return true;
		}
		else
		{
				if(player.isSneaking())
				{
					player.displayGUIChest(this);
				}
				else
				{
					if(!this.worldObj.isRemote)
					{
						player.mountEntity(this);
					}
				}

			return true;
		}
	}

	/**
	 * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
	 * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
	 */
	protected void updateFallState(double distanceFallenThisTick, boolean onGround)
	{
		this.fallDistance = 0.0F;
	}

	/**
	 * Sets the damage taken from the last hit.
	 */
	public void setDamageTaken(float damage)
	{
		this.dataWatcher.updateObject(19, Float.valueOf(damage));
	}

	/**
	 * Gets the damage taken from the last hit.
	 */
	public float getDamageTaken()
	{
		return this.dataWatcher.getWatchableObjectFloat(19);
	}

	/**
	 * Sets the time to count down from since the last time entity was hit.
	 */
	public void setTimeSinceHit(int time)
	{
		this.dataWatcher.updateObject(17, Integer.valueOf(time));
	}

	/**
	 * Gets the time since the last hit.
	 */
	public int getTimeSinceHit()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	/**
	 * Sets the forward direction of the entity.
	 */
	public void setForwardDirection(int direction)
	{
		this.dataWatcher.updateObject(18, Integer.valueOf(direction));
	}

	/**
	 * Gets the forward direction of the entity.
	 */
	public int getForwardDirection()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	/**
	 * Returns the stack in slot i
	 */
	public ItemStack getStackInSlot(int slot)
	{
		return this.invItems[slot];
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	 * new stack.
	 */
	public ItemStack decrStackSize(int slot, int amount)
	{
		if (this.invItems[slot] != null)
		{
			ItemStack itemstack;

			if (this.invItems[slot].stackSize <= amount)
			{
				itemstack = this.invItems[slot];
				this.invItems[slot] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.invItems[slot].splitStack(amount);

				if (this.invItems[slot].stackSize == 0)
				{
					this.invItems[slot] = null;
				}

				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	 * like when you close a workbench GUI.
	 */
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		if (this.invItems[slot] != null)
		{
			ItemStack itemstack = this.invItems[slot];
			this.invItems[slot] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		this.invItems[slot] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	/**
	 * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
	 * hasn't changed and skip it.
	 */
	public void markDirty() {}

	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.isDead ? false : player.getDistanceSqToEntity(this) <= 64.0D;
	}

	public void openInventory() {}

	public void closeInventory() {}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
	 */
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{	
		return true;
	}

	/**
	 * Returns the name of the inventory
	 */
	public String getName()
	{
		return "Speeder";
	}

	/**
	 * Returns the maximum stack size for a inventory slot.
	 */
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public int getSizeInventory() 
	{
		return invItems.length;
	}


	@Override
	public void writeSpawnData(ByteBuf buffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}


}