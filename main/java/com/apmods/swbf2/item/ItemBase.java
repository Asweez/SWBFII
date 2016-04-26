package com.apmods.swbf2.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.apmods.swbf2.main.Battlefront;

public class ItemBase extends Item{
	String tooltip;
	public ItemBase(String name){
		super();
		setUnlocalizedName(name);
		this.setCreativeTab(ItemManager.tabSWBF);
		
	}
    public ItemBase register(){
    	GameRegistry.registerItem(this, getUnlocalizedName().substring(5));
    	ItemManager.allItems.add(this);
    	return this;
    }
    public Item setItemTooltip(String s){
    	tooltip = s;
    	return this;
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
    	if(tooltip != null){
    		list.add(tooltip);
    	}
    }
}
