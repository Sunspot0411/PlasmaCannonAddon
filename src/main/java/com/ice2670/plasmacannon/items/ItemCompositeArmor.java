package com.ice2670.plasmacannon.items;

import com.ice2670.plasmacannon.init.BlockInit;
import com.ice2670.plasmacannon.init.ItemInit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCompositeArmor extends ItemBase {
    public ItemCompositeArmor(String name) {
        super(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (worldIn.getBlockState(pos).getBlock() == Blocks.COBBLESTONE || worldIn.getBlockState(pos).getBlock() == Blocks.IRON_BLOCK || worldIn.getBlockState(pos).getBlock() == Blocks.PLANKS)
        {
            worldIn.setBlockState(pos, BlockInit.BLOCK_COMPOSITEARMOR.getDefaultState());
            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }

        if (worldIn.getBlockState(pos).getBlock() == BlockInit.BLOCK_SHIPARMOR)
        {
            worldIn.setBlockState(pos, BlockInit.BLOCK_COMPOSITEARMOR.getDefaultState());
            itemstack.shrink(1);
            player.inventory.addItemStackToInventory(new ItemStack(ItemInit.ARMOR_STELL,4));
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
}
