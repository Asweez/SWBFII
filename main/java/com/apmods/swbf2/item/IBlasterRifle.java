package com.apmods.swbf2.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.world.World;

public interface IBlasterRifle {
	/**The max amount of bullets this gun can hold*/
	public int getMaxAmmo();
	/**The max amount of bullets a chamber can hold*/
	public int getMaxChamberAmmo();
	/**The number of ticks in between shots*/
	public int getRoF();
	/**The number of ticks to reload the chamber*/
	public int getReloadTime();
	
	public String getSound();
	public EntityThrowable getBullet(EntityPlayer player, World world);
}
