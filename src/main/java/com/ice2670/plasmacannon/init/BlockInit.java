package com.ice2670.plasmacannon.init;

import com.ice2670.plasmacannon.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block BLOCK_PLASMAGENERATOR = new BlockBase("block_plasmagenerator", Material.IRON);

    public static final Block BLOCK_PLASMA_CANNON = new BlockPlasmaCannon("plasma_cannon");

    public static final Block BLOCK_ADVCOMPOSITEARMOR = new BlockAdvCompositeArmor("block_advcompositearmor");

    public static final Block BLOCK_TORPEDOLAUNCHER = new BlockTorpedoLauncher("block_torpedo_launcher");

    public static final Block BLOCK_COMPOSITEARMOR = new BlockCompositeArmor("block_compositearmor");

    public static final Block BLOCK_SHIPARMOR = new BlockShipArmor("block_shiparmor");

    public static final Block BPC = new BlockPC("bpc");

}
