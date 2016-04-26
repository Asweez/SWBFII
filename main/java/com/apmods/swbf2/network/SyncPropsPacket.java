package com.apmods.swbf2.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.apmods.swbf2.extendedplayer.BattlefrontProps;
import com.apmods.swbf2.main.Battlefront;
import com.apmods.swbf2.network.SyncPropsPacket.PropsMessage;

public class SyncPropsPacket implements IMessageHandler<PropsMessage, IMessage>{

	@Override
	public IMessage onMessage(PropsMessage message, MessageContext ctx) {
		// TODO Auto-generated method stub
		return Battlefront.proxy.onMessage(message, ctx);
	}
	
	public static class PropsMessage implements IMessage{

		public NBTTagCompound data;

        public PropsMessage()
        {
        }
        public PropsMessage(EntityPlayer player)
        {
        	BattlefrontProps ext = BattlefrontProps.get(player);
        	this.data = new NBTTagCompound();
        	ext.saveNBTData(data);
        }

		@Override
		public void fromBytes(ByteBuf buf) {
			this.data = ByteBufUtils.readTag(buf);
		}

		@Override
		public void toBytes(ByteBuf buf) {
			ByteBufUtils.writeTag(buf, data);
		}
		
	}
	
}
