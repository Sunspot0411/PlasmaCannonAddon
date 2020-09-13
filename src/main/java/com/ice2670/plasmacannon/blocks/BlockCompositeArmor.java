package com.ice2670.plasmacannon.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Created by Eric C on 3/2/2019.
 */
public class BlockCompositeArmor extends BlockBase
{
    public BlockCompositeArmor(String name)
    {
        super(name, Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(1002.0F);
        setResistance(580.0F);
        setHarvestLevel("pickaxe", 3);

    }
}
