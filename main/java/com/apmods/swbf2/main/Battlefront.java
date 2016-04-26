package com.apmods.swbf2.main;

import com.apmods.swbf2.block.BlockManager;
import com.apmods.swbf2.entity.EntityManager;
import com.apmods.swbf2.extendedplayer.PropHandler;
import com.apmods.swbf2.item.HAADHandler;
import com.apmods.swbf2.item.ItemManager;
import com.apmods.swbf2.item.RecipeManager;
import com.apmods.swbf2.network.BattleNet;
import com.apmods.swbf2.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = "Star Wars Battlefront 2", modid = Battlefront.MODID, version = Battlefront.VERSION)
public class Battlefront
{
    public static final String MODID = "battlefront";
    public static final String VERSION = "1.0";
    
    @SidedProxy(clientSide="com.apmods.swbf2.proxy.ClientProxy", serverSide = "com.apmods.swbf2.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @Instance
    public static Battlefront instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	ItemManager.init();
    	BlockManager.init();
    	EntityManager.init();
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init();
    	BattleNet.init();
    	RecipeManager.init();
		MinecraftForge.EVENT_BUS.register(new PropHandler());
		FMLCommonHandler.instance().bus().register(new PropHandler());
		MinecraftForge.EVENT_BUS.register(new HAADHandler());
		FMLCommonHandler.instance().bus().register(new HAADHandler());
//		JSONWriter.loadJSON();

    }
}
