package com.ice2670.plasmacannon.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by Eric C on 10/5/2019.
 */
public class BlockBrokenACA1 extends BlockBase
{
    public BlockBrokenACA1(String name)
    {
        super(name, Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(651.0F);
        setResistance(270.0F);
        setHarvestLevel("pickaxe", 3);
    }
}
