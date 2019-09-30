package com.ice2670.plasmacannon.tileentities;


import com.ice2670.plasmacannon.blocks.BlockTorpedoLauncher;
import com.ice2670.plasmacannon.entity.*;
import com.ice2670.plasmacannon.init.ItemInit;
import com.ice2670.plasmacannon.util.handlers.SoundsHandler;
import net.minecraft.block.state.IBlockState;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import valkyrienwarfare.api.IPhysicsEntity;
import valkyrienwarfare.api.IPhysicsEntityManager;
import valkyrienwarfare.api.TransformType;

//import valkyrienwarfare.api.IPhysicsEntity;
//import valkyrienwarfare.api.IPhysicsEntityManager;
//import valkyrienwarfare.api.TransformType;

public class TileEntityTorpedoLauncher extends TileEntity
{
    int torpedoload = 0;
    int ftorpedoload = 0;

    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);

        if (nbt.hasKey("torpedoload"))
        {
            this.torpedoload = nbt.getInteger("torpedoload");
        }

        if (nbt.hasKey("ftorpedoload"))
        {
            this.ftorpedoload = nbt.getInteger("ftorpedoload");
        }

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("torpedoload", this.torpedoload);
        nbt.setInteger("ftorpedoload", this.ftorpedoload);
        return nbt;
    }



    public void launchtorpedo(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn)
    {
        EnumFacing enumfacing = (EnumFacing) state.getValue(BlockTorpedoLauncher.FACING);
        double d0 = pos.getX() + 0.5D + (float) enumfacing.getFrontOffsetX();
        double d1 = pos.getY() + 0.2D + (float) enumfacing.getFrontOffsetY();
        double d2 = pos.getZ() + 0.5D + (float) enumfacing.getFrontOffsetZ();
        if(this.torpedoload == 0 && this.ftorpedoload == 0)
        {
            if (world.isRemote) {
                playerIn.sendMessage(new TextComponentString("no torpedo loaded"));
            }
        }




        if(this.torpedoload == 1){
            if(!worldIn.isRemote){
                Vec3d playview2 = playerIn.getLookVec();
                if (enumfacing == EnumFacing.NORTH) {
                    EntityFastTorpedo torpedo1 = new EntityFastTorpedo(worldIn, d0, d1, d2-0.5, 9);
                    torpedo1.shoot(playview2.x, playview2.y, playview2.z, 4, 0.00F);
                    worldIn.spawnEntity(torpedo1);
                }

                if (enumfacing == EnumFacing.SOUTH) {
                    EntityFastTorpedo torpedo1 = new EntityFastTorpedo(worldIn, d0, d1, d2+0.5, 9);
                    torpedo1.shoot(playview2.x, playview2.y, playview2.z, 4, 0.00F);
                    worldIn.spawnEntity(torpedo1);
                }

                if (enumfacing == EnumFacing.EAST) {
                    EntityFastTorpedo torpedo1 = new EntityFastTorpedo(worldIn, d0+0.5, d1, d2, 9);
                    torpedo1.shoot(playview2.x, playview2.y, playview2.z, 4, 0.00F);
                    worldIn.spawnEntity(torpedo1);
                }

                if (enumfacing == EnumFacing.WEST) {
                    EntityFastTorpedo torpedo1 = new EntityFastTorpedo(worldIn, d0-0.5, d1, d2, 9);
                    torpedo1.shoot(playview2.x, playview2.y, playview2.z, 4, 0.00F);
                    worldIn.spawnEntity(torpedo1);
                }

                if (enumfacing == EnumFacing.UP) {
                    EntityFastTorpedo torpedo1 = new EntityFastTorpedo(worldIn, d0, d1+0.5, d2, 9);
                    torpedo1.shoot(playview2.x, playview2.y, playview2.z, 4, 0.00F);
                    worldIn.spawnEntity(torpedo1);
                }

                if (enumfacing == EnumFacing.DOWN) {
                    EntityFastTorpedo torpedo1 = new EntityFastTorpedo(worldIn, d0, d1-0.5, d2, 9);
                    torpedo1.shoot(playview2.x, playview2.y, playview2.z, 4, 0.00F);
                    worldIn.spawnEntity(torpedo1);
                }

            }
            worldIn.playSound(playerIn, pos, SoundsHandler.TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON, SoundCategory.BLOCKS,8.0F, 1.0F);
            this.torpedoload = 0;
            this.ftorpedoload = 0;
        } else if (this.ftorpedoload == 1)
        {
            if(!worldIn.isRemote){
                Vec3d playview2 = playerIn.getLookVec();
                if (enumfacing == EnumFacing.NORTH) {
                    EntityFastTorpedo fastTorpedo = new EntityFastTorpedo(worldIn, d0, d1, d2-0.5, 5);
                    fastTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6F, 0.00F);
                    worldIn.spawnEntity(fastTorpedo);
                }

                if (enumfacing == EnumFacing.SOUTH) {
                    EntityFastTorpedo fastTorpedo = new EntityFastTorpedo(worldIn, d0, d1, d2+0.5, 5);
                    fastTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6F, 0.00F);
                    worldIn.spawnEntity(fastTorpedo);
                }

                if (enumfacing == EnumFacing.EAST) {
                    EntityFastTorpedo fastTorpedo = new EntityFastTorpedo(worldIn, d0+0.5, d1, d2, 5);
                    fastTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6F, 0.00F);
                    worldIn.spawnEntity(fastTorpedo);
                }

                if (enumfacing == EnumFacing.WEST) {
                    EntityFastTorpedo fastTorpedo = new EntityFastTorpedo(worldIn, d0-0.5, d1, d2, 5);
                    fastTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6F, 0.00F);
                    worldIn.spawnEntity(fastTorpedo);
                }

                if (enumfacing == EnumFacing.UP) {
                    EntityFastTorpedo fastTorpedo = new EntityFastTorpedo(worldIn, d0, d1+0.5, d2, 5);
                    fastTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6F, 0.00F);
                    worldIn.spawnEntity(fastTorpedo);
                }

                if (enumfacing == EnumFacing.DOWN) {
                    EntityFastTorpedo fastTorpedo = new EntityFastTorpedo(worldIn, d0, d1-0.5, d2, 5);
                    fastTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6F, 0.00F);
                    worldIn.spawnEntity(fastTorpedo);
                }
            }
            worldIn.playSound(playerIn, pos, SoundsHandler.TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON, SoundCategory.BLOCKS,8.0F, 1.0F);
            this.torpedoload = 0;
            this.ftorpedoload = 0;
        }


    }

    public void shoottorpedo(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand)
    {
        EnumFacing enumfacing = (EnumFacing) state.getValue(BlockTorpedoLauncher.FACING);
        double d0 = pos.getX() + 0.5D + (float) enumfacing.getFrontOffsetX();
        double d1 = pos.getY() + 0.2D + (float) enumfacing.getFrontOffsetY();
        double d2 = pos.getZ() + 0.5D + (float) enumfacing.getFrontOffsetZ();

        if (this.ftorpedoload >= 1 || this.torpedoload >=1){
            if (world.isRemote) {
                playerIn.sendMessage(new TextComponentString("Launcher is full"));
            }
        }else
        {

            ItemStack heldItem = playerIn.getHeldItem(hand);
            heldItem.shrink(1);


            if (this.torpedoload == 0 && this.ftorpedoload == 0)
            {

                if (!worldIn.isRemote) {
                    Vec3d playview2 = playerIn.getLookVec();
                    if (enumfacing == EnumFacing.NORTH) {
                        EntityAutoTorpedo autoTorpedo = new EntityAutoTorpedo(worldIn, d0, d1, d2-0.4, 3);
                        autoTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6, 0.00F);
                        worldIn.spawnEntity(autoTorpedo);
                    }

                    if (enumfacing == EnumFacing.SOUTH) {
                        EntityAutoTorpedo autoTorpedo = new EntityAutoTorpedo(worldIn, d0, d1, d2+0.4, 3);
                        autoTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6, 0.00F);
                        worldIn.spawnEntity(autoTorpedo);
                    }

                    if (enumfacing == EnumFacing.EAST) {
                        EntityAutoTorpedo autoTorpedo = new EntityAutoTorpedo(worldIn, d0+0.4, d1, d2, 3);
                        autoTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6, 0.00F);
                        worldIn.spawnEntity(autoTorpedo);
                    }

                    if (enumfacing == EnumFacing.WEST) {
                        EntityAutoTorpedo autoTorpedo = new EntityAutoTorpedo(worldIn, d0-0.4, d1, d2, 3);
                        autoTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6, 0.00F);
                        worldIn.spawnEntity(autoTorpedo);
                    }

                    if (enumfacing == EnumFacing.UP) {
                        EntityAutoTorpedo autoTorpedo = new EntityAutoTorpedo(worldIn, d0, d1+0.4, d2, 3);
                        autoTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6, 0.00F);
                        worldIn.spawnEntity(autoTorpedo);
                    }

                    if (enumfacing == EnumFacing.DOWN) {
                        EntityAutoTorpedo autoTorpedo = new EntityAutoTorpedo(worldIn, d0, d1-0.4, d2, 3);
                        autoTorpedo.shoot(playview2.x, playview2.y, playview2.z, 6, 0.00F);
                        worldIn.spawnEntity(autoTorpedo);
                    }

                }
                worldIn.playSound(playerIn, pos, SoundsHandler.TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON, SoundCategory.BLOCKS, 8.0F, 1.0F);
            }


        }
    }



    public void torpedoLoad(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand){
        if (this.torpedoload == 0 && this.ftorpedoload == 0){
            ItemStack heldItem = playerIn.getHeldItem(hand);
            if (heldItem.getItem() == ItemInit.TORPEDO) {
                this.torpedoload = 1;
            } else if (heldItem.getItem() == ItemInit.FASTTORPEDO){
                this.ftorpedoload = 1;
            }
            heldItem.shrink(1);
        }

        else if(this.torpedoload >= 1 || this.ftorpedoload >= 1) {
            if (world.isRemote) {
                playerIn.sendMessage(new TextComponentString("torpedo loaded"));
            }
        }

    }
}
