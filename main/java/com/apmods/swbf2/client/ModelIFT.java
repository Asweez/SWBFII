package com.apmods.swbf2.client;

import java.util.HashMap;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

import com.apmods.swbf2.MCA.MCAClientLibrary.MCAModelRenderer;
import com.apmods.swbf2.MCA.MCACommonLibrary.MCAVersionChecker;
import com.apmods.swbf2.MCA.MCACommonLibrary.animation.AnimationHandler;
import com.apmods.swbf2.MCA.MCACommonLibrary.math.Matrix4f;
import com.apmods.swbf2.MCA.MCACommonLibrary.math.Quaternion;
import com.apmods.swbf2.entity.EntityIFT;

public class ModelIFT extends ModelBase {
public final int MCA_MIN_REQUESTED_VERSION = 5;
public HashMap<String, MCAModelRenderer> parts = new HashMap<String, MCAModelRenderer>();

MCAModelRenderer base;
MCAModelRenderer baseSurface;
MCAModelRenderer slant;
MCAModelRenderer leftArm;
MCAModelRenderer rightArm;
MCAModelRenderer rightSlant;
MCAModelRenderer leftSlant;
MCAModelRenderer upperSeat;
MCAModelRenderer leftGun;
MCAModelRenderer rightGun;
MCAModelRenderer upGun;
MCAModelRenderer leftGunMid;
MCAModelRenderer rightGunMid;
MCAModelRenderer leftGunBarrel;
MCAModelRenderer rightGunBarrel;
MCAModelRenderer leftGunEnd;
MCAModelRenderer rightGunEnd;

public ModelIFT()
{
MCAVersionChecker.checkForLibraryVersion(getClass(), MCA_MIN_REQUESTED_VERSION);

textureWidth = 256;
textureHeight = 256;

base = new MCAModelRenderer(this, "base", 0, 0);
base.mirror = false;
base.addBox(-2.0F, 0.0F, 0.0F, 34, 28, 28);
base.setInitialRotationPoint(-16.0F, -18.0F, -14.0F);
base.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
base.setTextureSize(256, 256);
parts.put(base.boxName, base);

baseSurface = new MCAModelRenderer(this, "baseSurface", 0, 57);
baseSurface.mirror = false;
baseSurface.addBox(0.0F, 0.0F, 0.0F, 37, 3, 28);
baseSurface.setInitialRotationPoint(32.0F, 0.0F, 0.0F);
baseSurface.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
baseSurface.setTextureSize(256, 256);
parts.put(baseSurface.boxName, baseSurface);
base.addChild(baseSurface);

slant = new MCAModelRenderer(this, "slant", 125, 0);
slant.mirror = false;
slant.addBox(0.0F, 0.0F, 0.0F, 41, 12, 20);
slant.setInitialRotationPoint(25.0F, 12.0F, 4.0F);
slant.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, -0.2419219F, 0.9702957F)).transpose());
slant.setTextureSize(256, 256);
parts.put(slant.boxName, slant);
base.addChild(slant);

leftArm = new MCAModelRenderer(this, "leftArm", 0, 91);
leftArm.mirror = false;
leftArm.addBox(0.0F, 0.0F, 0.0F, 96, 1, 14);
leftArm.setInitialRotationPoint(-6.0F, -1.0F, -17.0F);
leftArm.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.1305262F, 0.0F, 0.0F, 0.9914449F)).transpose());
leftArm.setTextureSize(256, 256);
parts.put(leftArm.boxName, leftArm);
base.addChild(leftArm);

rightArm = new MCAModelRenderer(this, "rightArm", 0, 106);
rightArm.mirror = false;
rightArm.addBox(0.0F, 0.0F, 0.0F, 96, 1, 14);
rightArm.setInitialRotationPoint(-6.0F, 2.0F, 30.0F);
rightArm.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.1305262F, 0.0F, 0.0F, 0.9914449F)).transpose());
rightArm.setTextureSize(256, 256);
parts.put(rightArm.boxName, rightArm);
base.addChild(rightArm);

rightSlant = new MCAModelRenderer(this, "rightSlant", 125, 33);
rightSlant.mirror = false;
rightSlant.addBox(0.0F, 0.0F, 0.0F, 37, 3, 14);
rightSlant.setInitialRotationPoint(-4.0F, 7.0F, 24.0F);
rightSlant.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.28401536F, 0.0F, 0.0F, 0.95881975F)).transpose());
rightSlant.setTextureSize(256, 256);
parts.put(rightSlant.boxName, rightSlant);
base.addChild(rightSlant);

leftSlant = new MCAModelRenderer(this, "leftSlant", 125, 33);
leftSlant.mirror = false;
leftSlant.addBox(0.0F, 0.0F, 0.0F, 37, 3, 14);
leftSlant.setInitialRotationPoint(-4.0F, 0.0F, -8.0F);
leftSlant.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.28401536F, 0.0F, 0.0F, 0.95881975F)).transpose());
leftSlant.setTextureSize(256, 256);
parts.put(leftSlant.boxName, leftSlant);
base.addChild(leftSlant);

