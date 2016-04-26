package com.apmods.swbf2.proxy;

import com.apmods.swbf2.client.RenderBullet;
import com.apmods.swbf2.client.RenderGrenade;
import com.apmods.swbf2.client.RenderIFT;
import com.apmods.swbf2.client.RenderLightsaberThrown;
import com.apmods.swbf2.client.RenderRocket;
import com.apmods.swbf2.client.RenderSniper;
import com.apmods.swbf2.client.RenderSnowspeeder;
import com.apmods.swbf2.client.RenderSpeeder;
import com.apmods.swbf2.client.key.BattlefrontKeys;
import com.apmods.swbf2.client.key.KeyEventBattlefront;
import com.apmods.swbf2.entity.EntityBullet;
import com.apmods.swbf2.entity.EntityDetpack;
import com.apmods.swbf2.entity.EntityGrenade;
import com.apmods.swbf2.entity.EntityIFT;
import com.apmods.swbf2.entity.EntityLightsaberThrown;
import com.apmods.swbf2.entity.EntityRocket;
import com.apmods.swbf2.entity.EntitySniperBullet;
import com.apmods.swbf2.entity.EntitySnowspeeder;
import com.apmods.swbf2.entity.EntitySpeeder;
import com.apmods.swbf2.extendedplayer.BattlefrontProps;
import com.apmods.swbf2.item.ItemManager;
import com.apmods.swbf2.main.Battlefront;
import com.apmods.swbf2.network.SyncPropsPacket.PropsMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy{
	public void init(){
		this.registerRenderers();
		MinecraftForge.EVENT_BUS.register(new KeyEventBattlefront());
		FMLCommonHandler.instance().bus().register(new KeyEventBattlefront());
		ClientRegistry.registerKeyBinding(BattlefrontKeys.shoot);
		ClientRegistry.registerKeyBinding(BattlefrontKeys.shoot2);
		ClientRegistry.registerKeyBinding(BattlefrontKeys.fly);
		
		for(int i = 0; i < ItemManager.allItems.size(); i++){
			Item item = ItemManager.allItems.get(i);
			regTex(item, item.getUnlocalizedName().substring(5));
		}
		for(int i = 0; i < ItemManager.armors.length; i++){
			Item item = ItemManager.armors[i];
			regTex(item, item.getUnlocalizedName().substring(5));
		}

		
	}
	protected void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderRocket());
		RenderingRegistry.registerEntityRenderingHandler(EntitySniperBullet.class, new RenderSniper());
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade(ItemManager.grenade, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDetpack.class, new RenderGrenade(ItemManager.detpack,  Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntityLightsaberThrown.class, new RenderLightsaberThrown());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpeeder.class, new RenderSpeeder());
		RenderingRegistry.registerEntityRenderingHandler(EntityIFT.class, new RenderIFT());
		RenderingRegistry.registerEntityRenderingHandler(EntitySnowspeeder.class, new RenderSnowspeeder());
	}
	
	public IMessage onMessage(PropsMessage message, MessageContext ctx)
    {
        if(ctx.side.isClient())
        {
        	EntityPlayer player = Minecraft.getMinecraft().thePlayer;

            if (player != null)
            {
                int playerX = (int) player.posX;
                int playerY = (int) player.posY;
                int playerZ = (int) player.posZ;
                World world = player.worldObj;
                BattlefrontProps ext = BattlefrontProps.get(player);
                ext.loadNBTData(message.data);
            }
            
        }
        return null;
    }
	
	public void regTex(Item item, String name){
    	ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(Battlefront.MODID + ":" + name, "inventory");
        final int DEFAULT_ITEM_SUBTYPE = 0;
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }
}
