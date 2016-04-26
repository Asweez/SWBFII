package com.apmods.swbf2.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.apmods.swbf2.entity.EntityIFT;
import com.apmods.swbf2.entity.EntityRocket;
import com.apmods.swbf2.main.Battlefront;

@SideOnly(Side.CLIENT)
public class RenderRocket extends Render
{
    private static ResourceLocation texture, texture2;
    
    public RenderRocket(ResourceLocation res, ResourceLocation tex){
        super(Minecraft.getMinecraft().getRenderManager());
    	texture = res;
    	texture2 = tex;
    	
    }
    public RenderRocket(){
        super(Minecraft.getMinecraft().getRenderManager());
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityRocket p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.bindEntityTexture(p_76986_1_);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
        GL11.glRotatef(p_76986_1_.prevRotationYaw + (p_76986_1_.rotationYaw - p_76986_1_.prevRotationYaw) * p_76986_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(p_76986_1_.prevRotationPitch + (p_76986_1_.rotationPitch - p_76986_1_.prevRotationPitch) * p_76986_9_, 0.0F, 0.0F, 1.0F);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        byte b0 = 0;
        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = (float)(0 + b0 * 10) / 32.0F;
        float f5 = (float)(5 + b0 * 10) / 32.0F;
        float f6 = 0.0F;
        float f7 = 0.15625F;
        float f8 = (float)(5 + b0 * 10) / 32.0F;
        float f9 = (float)(10 + b0 * 10) / 32.0F;
        float f10 = 0.05625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);

        GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(f10, f10, f10);
        GL11.glTranslatef(-4.0F, 0.0F, 0.0F);
        GL11.glNormal3f(f10, 0.0F, 0.0F);
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f8);
        worldRenderer.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f8);
        worldRenderer.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f9);
        worldRenderer.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f9);
        tessellator.draw();
        GL11.glNormal3f(-f10, 0.0F, 0.0F);
        worldRenderer.startDrawingQuads();
        worldRenderer.addVertexWithUV(-7.0D, 2.0D, -2.0D, (double)f6, (double)f8);
        worldRenderer.addVertexWithUV(-7.0D, 2.0D, 2.0D, (double)f7, (double)f8);
        worldRenderer.addVertexWithUV(-7.0D, -2.0D, 2.0D, (double)f7, (double)f9);
        worldRenderer.addVertexWithUV(-7.0D, -2.0D, -2.0D, (double)f6, (double)f9);
        tessellator.draw();

        for (int i = 0; i < 4; ++i)
        {
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, f10);
            worldRenderer.startDrawingQuads();
            worldRenderer.addVertexWithUV(-8.0D, -2.0D, 0.0D, (double)f2, (double)f4);
            worldRenderer.addVertexWithUV(8.0D, -2.0D, 0.0D, (double)f3, (double)f4);
            worldRenderer.addVertexWithUV(8.0D, 2.0D, 0.0D, (double)f3, (double)f5);
            worldRenderer.addVertexWithUV(-8.0D, 2.0D, 0.0D, (double)f2, (double)f5);
            tessellator.draw();
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityRocket spell)
    {
    	if(spell.shootingEntity != null){
	    	if(spell.shootingEntity.ridingEntity != null && spell.shootingEntity.ridingEntity instanceof EntityIFT){
	    		return new ResourceLocation(Battlefront.MODID + ":textures/entity/rocketift.png");
	    	}
    	}
		return new ResourceLocation(Battlefront.MODID + ":textures/entity/rocket.png");
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityRocket)p_110775_1_);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityRocket)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}