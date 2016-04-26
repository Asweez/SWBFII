package com.apmods.swbf2.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.apmods.swbf2.main.Battlefront;

public class ItemBattlefrontArmor extends ItemArmor{
	int armor;
	String texture;
	private int jetTimer = 0;
	public ItemBattlefrontArmor(ArmorMaterial material, int armorType, String name, String texName)
	{
		super(material, 0, armorType);
		setUnlocalizedName(name);
		texture = texName;
		armor = armorType;
		this.setCreativeTab(ItemManager.tabSWBF);
	}

    /**
     * This method returns what file MC should use, to render the armor (make it visible from above).
     */
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if(armor == 2)
		{
			return Battlefront.MODID + ":textures/armor/" + texture + "_2.png";
		}
		else
		{
			return Battlefront.MODID + ":textures/armor/" + texture + "_1.png";
		}
	}
    public Item register(){
    	GameRegistry.registerItem(this, getUnlocalizedName().substring(5));
    	return this;
    }
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
    	

    }
}
