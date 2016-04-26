package com.apmods.swbf2.client;

import java.util.HashMap;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

import com.apmods.swbf2.MCA.MCAClientLibrary.MCAModelRenderer;
import com.apmods.swbf2.MCA.MCACommonLibrary.MCAVersionChecker;
import com.apmods.swbf2.MCA.MCACommonLibrary.animation.AnimationHandler;
import com.apmods.swbf2.MCA.MCACommonLibrary.math.Matrix4f;
import com.apmods.swbf2.MCA.MCACommonLibrary.math.Quaternion;
import com.apmods.swbf2.entity.EntitySpeeder;

public class ModelSpeeder extends ModelBase {
	public final int MCA_MIN_REQUESTED_VERSION = 5;
	public HashMap<String, MCAModelRenderer> parts = new HashMap<String, MCAModelRenderer>();
	
	MCAModelRenderer base;
	MCAModelRenderer leftBar;
	MCAModelRenderer rightBar;
	MCAModelRenderer leftExhaust;
	MCAModelRenderer rightExhaust;
	MCAModelRenderer chest;
	MCAModelRenderer handle;
	MCAModelRenderer leftBarPanel;
	MCAModelRenderer rightBarPanel;
	MCAModelRenderer chestLock;
	
	public ModelSpeeder()
	{
	MCAVersionChecker.checkForLibraryVersion(getClass(), MCA_MIN_REQUESTED_VERSION);
	
	textureWidth = 64;
	textureHeight = 64;
	
	base = new MCAModelRenderer(this, "base", 0, 0);
	base.mirror = false;
	base.addBox(6.0F, 0.0F, 0.0F, 18, 4, 9);
	base.setInitialRotationPoint(-14.0F, 2.0F, -4.0F);
	base.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	base.setTextureSize(64, 64);
	parts.put(base.boxName, base);
	
	leftBar = new MCAModelRenderer(this, "leftBar", 0, 14);
	leftBar.mirror = false;
	leftBar.addBox(0.0F, 0.0F, 0.0F, 22, 1, 1);
	leftBar.setInitialRotationPoint(21.0F, 2.0F, 2.0F);
	leftBar.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	leftBar.setTextureSize(64, 64);
	parts.put(leftBar.boxName, leftBar);
	base.addChild(leftBar);
	
	rightBar = new MCAModelRenderer(this, "rightBar", 0, 14);
	rightBar.mirror = false;
	rightBar.addBox(0.0F, 0.0F, 0.0F, 22, 1, 1);
	rightBar.setInitialRotationPoint(21.0F, 2.0F, 6.0F);
	rightBar.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	rightBar.setTextureSize(64, 64);
	parts.put(rightBar.boxName, rightBar);
	base.addChild(rightBar);
	
	leftExhaust = new MCAModelRenderer(this, "leftExhaust", 11, 18);
	leftExhaust.mirror = false;
	leftExhaust.addBox(0.0F, 0.0F, 0.0F, 7, 5, 2);
	leftExhaust.setInitialRotationPoint(5.0F, -0.5F, -1.3F);
	leftExhaust.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	leftExhaust.setTextureSize(64, 64);
	parts.put(leftExhaust.boxName, leftExhaust);
	base.addChild(leftExhaust);
	
	rightExhaust = new MCAModelRenderer(this, "rightExhaust", 11, 18);
	rightExhaust.mirror = false;
	rightExhaust.addBox(0.0F, 0.0F, 0.0F, 7, 5, 2);
	rightExhaust.setInitialRotationPoint(5.0F, -0.5F, 8.3F);
	rightExhaust.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	rightExhaust.setTextureSize(64, 64);
	parts.put(rightExhaust.boxName, rightExhaust);
	base.addChild(rightExhaust);
	
	chest = new MCAModelRenderer(this, "chest", 0, 34);
	chest.mirror = false;
	chest.addBox(0.0F, 0.0F, 0.0F, 5, 5, 5);
	chest.setInitialRotationPoint(4.0F, -0.5F, 2.0F);
	chest.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	chest.setTextureSize(64, 64);
	parts.put(chest.boxName, chest);
	base.addChild(chest);
	
	handle = new MCAModelRenderer(this, "handle", 30, 17);
	handle.mirror = false;
	handle.addBox(0.0F, 0.0F, 0.0F, 1, 5, 11);
	handle.setInitialRotationPoint(20.0F, 3.0F, -1.0F);
	handle.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	handle.setTextureSize(64, 64);
	parts.put(handle.boxName, handle);
	base.addChild(handle);
	
	leftBarPanel = new MCAModelRenderer(this, "leftBarPanel", 0, 18);
	leftBarPanel.mirror = false;
	leftBarPanel.addBox(17.0F, -2.4F, -1.0F, 4, 6, 1);
	leftBarPanel.setInitialRotationPoint(0.0F, 0.0F, 0.0F);
	leftBarPanel.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.27563736F, 0.0F, 0.0F, 0.9612617F)).transpose());
	leftBarPanel.setTextureSize(64, 64);
	parts.put(leftBarPanel.boxName, leftBarPanel);
	leftBar.addChild(leftBarPanel);
	
	rightBarPanel = new MCAModelRenderer(this, "rightBarPanel", 0, 25);
	rightBarPanel.mirror = false;
	rightBarPanel.addBox(17.0F, -3.0F, 1.0F, 4, 6, 1);
	rightBarPanel.setInitialRotationPoint(0.0F, 0.0F, 0.0F);
	rightBarPanel.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.27563736F, 0.0F, 0.0F, 0.9612617F)).transpose());
	rightBarPanel.setTextureSize(64, 64);
	parts.put(rightBarPanel.boxName, rightBarPanel);
	rightBar.addChild(rightBarPanel);
	
	chestLock = new MCAModelRenderer(this, "chestLock", 22, 34);
	chestLock.mirror = false;
	chestLock.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
	chestLock.setInitialRotationPoint(-0.75F, 2.0F, 2.0F);
	chestLock.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
	chestLock.setTextureSize(64, 64);
	parts.put(chestLock.boxName, chestLock);
	chest.addChild(chestLock);
	
	}
	
	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) 
	{
	EntitySpeeder entity = (EntitySpeeder)par1Entity;
	
	
	//Render every non-child part
	base.render(par7);
	}
	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {}
	
	public MCAModelRenderer getModelRendererFromName(String name)
	{
	return parts.get(name);
	}
}