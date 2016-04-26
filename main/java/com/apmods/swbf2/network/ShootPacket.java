package com.apmods.swbf2.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.apmods.swbf2.entity.EntityBullet;
import com.apmods.swbf2.entity.EntityIFT;
import com.apmods.swbf2.entity.EntityRocket;
import com.apmods.swbf2.entity.EntitySpeeder;
import com.apmods.swbf2.main.Battlefront;
import com.apmods.swbf2.network.ShootPacket.ShootMessage;

public class ShootPacket implements IMessageHandler<ShootMessage, IMessage>{
	@Override
    public IMessage onMessage(ShootMessage message, MessageContext ctx)
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
                if(!message.isSecondary){
	                if(e  instanceof EntitySpeeder || e instanceof EntityIFT){
	                	EntityBullet b = new EntityBullet(world, player, 1.5f);
	                	if(!world.isRemote){
	                		world.spawnEntityInWorld(b);
	                		world.playSoundAtEntity(b, Battlefront.MODID + ":blaster", 1.0f, 1.0f);
	                	}
	                }
                }
                else{
                	if(e instanceof EntityIFT){
                		EntityRocket b = new EntityRocket(world, player, 1.1f, 2f);
	                	if(!world.isRemote){
	                		world.spawnEntityInWorld(b);
	                		world.playSoundAtEntity(b, Battlefront.MODID + ":rocketLauncher", 0.7f, 1.0f);
	                	}
                	}
                }
            }
            
        }
        return null;
    }

    public static class ShootMessage implements IMessage
    {
    	private boolean isSecondary;
    	private int y;
    	private int z;

        public ShootMessage()
        {
        }
        public ShootMessage(boolean sec)
        {
        	this.isSecondary = sec;
        }

		@Override
		public void fromBytes(ByteBuf buf) {
			this.isSecondary = buf.readBoolean();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeBoolean(isSecondary);
		}

        
    }
}
