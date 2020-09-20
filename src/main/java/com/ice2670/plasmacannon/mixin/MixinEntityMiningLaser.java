package com.ice2670.plasmacannon.mixin;

import ic2.api.event.LaserEvent;
import ic2.api.event.LaserEvent.LaserExplodesEvent;
import ic2.core.ExplosionIC2;
import ic2.core.item.tool.EntityMiningLaser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;



@Mixin(value = EntityMiningLaser.class, priority = 2018)
public class MixinEntityMiningLaser
{

    @Shadow(remap = false)
    public float range = 0.0F;
    @Shadow(remap = false)
    public float power = 0.0F;
    @Shadow(remap = false)
    public int blockBreaks = 0;
    @Shadow(remap = false)
    public boolean explosive = false;
    @Shadow(remap = false)
    public static final double laserSpeed = 1.0D;
    @Shadow(remap = false)
    public EntityLivingBase owner;
    @Shadow(remap = false)
    public boolean headingSet = false;
    @Shadow(remap = false)
    public boolean smelt = false;
    @Shadow(remap = false)
    private int ticksInAir = 0;


    @Redirect(method = "explode", at = @At(value = "INVOKE", target="Lic2/core/ExplosionIC2;doExplosion()V"),remap = false)
    public void doExplosionRedirect(ExplosionIC2 explosionIC2)
    {
        Explosion explosion = new Explosion(IMixinEntityMiningLaser.class.cast(this).getWorld(), null, IMixinEntityMiningLaser.class.cast(this).getX(), IMixinEntityMiningLaser.class.cast(this).getY(), IMixinEntityMiningLaser.class.cast(this).getZ(), 3, true, false);
        explosion.doExplosionA();
        explosion.doExplosionB(true);
    }


    @Shadow(remap = false)
    void copyDataFromEvent(LaserEvent event) {
        this.owner = event.owner;
        this.range = event.range;
        this.power = event.power;
        this.blockBreaks = event.blockBreaks;
        this.explosive = event.explosive;
        this.smelt = event.smelt;
    }

}
