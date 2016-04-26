package com.apmods.swbf2.item;

public class ItemHAAD extends ItemBase{

	public String color;
	
	public ItemHAAD(String name, String color) {
		super(name + "_" + color);
		this.color = color;
	}

}
