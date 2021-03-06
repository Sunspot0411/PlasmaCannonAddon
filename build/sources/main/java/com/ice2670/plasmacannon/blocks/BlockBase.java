package com.ice2670.plasmacannon.blocks;

import com.ice2670.plasmacannon.Main;
import com.ice2670.plasmacannon.init.BlockInit;
import com.ice2670.plasmacannon.init.ItemInit;
import com.ice2670.plasmacannon.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import org.valkyrienskies.mod.common.ValkyrienSkiesMod;

public class BlockBase extends Block implements IHasModel
{
    public BlockBase(String name, Material material)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ValkyrienSkiesMod.VS_CREATIVE_TAB);
        setHardness(10.0F);
        setResistance(20.0F);
        setHarvestLevel("pickaxe", 2);

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
