package com.apmods.swbf2.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.apmods.swbf2.network.JetpackPacket.JetpackMessage;

public class JetpackPacket implements IMessageHandler<JetpackMessage, IMessage>{
	@Override
    public IMessage onMessage(JetpackMessage message, MessageContext ctx)
    {
        if(ctx.side.isServer())
        {
        	EntityPlayerMP player = ctx.getServerHandler().playerEntity;

            if (player != null)
            {
                int playerX = (int) player.posX;
                int playerY = (int) player.posY;
                int playerZ = (int) player.posZ;
                World world = player.worldObj;
                Entity e = player.ridingEntity;
                player.addVelocity(0, 0.15, 0);
            }
            
        }
        return null;
    }

    public static class JetpackMessage implements IMessage
    {

		@Override
		public void fromBytes(ByteBuf buf) {
		}

		@Override
		public void toBytes(ByteBuf buf) {
		}

        
    }
}
