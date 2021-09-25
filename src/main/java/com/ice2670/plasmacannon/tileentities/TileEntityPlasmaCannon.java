package com.ice2670.plasmacannon.tileentities;

import com.ice2670.plasmacannon.entity.EntityLargePlasmaBall;
import com.ice2670.plasmacannon.init.BlockInit;
import com.ice2670.plasmacannon.util.handlers.SoundsHandler;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;

import java.util.Random;

import static com.ice2670.plasmacannon.blocks.BlockPlasmaCannon.FACING;

public class TileEntityPlasmaCannon extends TileEntity implements ITickable, IEnergySink
{
    int powerfactor;
    public double maxenergy = 10000;
    double energy = 0.0D;
    int ticks = 0;

    //data synchronization required codes[
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);

        if (nbt.hasKey("energy"))
        {
            this.energy = nbt.getDouble("energy");
        }

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setDouble("energy", this.energy);
        return nbt;
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getNbtCompound());
    }

    private IBlockState getState() {
        return world.getBlockState(pos);
    }

    //]


    public void fireplasma(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
        //direction
        EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
        double d0 = pos.getX() + 0.5D + (float) enumfacing.getFrontOffsetX();
        double d1 = pos.getY() + 0.164D + (float) enumfacing.getFrontOffsetY();
        double d2 = pos.getZ() + 0.5D + (float) enumfacing.getFrontOffsetZ();

        int x = this.pos.getX();
        int y = this.pos.getY();
        int z = this.pos.getZ();

        BlockPos pos2;
        int i = 1;

        // [ProgramCrafter/23-09-2021]
        // possibly this code can break, if ship is built near world border
        // but ValkyrienSkies thinks that ever 512K/1M blocks cannot be accessed
        if (enumfacing == EnumFacing.NORTH) {
            while (i < 71) {
                pos2 = new BlockPos(x, y, z + i);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR) {
                    i++;
                } else {
                    break;
                }
            }
        } else if (enumfacing == EnumFacing.SOUTH) {
            while (i < 71) {
                pos2 = new BlockPos(x, y, z - i);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR) {
                    i++;
                } else {
                    break;
                }
            }
        } else if (enumfacing == EnumFacing.EAST) {
            while (i < 71) {
                pos2 = new BlockPos(x - i, y, z);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR) {
                    i++;
                } else {
                    break;
                }
            }
        } else if (enumfacing == EnumFacing.WEST) {
            while (i < 71) {
                pos2 = new BlockPos(x + i, y, z);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR) {
                    i++;
                } else {
                    break;
                }
            }
        }
        
        this.powerfactor = i;
        this.maxenergy = 30000 * i;
        if (this.energy > (this.maxenergy - 100)) {
            if (!worldIn.isRemote) {
                EntityLargePlasmaBall entityplasmaball = null;
                
                if (enumfacing == EnumFacing.NORTH) {
                    entityplasmaball = new EntityLargePlasmaBall(worldIn, d0, d1, d2 - 1, powerfactor);
                } else if (enumfacing == EnumFacing.SOUTH) {
                    entityplasmaball = new EntityLargePlasmaBall(worldIn, d0, d1, d2 + 1, powerfactor);
                } else if (enumfacing == EnumFacing.EAST) {
                    entityplasmaball = new EntityLargePlasmaBall(worldIn, d0 + 1, d1, d2, powerfactor);
                } else if (enumfacing == EnumFacing.WEST) {
                    entityplasmaball = new EntityLargePlasmaBall(worldIn, d0 - 1, d1, d2, powerfactor);
                }
        
                entityplasmaball.shoot((double) enumfacing.getFrontOffsetX(), (double) enumfacing.getFrontOffsetY(), (double) enumfacing.getFrontOffsetZ(), 5, 0.001F);
        
                worldIn.spawnEntity(entityplasmaball);
            }
        
            worldIn.playSound(playerIn, pos, SoundsHandler.TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON, SoundCategory.BLOCKS, 9.9F, 1.0F);
            this.energy = this.energy - (this.maxenergy - 100);
        } else {
            if (world.isRemote) {
                String str2 = Double.toString(energy);
                String str3 = Double.toString(maxenergy);
                playerIn.sendMessage(new TextComponentString("not enough energy " + str2 + ", needs " + str3));
            }
        }
    }

    public void fireredstone(World worldIn, BlockPos pos, IBlockState state) {
        fireplasma(worldIn, pos, state);
        
        /* [ProgramCrafter/23-09-2021] this code looks like the duplicate of fireplasma

        //direction
        EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
        double d0 = pos.getX() + 0.5D + (float) enumfacing.getFrontOffsetX();
        double d1 = pos.getY() + 0.164D + (float) enumfacing.getFrontOffsetY();
        double d2 = pos.getZ() + 0.5D + (float) enumfacing.getFrontOffsetZ();

        int x = this.pos.getX();
        int y = this.pos.getY();
        int z = this.pos.getZ();



        BlockPos pos2;


        if (enumfacing == EnumFacing.NORTH) {
            int i = 1;
            while (i < 71) {
                pos2 = new BlockPos(x, y, z + i);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR) {
                    i++;
                } else {
                    this.powerfactor = i;
                    this.maxenergy = 30000 * i;
                    if (this.energy > (this.maxenergy - 100)) {

                        if (!worldIn.isRemote) {
                            EntityLargePlasmaBall entityplasmaball = new EntityLargePlasmaBall(worldIn, d0, d1, d2 - 1, powerfactor);

                            entityplasmaball.shoot((double) enumfacing.getFrontOffsetX(), (double) enumfacing.getFrontOffsetY(), (double) enumfacing.getFrontOffsetZ(), 5, 0.001F);

                            worldIn.spawnEntity(entityplasmaball);
                        }

                        worldIn.playSound(null, pos, SoundsHandler.TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON, SoundCategory.BLOCKS, 9.9F, 1.0F);
                        this.energy = 0;
                    } else {
                        if (world.isRemote) {
                            String str2 = Double.toString(energy);
                            String str3 = Double.toString(maxenergy);
                        }
                    }
                    break;
                }
            }
        }

        if (enumfacing == EnumFacing.SOUTH) {

            int i = 1;
            while (i < 71) {
                pos2 = new BlockPos(x, y, z - i);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR) {
                    i++;
                } else {
                    this.powerfactor = i;
                    this.maxenergy = 30000 * i;
                    if (this.energy > (this.maxenergy - 100)) {

                        if (!worldIn.isRemote) {
                            EntityLargePlasmaBall entityplasmaball = new EntityLargePlasmaBall(worldIn, d0, d1, d2 + 1, powerfactor);

                            entityplasmaball.shoot((double) enumfacing.getFrontOffsetX(), (double) enumfacing.getFrontOffsetY(), (double) enumfacing.getFrontOffsetZ(), 5, 0.001F);

                            worldIn.spawnEntity(entityplasmaball);
                        }

                        worldIn.playSound(null, pos, SoundsHandler.TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON, SoundCategory.BLOCKS, 9.9F, 1.0F);
                        this.energy = 0;
                    } else {
                        if (world.isRemote) {
                            String str2 = Double.toString(energy);
                            String str3 = Double.toString(maxenergy);
                        }
                    }
                    break;
                }
            }
        }

        if (enumfacing == EnumFacing.EAST) {

            int i = 1;
            while (i < 71) {
                pos2 = new BlockPos(x - i, y, z);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR) {
                    i++;
                } else {
                    this.powerfactor = i;
                    this.maxenergy = 30000 * i;
                    if (this.energy > (this.maxenergy - 100)) {

                        if (!worldIn.isRemote) {
                            EntityLargePlasmaBall entityplasmaball = new EntityLargePlasmaBall(worldIn, d0 + 1, d1, d2, powerfactor);

                            entityplasmaball.shoot((double) enumfacing.getFrontOffsetX(), (double) enumfacing.getFrontOffsetY(), (double) enumfacing.getFrontOffsetZ(), 5, 0.001F);

                            worldIn.spawnEntity(entityplasmaball);
                        }

                        worldIn.playSound(null, pos, SoundsHandler.TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON, SoundCategory.BLOCKS, 9.9F, 1.0F);
                        this.energy = 0;
                    } else {
                        if (world.isRemote) {
                            String str2 = Double.toString(energy);
                            String str3 = Double.toString(maxenergy);
                        }
                    }
                    break;
                }
            }
        }

        if (enumfacing == EnumFacing.WEST) {

            int i = 1;
            while (i < 71) {
                pos2 = new BlockPos(x + i, y, z);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR) {
                    i++;
                } else {
                    this.powerfactor = i;
                    this.maxenergy = 30000 * i;
                    if (this.energy > (this.maxenergy - 100)) {

                        if (!worldIn.isRemote) {
                            EntityLargePlasmaBall entityplasmaball = new EntityLargePlasmaBall(worldIn, d0 - 1, d1, d2, powerfactor);

                            entityplasmaball.shoot((double) enumfacing.getFrontOffsetX(), (double) enumfacing.getFrontOffsetY(), (double) enumfacing.getFrontOffsetZ(), 5, 0.001F);

                            worldIn.spawnEntity(entityplasmaball);
                        }

                        worldIn.playSound(null, pos, SoundsHandler.TILEENTITY_TILEENTITYPLASMACANNON_FIRECANNON, SoundCategory.BLOCKS, 9.9F, 1.0F);
                        this.energy = 0;
                    } else {
                        if (world.isRemote) {
                            String str2 = Double.toString(energy);
                            String str3 = Double.toString(maxenergy);
                        }
                    }
                    break;
                }
            }
        }
        */
    }


    public void update()
    {
        ticks++;

        if (ticks > 23){
            ticks = 0;
        }

        if (ticks == 0) {
            if (!world.isRemote) {
                EnergyTileLoadEvent loadEvent = new EnergyTileLoadEvent(this);
                MinecraftForge.EVENT_BUS.post(loadEvent);
            }
            //world.markBlockRangeForRenderUpdate(pos, pos);
            world.notifyBlockUpdate(pos, getState(), getState(), 3);
            //world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
            //markDirty();
        }
    }



    public void invalidate()
    {
        EnergyTileUnloadEvent unloadEvent = new EnergyTileUnloadEvent(this);
        MinecraftForge.EVENT_BUS.post(unloadEvent);
    }



    public void displayfirepower(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn)
    {

        EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

        int x = this.pos.getX();
        int y = this.pos.getY();
        int z = this.pos.getZ();

        BlockPos pos2;

        if (enumfacing == EnumFacing.NORTH)
        {
            int i = 1;
            while(i < 71)
            {
                pos2 = new BlockPos(x,y,z+i);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR)
                {
                    i++;

                }
                else
                {
                    this.powerfactor = i;
                    this.maxenergy = 30000*i;
                    break;
                }
            }
        }

        if (enumfacing == EnumFacing.SOUTH)
        {

            int i = 1;
            while(i < 71)
            {
                pos2 = new BlockPos(x,y,z-i);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR)
                {
                    i++;


                }
                else
                {
                    this.powerfactor = i;
                    this.maxenergy = 30000*i;
                    break;
                }
            }
        }

        if (enumfacing == EnumFacing.EAST)
        {

            int i = 1;
            while(i < 71)
            {
                pos2 = new BlockPos(x-i,y,z);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR)
                {
                    i++;

                }
                else
                {
                    this.powerfactor = i;
                    this.maxenergy = 30000*i;
                    break;
                }
            }
        }

        if (enumfacing == EnumFacing.WEST)
        {

            int i = 1;
            while(i < 71)
            {
                pos2 = new BlockPos(x+i,y,z);
                if (world.getBlockState(pos2).getBlock() == BlockInit.BLOCK_PLASMAGENERATOR)
                {
                    i++;

                }
                else
                {
                    this.powerfactor = i;
                    this.maxenergy = 30000*i;
                    break;
                }
            }
        }

        if(world.isRemote) {
            String str = Integer.toString(powerfactor);
            String str2 = Double.toString(maxenergy);
            String str3 = Double.toString(energy);
            playerIn.sendMessage(new TextComponentString("power is "+str+", "+ "energy is " + str3 +"," + " max energy is "+ str2));
        }
    }

    @Override
    public double getDemandedEnergy() {
        return this.maxenergy - this.energy;
    }

    @Override
    public int getSinkTier() {
        return Integer.MAX_VALUE;
    }

    @Override
    public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {

        if (this.ticks == 20) {
            if (this.energy >= this.maxenergy) return amount;

            double openEnergy = this.maxenergy - this.energy;
            if (openEnergy >= 20*amount) {
                this.energy += 20*amount;
                return 0.0D;
            } else if (20*amount > openEnergy) {
                this.energy += openEnergy;
                return 20*amount - openEnergy;
            }
        }

        return 0;
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
        return true;
    }
}
