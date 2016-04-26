package com.apmods.swbf2.extendedplayer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.apmods.swbf2.network.BattleNet;
import com.apmods.swbf2.network.SyncPropsPacket;

public class BattlefrontProps implements IExtendedEntityProperties
{
	/*
	Here I create a constant EXT_PROP_NAME for this class of properties. You need a unique name for every instance of IExtendedEntityProperties you make, and doing it at the top of each class as a constant makes
	it very easy to organize and avoid typos. It's easiest to keep the same constant name in every class, as it will be distinguished by the class name: ExtendedPlayer.EXT_PROP_NAME vs. ExtendedEntity.EXT_PROP_NAME
	
	Note that a single entity can have multiple extended properties, so each property should have a unique name. Try to come up with something more unique than the tutorial example.
	*/
	public final static String BATTLEFRONT = "Battlefront Props";
	
	// I always include the entity to which the properties belong for easy access
	// It's final because we won't be changing which player it is
	private final EntityPlayer player;
	
	
	private int jetpackFuel;
	
	/*
	The default constructor takes no arguments, but I put in the Entity so I can initialize the above variable 'player'
	
	Also, it's best to initialize any other variables you may have added, just like in any constructor.
	*/
	public BattlefrontProps(EntityPlayer player)
	{
		this.player = player;
		this.jetpackFuel = 100;
		
	}
	
	/**
	* Used to register these extended properties for the player during EntityConstructing event
	* This method is for convenience only; it will make your code look nicer
	*/
	/**
	 * Returns ExtendedPlayer properties for player
	 */
	public static final BattlefrontProps get(EntityPlayer player) {
		return (BattlefrontProps) player.getExtendedProperties(BATTLEFRONT);
	}
	public void onJoinWorld() {
		if (player instanceof EntityPlayerMP) {
		}
	}

	
	// Save any custom data that needs saving here
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("jetpack", this.jetpackFuel);
		compound.setTag(BATTLEFRONT, properties);
	}
	
	// Load whatever data you saved
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(BATTLEFRONT);
		this.jetpackFuel = properties.getInteger("jetpack");
	}
	
	@Override
	public void init(Entity entity, World world)
	{
	}
	/**
	 * Used to register these extended properties for the player during EntityConstructing event
	 */
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(BattlefrontProps.BATTLEFRONT, new BattlefrontProps(player));
	}
	public int getJetpackFuel(){
		return this.jetpackFuel;
	}
	public void useJetpackFuel(){
		this.jetpackFuel--;
		BattleNet.net.sendTo(new SyncPropsPacket.PropsMessage(player), (EntityPlayerMP) player);
	}
	public void gainJetpackFuel(){
		if(this.jetpackFuel < 100){
			this.jetpackFuel++;
		}
		BattleNet.net.sendTo(new SyncPropsPacket.PropsMessage(player), (EntityPlayerMP) player);
	}
	
	

}