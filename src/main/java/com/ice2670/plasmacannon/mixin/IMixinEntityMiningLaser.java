package com.ice2670.plasmacannon.mixin;

import net.minecraft.world.World;

public interface IMixinEntityMiningLaser
{
    public double getX();

    public double getY();

    public double getZ();

    public World getWorld();

}
