package com.ice2670.plasmacannon.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.valkyrienskies.mod.common.ValkyrienSkiesMod;


import java.util.Random;

/**
 * Created by Eric C on 10/5/2019.
 */
public class BlockBrokenTG extends BlockBase
{
    public BlockBrokenTG(String name)
    {
        super(name, Material.GLASS);
        setCreativeTab(ValkyrienSkiesMod.VS_CREATIVE_TAB);
        setSoundType(SoundType.GLASS);
        setHardness(100.0F);
        setResistance(60.0F);
        setHarvestLevel("pickaxe", 3);
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }


    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
