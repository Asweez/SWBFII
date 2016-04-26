package com.apmods.swbf2.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.world.World;

import com.apmods.swbf2.entity.EntityBullet;
import com.apmods.swbf2.entity.EntityRocket;

public class ItemRocketLauncher extends ItemBlaster implements IBlasterRifle{

	public ItemRocketLauncher(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getMaxAmmo() {
		return 8;
	}

	@Override
	public int getMaxChamberAmmo() {
		return 4;
	}

	@Override
	public int getRoF() {
		return 100;
	}

	@Override
	public int getReloadTime() {
		return 300;
	}
	@Override
	public EntityThrowable getBullet(EntityPlayer player, World world) {
		return new EntityRocket(world, player, 1f);
	}
	public String getSound(){
		return "rocketLauncher";
	}

}
