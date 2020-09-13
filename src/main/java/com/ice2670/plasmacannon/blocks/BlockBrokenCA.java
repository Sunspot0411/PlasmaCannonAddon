package com.ice2670.plasmacannon.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by Eric C on 10/5/2019.
 */
public class BlockBrokenCA extends BlockBase
{
    public BlockBrokenCA(String name)
    {
        super(name, Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(399.0F);
        setResistance(310.0F);
        setHarvestLevel("pickaxe", 3);
    }
}
