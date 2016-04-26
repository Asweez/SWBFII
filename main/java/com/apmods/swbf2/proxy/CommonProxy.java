package com.apmods.swbf2.proxy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.apmods.swbf2.extendedplayer.PropHandler;
import com.apmods.swbf2.network.SyncPropsPacket.PropsMessage;

public class CommonProxy {
	
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();
	
	public void init(){
		this.registerRenderers();
		MinecraftForge.EVENT_BUS.register(new PropHandler());
	}

	protected void registerRenderers() {
		// TODO Auto-generated method stub
		
	}
	/**
	* Adds an entity's custom data to the map for temporary storage
	* @param compound An NBT Tag Compound that stores the IExtendedEntityProperties data only
	*/
	public static void storeEntityData(String name, NBTTagCompound compound)
	{
		extendedEntityData.put(name, compound);
	}

	/**
	* Removes the compound from the map and returns the NBT tag stored for name or null if none exists
	*/
	public static NBTTagCompound getEntityData(String name)
	{
		return extendedEntityData.remove(name);
	}
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}
	public IMessage onMessage(PropsMessage message, MessageContext ctx)
    {
		return null;
    }
}
