package com.apmods.swbf2.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.apmods.swbf2.entity.EntityLightsaberThrown;
import com.apmods.swbf2.main.Battlefront;
import com.google.common.collect.Multimap;

public class ItemLightsaber extends ItemBase{

	
	
	public ItemLightsaber(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	public boolean onLeftClickEntity(ItemStack is, EntityPlayer player, Entity target)
    {
		super.onLeftClickEntity(is, player, target);
		if(is.getTagCompound().getInteger("sprint") > 0){
			if(target instanceof EntityLivingBase){
				EntityLivingBase target1 = (EntityLivingBase) target;
				if(target1.getCreatureAttribute() != EnumCreatureAttribute.UNDEAD){
					target1.addPotionEffect(new PotionEffect(Potion.harm.id, 1, 7, true, false));
				}
				else{
					target1.addPotionEffect(new PotionEffect(Potion.heal.id, 1, 7, true, false));
				}
			}
			
		}
		player.worldObj.playSoundAtEntity(player, Battlefront.MODID + ":lightsaberHit", 0.7f, 1.0f);
        return false;
    }
	
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World world, EntityPlayer player)
    {
		player.worldObj.playSoundAtEntity(player, Battlefront.MODID + ":lightsaberthrow", 0.7f, 1.0f);
		if(!world.isRemote){
			world.spawnEntityInWorld(new EntityLightsaberThrown(world, player, EntityLightsaberThrown.getIntForLightsaber(this)));
		}
		p_77659_1_.stackSize--;
        return p_77659_1_;
    }
	
    public void onUpdate(ItemStack is, World p_77663_2_, Entity entity, int p_77663_4_, boolean p_77663_5_) {
    	if(is.getTagCompound() == null){
    		is.setTagCompound(new NBTTagCompound());
    		is.getTagCompound().setInteger("sprint", 0);
    	}
    	if(entity instanceof EntityPlayer){
    		EntityPlayer player = (EntityPlayer) entity;
    		if(player.isSprinting() && !(is.getTagCompound().getInteger("sprint") >= 10)){
    			is.getTagCompound().setInteger("sprint", 10);
    		}
    		else{
    			if(is.getTagCompound().getInteger("sprint") > 0){
    				int i = is.getTagCompound().getInteger("sprint");
        			is.getTagCompound().setInteger("sprint", i - 1);
    			}
    		}
    	}
    }

	
	public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Weapon modifier", 10, 0));
        return multimap;
    }
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
		entityLiving.worldObj.playSoundAtEntity(entityLiving, Battlefront.MODID + ":lightsaberswing", 0.7f, 1.0f);
        return false;
    }

}
