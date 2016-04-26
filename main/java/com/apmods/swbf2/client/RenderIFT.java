package com.apmods.swbf2.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.apmods.swbf2.entity.EntityIFT;
import com.apmods.swbf2.main.Battlefront;

public class RenderIFT extends Render {

	private static String letter;
	public static ResourceLocation IFT_texture = new ResourceLocation(Battlefront.MODID + ":textures/entity/IFT-T" + ".png");
	public static ModelIFT modelIFT;	
	
	public RenderIFT()
    {
    	super(Minecraft.getMinecraft().getRenderManager());
        this.modelIFT = new ModelIFT();
    }
	
	@Override
	public void doRender(Entity _entity, double posX, double posY, double posZ, float var8, float var9) {
		EntityIFT entity = (EntityIFT) _entity;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glTranslatef((float)posX, (float)posY + entity.height/2, (float)posZ);
//		if(entity.riddenByEntity != null){
			GL11.glRotatef(-90.0F - var8, 0.0F, 1.0F, 0.0F);
	        float f2 = (float)entity.getTimeSinceHit() - var9;
		        float f3 = entity.getDamageTaken() - var9;
		
		        if (f3 < 0.0F)
		        {
		            f3 = 0.0F;
		        }
		
		        if (f2 > 0.0F)
		        {
		            GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float)entity.getForwardDirection(), 1.0F, 0.0F, 0.0F);
		        }
//			}
//		else{
//			 float f2 = (float)entity.getTimeSinceHit() - var9;
//			 float f3 = entity.getDamageTaken() - var9;
//				
//		        if (f3 < 0.0F)
//		        {
//		            f3 = 0.0F;
//		        }
//			if (f2 > 0.0F)
//	        {
//	            GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float)entity.getForwardDirection(), 1.0F, 0.0F, 0.0F);
//	        }
//		}
		this.bindEntityTexture(entity);

		this.modelIFT.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return this.getEntityTexture((EntityIFT)var1);
	}
	protected ResourceLocation getEntityTexture(EntityIFT var1) {
		return new ResourceLocation(Battlefront.MODID + ":textures/entity/IFT-" + var1.getLetter() + ".png");
	}
}