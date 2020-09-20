package com.ice2670.plasmacannon.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Entity.class, priority = 2018)
public class MixinEntity implements IMixinEntityMiningLaser
{
    public final Entity thisAsEntity = Entity.class.cast(this);
    @Shadow
    public double posX;
    /** Y position of this entity, located at the bottom of its bounding box (its feet) */
    @Shadow
    public double posY;
    /** Z position of this entity, located at the center of its bounding box. */
    @Shadow
    public double posZ;
    @Shadow
    public World world;

    @Override
    public double getX() {
        return posX;
    }

    @Override
    public double getY() {
        return posY;
    }

    @Override
    public double getZ() {
        return posZ;
    }

    @Override
    public World getWorld() {
        return world;
    }
}
