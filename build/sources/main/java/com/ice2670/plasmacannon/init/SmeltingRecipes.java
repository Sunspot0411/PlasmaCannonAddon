package com.ice2670.plasmacannon.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Eric C on 3/2/2019.
 */
public class SmeltingRecipes
{
    public static void init(){
        GameRegistry.addSmelting(ItemInit.CARBON_IRON_MIX, new ItemStack(ItemInit.ARMOR_STELL,1),5f);
    }
}
