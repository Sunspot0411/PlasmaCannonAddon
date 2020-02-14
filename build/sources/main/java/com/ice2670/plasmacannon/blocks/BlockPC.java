package com.ice2670.plasmacannon.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockPC extends BlockBase {
    public BlockPC(String name) {
        super(name, Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(10.0F);
        setResistance(1.0F);
        setHarvestLevel("pickaxe", 1);
    }
}
