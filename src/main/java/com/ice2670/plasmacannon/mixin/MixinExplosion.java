package com.ice2670.plasmacannon.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Mixin(value = Explosion.class, priority = 2018)

public abstract class MixinExplosion
{
    @Shadow
    @Final
    private Vec3d position;
    @Shadow
    @Final
    private boolean causesFire;
    /** whether or not this explosion spawns smoke particles */
    @Shadow
    @Final
    private boolean damagesTerrain;
    @Shadow
    @Final
    private Random random;
    @Shadow
    @Final
    private World world;
    @Shadow
    @Final
    private double x;
    @Shadow
    @Final
    private double y;
    @Shadow
    @Final
    private double z;
    @Shadow
    @Final
    private Entity exploder;
    @Shadow
    @Final
    private float size;
    /** A list of ChunkPositions of blocks affected by this explosion */
    @Shadow
    @Final
    private List<BlockPos> affectedBlockPositions;

    /** Maps players to the knockback vector applied by the explosion, to send to the client */
    @Shadow
    @Final
    private Map<EntityPlayer, Vec3d> playerKnockbackMap;

    @Shadow
    public Vec3d getPosition(){
        return this.position;
    };


}

