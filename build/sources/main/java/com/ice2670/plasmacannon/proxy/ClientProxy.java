package com.ice2670.plasmacannon.proxy;


import com.ice2670.plasmacannon.entity.*;
import com.ice2670.plasmacannon.entity.render.RenderEntityTorpedoPrimed;
import com.ice2670.plasmacannon.entity.render.RenderFastTorpedo;
import com.ice2670.plasmacannon.entity.render.RenderLargePlasmaBall;
import com.ice2670.plasmacannon.entity.render.RenderPlasmaBall;
import com.ice2670.plasmacannon.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String filename, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, filename), id));
    }


    @Override
    public void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPlasmaBall.class, new IRenderFactory<EntityPlasmaBall>()
        {

            public Render<? super EntityPlasmaBall> createRenderFor(RenderManager manager)
            {
                return new RenderPlasmaBall(manager, 1F);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityLargePlasmaBall.class, new IRenderFactory<EntityLargePlasmaBall>()
        {

            public Render<? super EntityLargePlasmaBall> createRenderFor(RenderManager manager)
            {
                return new RenderLargePlasmaBall(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityFastTorpedo.class, new IRenderFactory<EntityFastTorpedo>()
        {

            public Render<? super EntityFastTorpedo> createRenderFor(RenderManager manager)
            {
                return new RenderFastTorpedo(manager);
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityTorpedoPrimed.class, new IRenderFactory<EntityTorpedoPrimed>()
        {

            public Render<? super EntityTorpedoPrimed> createRenderFor(RenderManager manager)
            {
                return new RenderEntityTorpedoPrimed(manager);
            }
        });
    }
}
