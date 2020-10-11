package com.ice2670.plasmacannon.items;

import com.ice2670.plasmacannon.init.BlockInit;
import com.ice2670.plasmacannon.init.ItemInit;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemAdvCompositeArmor extends ItemBase {
    public ItemAdvCompositeArmor(String name) {
        super(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (worldIn.getBlockState(pos).getBlock() == Blocks.COBBLESTONE || worldIn.getBlockState(pos).getBlock() == Blocks.IRON_BLOCK || worldIn.getBlockState(pos).getBlock() == Blocks.PLANKS)
        {
            worldIn.setBlockState(pos, BlockInit.BLOCK_ADVCOMPOSITEARMOR.getDefaultState());
            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }

        if (worldIn.getBlockState(pos).getBlock() == BlockInit.BLOCK_SHIPARMOR)
        {
            worldIn.setBlockState(pos, BlockInit.BLOCK_ADVCOMPOSITEARMOR.getDefaultState());
            itemstack.shrink(1);
            player.inventory.addItemStackToInventory(new ItemStack(ItemInit.ARMOR_STELL,2));
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player,
                               List<String> itemInformation, ITooltipFlag advanced) {
        itemInformation.add(TextFormatting.ITALIC + "" + TextFormatting.BLUE + I18n
                .format("tooltip.plasmacannons.item_advcompositearmor_1"));
        itemInformation.add(TextFormatting.RED + "" + TextFormatting.ITALIC + I18n
                .format("tooltip.plasmacannons.item_advcompositearmor_2"));
    }
}
