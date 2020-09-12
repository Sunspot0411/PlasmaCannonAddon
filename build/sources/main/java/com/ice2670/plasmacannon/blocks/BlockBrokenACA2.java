package com.ice2670.plasmacannon.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by Eric C on 10/5/2019.
 */
public class BlockBrokenACA2 extends BlockBase
{
    public BlockBrokenACA2(String name)
    {
        super(name, Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(451.0F);
        setResistance(320.0F);
        setHarvestLevel("pickaxe", 3);
    }
}
