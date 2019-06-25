package com.ice2670.plasmacannon.init;

import com.ice2670.plasmacannon.Main;
import com.ice2670.plasmacannon.entity.*;
import com.ice2670.plasmacannon.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit
{
    public static void registerEntities()
    {
        registerEntity("plasmaball", EntityPlasmaBall.class, Reference.ENTITY_PLASMABALL, 200);
        registerEntity("largeplasmaball", EntityLargePlasmaBall.class, Reference.ENTITY_lARGEPLASMABALL, 200);
        registerEntity("fasttorpedo", EntityFastTorpedo.class, Reference.FAST_TORPEDO, 200);
        registerEntity("autotorpedo", EntityAutoTorpedo.class, Reference.AUTO_TORPEDO, 200);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range)
    {

        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, 200, 1, true);
    }
}
