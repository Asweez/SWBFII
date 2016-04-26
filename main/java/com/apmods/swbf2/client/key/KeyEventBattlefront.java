package com.apmods.swbf2.client.key;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

import com.apmods.swbf2.network.BattleNet;
import com.apmods.swbf2.network.ShootPacket;

public class KeyEventBattlefront {
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent evt){

			if(BattlefrontKeys.shoot.isKeyDown()){
				BattleNet.net.sendToServer(new ShootPacket.ShootMessage(false));
			}
			if(BattlefrontKeys.shoot2.isKeyDown()){
				BattleNet.net.sendToServer(new ShootPacket.ShootMessage(true));
			}
		
	}
}
