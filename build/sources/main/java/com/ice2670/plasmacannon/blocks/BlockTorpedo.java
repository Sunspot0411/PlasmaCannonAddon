package com.ice2670.plasmacannon.blocks;

import com.ice2670.plasmacannon.entity.EntityTorpedoPrimed;
import com.ice2670.plasmacannon.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.valkyrienskies.addon.control.ValkyrienSkiesControl;

import javax.annotation.Nullable;
import java.util.List;

public class BlockTorpedo extends BlockBase
{

    int power;
    public BlockTorpedo(String name, int power) {
        super(name, Material.TNT);
        setHardness(0.0F);
        setResistance(4.0F);
        this.power = power;
    }


    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);

        if (worldIn.isBlockPowered(pos))
        {
            worldIn.setBlockToAir(pos);
            this.explode(worldIn, pos, state, (EntityLivingBase)null);
        }
    }


    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (worldIn.isBlockPowered(pos))
        {
            worldIn.setBlockToAir(pos);
            this.explode(worldIn, pos, state, (EntityLivingBase)null);
        }
    }


    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        if (!worldIn.isRemote)
        {
            EntityTorpedoPrimed entitytorpedoprimed = new EntityTorpedoPrimed(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), explosionIn.getExplosivePlacedBy(), this.power);
            entitytorpedoprimed.setFuse((short)(worldIn.rand.nextInt(entitytorpedoprimed.getFuse() / 4) + entitytorpedoprimed.getFuse() / 8));
            worldIn.spawnEntity(entitytorpedoprimed);
        }
    }

    public void explode(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase igniter)
    {
        if (!worldIn.isRemote)
        {
            EntityTorpedoPrimed entitytorpedoprimed = new EntityTorpedoPrimed(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), igniter, this.power);
            worldIn.spawnEntity(entitytorpedoprimed);
            worldIn.playSound((EntityPlayer)null, entitytorpedoprimed.posX, entitytorpedoprimed.posY, entitytorpedoprimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }


    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (!itemstack.isEmpty() && itemstack.getItem() == ValkyrienSkiesControl.INSTANCE.vsWrench)
        {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            this.explode(worldIn, pos, state, (EntityLivingBase)null);

            return true;
        }
        else
        {
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        }
    }

    public boolean canDropFromExplosion(Explosion explosionIn)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player,
                               List<String> itemInformation, ITooltipFlag advanced) {
        itemInformation.add(TextFormatting.ITALIC + "" + TextFormatting.BLUE + I18n
                .format("tooltip.plasmacannons.torpedo_1"));
        itemInformation.add(TextFormatting.RED + "" + TextFormatting.ITALIC + I18n
                .format("tooltip.plasmacannons.torpedo_2"));
    }
}
