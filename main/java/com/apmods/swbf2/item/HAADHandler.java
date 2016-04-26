package com.apmods.swbf2.item;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;

public class HAADHandler {
	@SubscribeEvent
	public void pickup(ItemPickupEvent evt){
		if(evt.pickedUp.getEntityItem().getItem() instanceof ItemHAAD){
			ItemHAAD haad = (ItemHAAD) evt.pickedUp.getEntityItem().getItem();
			if(haad.color == "red"){
				evt.player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 100, 1));
			}
			if(haad.color == "green"){
				evt.player.addPotionEffect(new PotionEffect(Potion.resistance.id, 100, 1));
			}
			if(haad.color == "ammo"){
				if(evt.player.getHeldItem() != null && evt.player.getHeldItem().getItem() instanceof ItemBlaster){
					ItemStack b = evt.player.getHeldItem(); 
					ItemBlaster blaster = (ItemBlaster) b.getItem();
					b.getTagCompound().setInteger("chamberammo", blaster.getMaxChamberAmmo());
				}
			}
			if(haad.color == "blue"){
				evt.player.heal(20);
			}
			if(haad.color == "yellow"){
				evt.player.addPotionEffect(new PotionEffect(Potion.absorption.id, 1, 23));
			}
			evt.pickedUp.setDead();
		}
	}
}
