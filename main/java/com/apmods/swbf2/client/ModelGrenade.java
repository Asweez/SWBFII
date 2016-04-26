package com.apmods.swbf2.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGrenade extends ModelBase{
	public ModelRenderer cube;
	
	public ModelGrenade(){
		this.cube = new ModelRenderer(this, 0, 0);
		this.cube.addBox(-2.5f, 0, -2.5f, 5, 5, 1);
	}
	
	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
    	this.cube.render(p_78088_7_);
    }
}
