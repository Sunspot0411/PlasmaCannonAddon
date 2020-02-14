package com.ice2670.plasmacannon.items;

import com.ice2670.plasmacannon.Main;
import com.ice2670.plasmacannon.init.ItemInit;
import com.ice2670.plasmacannon.util.IHasModel;
import net.minecraft.item.Item;
import org.valkyrienskies.mod.common.ValkyrienSkiesMod;

public class ItemBase extends Item implements IHasModel
{
    public ItemBase(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ValkyrienSkiesMod.VS_CREATIVE_TAB);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