upperSeat = new MCAModelRenderer(this, "UpperSeat", 122, 51);
upperSeat.mirror = false;
upperSeat.addBox(0.0F, 0.0F, 0.0F, 1, 12, 14);
upperSeat.setInitialRotationPoint(20.0F, 28.0F, 7.0F);
upperSeat.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
upperSeat.setTextureSize(256, 256);
parts.put(upperSeat.boxName, upperSeat);
base.addChild(upperSeat);

leftGun = new MCAModelRenderer(this, "LeftGun", 0, 122);
leftGun.mirror = false;
leftGun.addBox(0.0F, 0.0F, 0.0F, 18, 12, 6);
leftGun.setInitialRotationPoint(-3.0F, 9.0F, 28.0F);
leftGun.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
leftGun.setTextureSize(256, 256);
parts.put(leftGun.boxName, leftGun);
base.addChild(leftGun);

rightGun = new MCAModelRenderer(this, "RightGun", 0, 142);
rightGun.mirror = false;
rightGun.addBox(0.0F, 0.0F, 0.0F, 18, 12, 6);
rightGun.setInitialRotationPoint(-3.0F, 9.0F, -6.0F);
rightGun.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
rightGun.setTextureSize(256, 256);
parts.put(rightGun.boxName, rightGun);
base.addChild(rightGun);

upGun = new MCAModelRenderer(this, "upGun", 152, 52);
upGun.mirror = false;
upGun.addBox(0.0F, 0.0F, 0.0F, 16, 2, 2);
upGun.setInitialRotationPoint(1.0F, 4.0F, 6.0F);
upGun.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
upGun.setTextureSize(256, 256);
parts.put(upGun.boxName, upGun);
upperSeat.addChild(upGun);

leftGunMid = new MCAModelRenderer(this, "LeftGunMid", 50, 122);
leftGunMid.mirror = false;
leftGunMid.addBox(0.0F, 0.0F, 0.0F, 12, 8, 4);
leftGunMid.setInitialRotationPoint(18.0F, 2.0F, 1.0F);
leftGunMid.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
leftGunMid.setTextureSize(256, 256);
parts.put(leftGunMid.boxName, leftGunMid);
leftGun.addChild(leftGunMid);

rightGunMid = new MCAModelRenderer(this, "RightGunMid", 50, 142);
rightGunMid.mirror = false;
rightGunMid.addBox(0.0F, 0.0F, 0.0F, 12, 8, 4);
rightGunMid.setInitialRotationPoint(18.0F, 2.0F, 1.0F);
rightGunMid.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
rightGunMid.setTextureSize(256, 256);
parts.put(rightGunMid.boxName, rightGunMid);
rightGun.addChild(rightGunMid);

leftGunBarrel = new MCAModelRenderer(this, "leftGunBarrel", 82, 122);
leftGunBarrel.mirror = false;
leftGunBarrel.addBox(0.0F, 0.0F, 0.0F, 28, 1, 1);
leftGunBarrel.setInitialRotationPoint(12.0F, 3.5F, 1.5F);
leftGunBarrel.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
leftGunBarrel.setTextureSize(256, 256);
parts.put(leftGunBarrel.boxName, leftGunBarrel);
leftGunMid.addChild(leftGunBarrel);

rightGunBarrel = new MCAModelRenderer(this, "rightGunBarrel", 82, 142);
rightGunBarrel.mirror = false;
rightGunBarrel.addBox(0.0F, 0.0F, 0.0F, 28, 1, 1);
rightGunBarrel.setInitialRotationPoint(12.0F, 3.5F, 1.5F);
rightGunBarrel.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
rightGunBarrel.setTextureSize(256, 256);
parts.put(rightGunBarrel.boxName, rightGunBarrel);
rightGunMid.addChild(rightGunBarrel);

leftGunEnd = new MCAModelRenderer(this, "leftGunEnd", 141, 122);
leftGunEnd.mirror = false;
leftGunEnd.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2);
leftGunEnd.setInitialRotationPoint(28.0F, -0.5F, -0.5F);
leftGunEnd.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
leftGunEnd.setTextureSize(256, 256);
parts.put(leftGunEnd.boxName, leftGunEnd);
leftGunBarrel.addChild(leftGunEnd);

rightGunEnd = new MCAModelRenderer(this, "rightGunEnd", 141, 142);
rightGunEnd.mirror = false;
rightGunEnd.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2);
rightGunEnd.setInitialRotationPoint(28.0F, -0.5F, -0.5F);
rightGunEnd.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
rightGunEnd.setTextureSize(256, 256);
parts.put(rightGunEnd.boxName, rightGunEnd);
rightGunBarrel.addChild(rightGunEnd);

}

@Override
public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) 
{
EntityIFT entity = (EntityIFT)par1Entity;

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