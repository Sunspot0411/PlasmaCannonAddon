package com.ice2670.plasmacannon.init;

import com.ice2670.plasmacannon.items.IridiumPlate;
import com.ice2670.plasmacannon.items.ItemBase;
import com.ice2670.plasmacannon.items.ItemAdvCompositeArmor;
import com.ice2670.plasmacannon.items.ItemCompositeArmor;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemInit
{
    public static final List<Item> ITEMS = new ArrayList<Item>();

    public static final Item INGOT_KEY = new ItemBase("ingot_key");

    public static final Item INGOT_ENERGYREADER = new ItemBase("ingot_energyreader");

    public static final Item ITEM_ADVCOMPOSITEARMOR = new ItemAdvCompositeArmor("item_advcompositearmor");

    public static final Item ITEM_COMPOSITEARMOR = new ItemCompositeArmor("item_compositearmor");

    public static final Item COMPOSITEMATERIAL = new ItemBase("compositematerial");

    public static final Item DENSE_COMPOSITEMATERIAL = new ItemBase("dense_compositematerial");

    public static final Item COMPOSITEARMORMATERIAL = new ItemBase("composite_armor_material");

    public static final Item ADVCOMPOSITEARMORMATERIAL = new ItemBase("advcomposite_armor_material");

    public static final Item IRIDIUM_PLATE = new IridiumPlate("iridium_plate");

    public static final Item ARMOR_STELL = new ItemBase("armor_steel");

    public static final Item CARBON_IRON_MIX = new ItemBase("carbon_iron_mixture");

    public static final Item SMALL_OBSIDIAN = new ItemBase("small_obsidian");

    public static final Item TORPEDO = new ItemBase("torpedo");

    public static final Item FASTTORPEDO = new ItemBase("ftorpedo");

    public static final Item PLASMACONTAINER = new ItemBase("plasma_container");

    public static final Item INTEGRATED_CIRCUIT = new ItemBase("integrated_circuit");

    public static final Item CARBON_DUST = new ItemBase("carbon_dust");

    public static final Item AUTO_TORPEDO = new ItemBase("autotorpedo");



}

