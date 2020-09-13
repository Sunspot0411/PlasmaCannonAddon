package com.ice2670.plasmacannon.entity;

import com.ice2670.plasmacannon.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class EntityLargePlasmaBall extends EntityPlasmaBall
{
    public double explosionPower = 0.1*powerFactorf;
    public int damagefactor =10* powerFactorf;

    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.<Boolean>createKey(net.minecraft.entity.projectile.EntityWitherSkull.class, DataSerializers.BOOLEAN);

    public EntityLargePlasmaBall(World worldIn)
    {
        super(worldIn);
        this.setSize(0.79F, 0.79F);
    }

    public EntityLargePlasmaBall(World worldIn, double x, double y, double z, int powerfactor)
    {
        super(worldIn, x, y, z, powerfactor);
        this.setSize(0.79F, 0.79F);
    }

    public static void registerFixesLargePlasmaBall(DataFixer fixer)
    {
        EntityPlasmaBall.registerFixesPlasmaBall(fixer, "PlasmaBall");
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */


    @SideOnly(Side.CLIENT)
    public EntityLargePlasmaBall(World worldIn, EntityLivingBase throwerIn, int powerfactor2)
    {
        super(worldIn, throwerIn, powerfactor2);
        this.setSize(0.79F, 0.79F);
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire effect on rendering.
     */
    public boolean isBurning()
    {
        return true;
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        compound.setByte("ExplosionPower", (byte)this.explosionPower);
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        if (compound.hasKey("ExplosionPower", 99))
        {
            this.explosionPower = compound.getByte("ExplosionPower");
        }
    }
    /**
     * Explosion resistance of a block relative to this entity
     */
    public float getExplosionResistance(Explosion explosionIn, World worldIn, BlockPos pos, IBlockState blockStateIn)
    {
        float f = super.getExplosionResistance(explosionIn, worldIn, pos, blockStateIn);
        Block block = blockStateIn.getBlock();

        if (this.isInvulnerable() && block.canEntityDestroy(blockStateIn, worldIn, pos, this) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this.thrower, pos, blockStateIn))
        {
            f = Math.min(0.8F, f);
        }

        double d=(double)f;
        float f1;

        if (powerFactorf > 68){

            f1 = (float) (0.01 * d);

        } else if (powerFactorf > 65) {

            f1 = (float) (0.02 * d);

        } else if (powerFactorf > 60) {

            f1 = (float) (0.03 * d);

        } else {
                f1 =(float) (0.056 * d);
            }


        return f1;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */

    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
                if (powerFactorf >= 20)
                {
                        result.entityHit.attackEntityFrom(DamageSource.DRAGON_BREATH, damagefactor);
                        result.entityHit.attackEntityFrom(DamageSource.LAVA, damagefactor);
                } else {
                        result.entityHit.attackEntityFrom(DamageSource.LAVA, damagefactor);
                }


                if (result.entityHit instanceof EntityLivingBase)
                {
                    if (powerFactorf >= 20)
                    {
                        AxisAlignedBB axis = new AxisAlignedBB(result.entityHit.posX - 2, result.entityHit.posY - 2, result.entityHit.posZ - 2,
                                result.entityHit.posX + 2, result.entityHit.posY + 2, result.entityHit.posZ + 2);
                        List<EntityLivingBase> targets = getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, axis);
                        for (EntityLivingBase mob : targets) {
                            (mob).setHealth((mob).getHealth() - damagefactor);
                            mob.hurtResistantTime = -1;
                        }
                    }

                    ((EntityLivingBase)result.entityHit).setFire(30);


                }

                if (result.entityHit instanceof EntityPlayer)
                {
                    if (powerFactorf >= 20)
                    {
                        AxisAlignedBB axis = new AxisAlignedBB(result.entityHit.posX - 2, result.entityHit.posY - 2, result.entityHit.posZ - 2,
                                result.entityHit.posX + 2, result.entityHit.posY + 2, result.entityHit.posZ + 2);
                        List<EntityLivingBase> targets = getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, axis);
                        for (EntityLivingBase mob : targets) {
                            (mob).setHealth((mob).getHealth() - damagefactor);
                            mob.hurtResistantTime = -1;
                        }

                    }
                    ((EntityLivingBase)result.entityHit).setFire(30);

                }
                this.world.createExplosion(this, result.entityHit.posX - 0.3*this.motionX, result.entityHit.posY - 0.3*this.motionY, result.entityHit.posZ - 0.3*this.motionZ, (float)this.explosionPower, net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.thrower));
                this.setDead();
            }



        }
    }



    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onHitBlock(IBlockState hitBlock, BlockPos pos)
    {
        if (!hitBlock.getMaterial().isSolid()) {
            // Go through non solid block
            return;
        } else {
            if (powerFactorf >= 55){
            getEntityWorld().destroyBlock(pos, false);
            }else if(powerFactorf >= 40 && powerFactorf <= 54)
            {
                if (hitBlock.getBlockHardness(world,pos)==1902)
                {
                    world.setBlockState(pos, BlockInit.BLOCK_BROKENACA2.getDefaultState(), 11);
                }
                if (hitBlock.getBlockHardness(world,pos)==1302)
                {
                    world.setBlockState(pos, BlockInit.BLOCK_BROKENACA3.getDefaultState(), 11);
                }
                if (hitBlock.getBlockHardness(world,pos)<=1003)
                {
                    getEntityWorld().destroyBlock(pos, false);
                }
            }else if(powerFactorf >= 10 && powerFactorf <= 39)
            {
                if (hitBlock.getBlockHardness(world,pos)==1902)
                {
                    world.setBlockState(pos, BlockInit.BLOCK_BROKENACA1.getDefaultState(), 11);
                }
                if (hitBlock.getBlockHardness(world,pos)==1302)
                {
                    world.setBlockState(pos, BlockInit.BLOCK_BROKENACA2.getDefaultState(), 11);
                }
                if (hitBlock.getBlockHardness(world,pos)==1002)
                {
                    world.setBlockState(pos, BlockInit.BLOCK_BROKENCA.getDefaultState(), 11);
                }
                if (hitBlock.getBlockHardness(world,pos)==902)
                {
                    world.setBlockState(pos, BlockInit.BLOCK_BROKENACA3.getDefaultState(), 11);
                }
                if (hitBlock.getBlockHardness(world,pos)==402)
                {
                    world.setBlockState(pos, BlockInit.BLOCK_BROKENTG.getDefaultState(), 11);
                }
                if (hitBlock.getBlockHardness(world,pos)<=400)
                {
                    getEntityWorld().destroyBlock(pos, false);
                }
            }
        }
        this.world.createExplosion(this, pos.getX() - 0.32*this.motionX, pos.getY() - 0.32*this.motionY, pos.getZ() - 0.32*this.motionZ, (float)this.explosionPower, net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.thrower));
        this.setDead();




    }




    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        return false;
    }

    protected void entityInit()
    {
        this.dataManager.register(INVULNERABLE, Boolean.valueOf(false));
    }

    /**
     * Return whether this skull comes from an invulnerable (aura) wither boss.
     */
    public boolean isInvulnerable()
    {
        return ((Boolean)this.dataManager.get(INVULNERABLE)).booleanValue();
    }

    /**
     * Set whether this skull comes from an invulnerable (aura) wither boss.
     */
    public void setInvulnerable(boolean invulnerable)
    {
        this.dataManager.set(INVULNERABLE, Boolean.valueOf(invulnerable));
    }

    protected boolean isFireballFiery()
    {
        return false;
    }
}
