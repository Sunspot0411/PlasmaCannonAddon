package com.ice2670.plasmacannon.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

public class BlockAdvCompositeArmor extends BlockBase {
    public BlockAdvCompositeArmor(String name) {
        super(name, Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(1902.0F);
        setResistance(1240.0F);
        setHarvestLevel("pickaxe", 4);
    }
}
