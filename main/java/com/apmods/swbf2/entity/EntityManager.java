package com.apmods.swbf2.entity;

import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import com.apmods.swbf2.main.Battlefront;

public class EntityManager {
	public static void init(){
		registerEntity(EntityBullet.class, "RifleBolt");
		registerEntity(EntitySniperBullet.class, "Sniper");
		registerEntity(EntityShotgunBullet.class, "Shotgun");
		registerEntity(EntityRocket.class, "Rocket");
		registerEntity(EntityBowcasterBullet.class, "Bowcaster Bullet");
		registerEntity(EntityGrenade.class, "Grenade");
		registerEntity(EntityLightsaberThrown.class, "Lightsaber");
		registerEntity(EntityDetpack.class, "Detpack");
		registerEntity(EntitySpeeder.class, "Speeder");
		registerEntity(EntityIFT.class, "IFT-T");
		registerEntity(EntityIFT.class, "IFT-X");
		registerEntity(EntitySnowspeeder.class, "Snowspeeder");

	}
	public static void registerEntity(Class entityClass, String name)
	{
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
	
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, entityID, Battlefront.instance, 64, 1, true);
	}
	public static void registerEntity(Class entityClass, String name, int primary, int secondary)
	{
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
	
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, entityID, Battlefront.instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID), new EntityList.EntityEggInfo(entityID, primary, secondary));
	}
}
