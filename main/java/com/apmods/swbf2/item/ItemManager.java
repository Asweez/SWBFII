package com.apmods.swbf2.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemManager {
	
	public static Item blaster, lightsaberblue, lightsaberred, lightsaberpurple, lightsabergreen, lightsaberorange, lightsaberyellow, grenade,
	minigun, detpack, detonator, sniper, haadred, haddblue, haadyellow, haadgreen, haadammo, rocketLauncher, bowcaster, grenadeLauncher, 
	shotgun;
	public static List<Item> allItems = new ArrayList<Item>();
	public static String[] armorNames = {"clonereg", "clonejet", "clonesniper", "cloneheavy", "cloneeng", "clonecom", "trooperreg", "troopersniper", "troopershock", "troopereng", "trooperdark", "trooperoff", "rebelreg", "rebelsniper", "rebelheavy", "rebeleng", "wookie", "bothanspy"};
    public static Item[] armors = new Item[armorNames.length * 4];
    public static ArmorMaterial cloneReg = EnumHelper.addArmorMaterial("Clone", "cloneReg", 20, new int[]{4, 8, 4, 2}, 9);
	public static Item speeder, iftt, iftx, snowspeeder;
    
	public static void init(){
		blaster = new ItemBlaster("blasterrifle").register();
		lightsaberblue = new ItemLightsaber("lightsaberblue").register();
		lightsaberred = new ItemLightsaber("lightsaberred").register();
		lightsabergreen = new ItemLightsaber("lightsabergreen").register();
		lightsaberpurple = new ItemLightsaber("lightsaberpurple").register();
		lightsaberorange = new ItemLightsaber("lightsaberorange").register();
		lightsaberyellow = new ItemLightsaber("lightsaberyellow").register();
		minigun = new ItemMinigun("minigun").register();
		bowcaster = new ItemWookieBowcaster("bowcaster").register();
		rocketLauncher = new ItemRocketLauncher("rocketlauncher").register();
		grenadeLauncher = new ItemGrenadeLauncher("grenadelauncher").register();
		shotgun = new ItemShotgun("shotgun").register();
		grenade = new ItemGrenade("grenade").register();
		detpack = new ItemDetpack("detpack").register();
		detonator = new ItemRemoteDetonator("detonator").register();
		sniper= new ItemSniperRifle("sniper").register();
		speeder = new ItemSpeeder("speeder").register();
		iftt = new ItemIFT("ift-t", "T").register();
		iftx = new ItemIFT("ift-x", "X").register();
		snowspeeder = new ItemSnowSpeeder("snowspeeder").register();
		haadred = new ItemHAAD("haad", "red").register();
		haddblue = new ItemHAAD("haad", "blue").register();
		haadgreen = new ItemHAAD("haad", "green").register();
		haadyellow = new ItemHAAD("haad", "yellow").register();
		haadammo = new ItemHAAD("haad", "ammo").register();
		registerArmorSets(armors, cloneReg);
	}
	public static void registerArmorSets(Item[] itema, ArmorMaterial material){
		String[] pieceNames = {"helmet", "chest", "leggings", "boots"};
		String pieceName = pieceNames[0];
		int armorNum = 0;
    	for(int i = 0; i < armors.length; i++){
        	itema[i] = new ItemBattlefrontArmor(material, armorNum, armorNames[(int)i/4] + pieceName.toString(), armorNames[(int)i/4]);
        	((ItemBattlefrontArmor) itema[i]).register();
        	armorNum++;
        	if(armorNum > 3){
        		armorNum = 0;
        	}
        	pieceName = pieceNames[armorNum];
    	}
    }
	public static Item getArmor(String name, int part){
		String[] pieceNames = {"helmet", "chest", "leggings", "boots"};
		for(int i = 0; i < armors.length; i++){
			if(armors[i].getUnlocalizedName().substring(5, 5 + name.length()).equals(name)){
				int j = i + part;
				return armors[j];
			}
		}
		return armors[0];
	}
	
	public static CreativeTabs tabSWBF = new CreativeTabs("swbf") {
	    @Override
	    @SideOnly(Side.CLIENT)
	    public Item getTabIconItem() {
	    	return armors[0];
	    }
	};
}
