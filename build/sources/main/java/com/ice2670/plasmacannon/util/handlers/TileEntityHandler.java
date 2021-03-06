package com.ice2670.plasmacannon.util.handlers;

import com.ice2670.plasmacannon.tileentities.TileEntityPlasmaCannon;
import com.ice2670.plasmacannon.tileentities.TileEntityTorpedoLauncher;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler
{
    public static void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityPlasmaCannon.class, "plasma_cannon");
        GameRegistry.registerTileEntity(TileEntityTorpedoLauncher.class, "torpedo_launcher");
    }
}
