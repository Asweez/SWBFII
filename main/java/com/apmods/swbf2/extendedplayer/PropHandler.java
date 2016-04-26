package com.apmods.swbf2.extendedplayer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import com.apmods.swbf2.client.key.BattlefrontKeys;
import com.apmods.swbf2.item.ItemManager;
import com.apmods.swbf2.main.Battlefront;
import com.apmods.swbf2.network.BattleNet;
import com.apmods.swbf2.network.SyncPropsPacket;

public class PropHandler {
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && BattlefrontProps.get((EntityPlayer) event.entity) == null){
			BattlefrontProps.register((EntityPlayer) event.entity);
		}
	}
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote) {
			BattleNet.net.sendTo(new SyncPropsPacket.PropsMessage((EntityPlayer) event.entity), (EntityPlayerMP) event.entity);
			Battlefront.proxy.getEntityData(BattlefrontProps.BATTLEFRONT);
		}
	}
	@SubscribeEvent
	public void onTick(TickEvent.PlayerTickEvent evt){
		if(evt.side.isServer()){
			BattlefrontProps ext = BattlefrontProps.get(evt.player);
			if(ext.getJetpackFuel() < 100 && !BattlefrontKeys.fly.isKeyDown()){
				ext.gainJetpackFuel();
			}
			BattlefrontProps props = BattlefrontProps.get(evt.player);
	    	if(evt.player.ridingEntity == null){
		    	if(BattlefrontKeys.fly.isKeyDown()){
		    		Item[] armor = new Item[] {ItemManager.getArmor("clonejet", 3), ItemManager.getArmor("clonejet", 2), ItemManager.getArmor("clonejet", 1), ItemManager.getArmor("clonejet", 0)}; //Create a new itemstack with each piece of your armor
		    		Item[] armor2 = new Item[] {ItemManager.getArmor("trooperdark", 3), ItemManager.getArmor("trooperdark", 2), ItemManager.getArmor("trooperdark", 1), ItemManager.getArmor("trooperdark", 0)}; //Create a new itemstack with each piece of your armor

		    		int gearCounter = 0;
		    		int gearCounter2 = 0;
		    		for(int i = 0; i < armor.length; i++) {
		    		     if(evt.player.getEquipmentInSlot(i + 1) != null &&  evt.player.getEquipmentInSlot(i + 1).getItem() == armor[i]) {
		    		        gearCounter++;
		    		     }
		    		}
		    		for(int i = 0; i < armor2.length; i++) {
		    		     if(evt.player.getEquipmentInSlot(i + 1) != null &&  evt.player.getEquipmentInSlot(i + 1).getItem() == armor2[i]) {
		    		        gearCounter2++;
		    		     }
		    		}
		    		if(gearCounter == 4 || gearCounter2 == 4) {
		    			if(props.getJetpackFuel() > 0){
		    				evt.player.addVelocity(0, 0.005, 0);
		    				props.useJetpackFuel();
		    			}
			    		evt.player.fallDistance = 0;
		    		    gearCounter = 0;
		    		    gearCounter2 = 0;
		    		}
		    		
		    	}
	    	}
			
		}
	}
}
