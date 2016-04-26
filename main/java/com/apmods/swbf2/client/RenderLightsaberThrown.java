package com.apmods.swbf2.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.apmods.swbf2.entity.EntityLightsaberThrown;

@SideOnly(Side.CLIENT)
public class RenderLightsaberThrown extends Render
{
    private Item field_94151_a;
    private int field_94150_f;
    private static final String __OBFID = "CL_00001008";
    
    public RenderLightsaberThrown(){
    	super(Minecraft.getMinecraft().getRenderManager());

    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doe
     */
    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
    {
        this.doRender((EntityLightsaberThrown)entity, x, y, z, p_76986_8_, partialTicks);
    }
    
    public void doRender(EntityLightsaberThrown entity, double x, double y, double z, float p_76986_8_, float partialTicks){
    	GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        this.bindTexture(TextureMap.locationBlocksTexture);
        Minecraft.getMinecraft().getRenderItem().renderItemModel(this.func_177082_d(entity));
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
    }

    public ItemStack func_177082_d(EntityLightsaberThrown p_177082_1_)
    {
        return new ItemStack(p_177082_1_.getLightsaber(), 1, 0);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return TextureMap.locationBlocksTexture;
    }
}