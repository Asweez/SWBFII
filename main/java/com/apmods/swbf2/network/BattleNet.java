package com.apmods.swbf2.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import com.apmods.swbf2.main.Battlefront;

public class BattleNet {
	public static SimpleNetworkWrapper net;
    public static int messages = 0;

    public static void init(){
        net = NetworkRegistry.INSTANCE.newSimpleChannel(Battlefront.MODID);
        registerMessage(ShootPacket.class, ShootPacket.ShootMessage.class);
        registerMessage(JetpackPacket.class, JetpackPacket.JetpackMessage.class);
        registerMessageForClient(SyncPropsPacket.class, SyncPropsPacket.PropsMessage.class);

    }
    
    private static void registerMessage(Class handler, Class message)
    {
        net.registerMessage(handler, message, messages, Side.CLIENT);
        net.registerMessage(handler, message, messages, Side.SERVER);
        messages++;
    }
    private static void registerMessageForServer(Class handler, Class message)
    {
        net.registerMessage(handler, message, messages, Side.SERVER);
        messages++;
    }
    private static void registerMessageForClient(Class handler, Class message)
    {
        net.registerMessage(handler, message, messages, Side.CLIENT);
        messages++;
    }
}
