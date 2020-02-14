package com.ice2670.plasmacannon;

import com.ice2670.plasmacannon.init.ItemInit;
import com.ice2670.plasmacannon.init.SmeltingRecipes;
import com.ice2670.plasmacannon.proxy.CommonProxy;
import com.ice2670.plasmacannon.util.Reference;
import com.ice2670.plasmacannon.util.handlers.RegistryHandler;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Collections;


@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
    @Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
    public static CommonProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        RegistryHandler.preInitRegistries();
        proxy.registerEntityRenders();}
    @EventHandler
    public static void init(FMLInitializationEvent event) {
        IRecipeInput input = Recipes.inputFactory.forStack(new ItemStack(ItemInit.COMPOSITEMATERIAL));
        ItemStack output = new ItemStack(ItemInit.DENSE_COMPOSITEMATERIAL);
        Recipes.compressor.addRecipe(input, Collections.singletonList(output), null, false);

        IRecipeInput input2 = Recipes.inputFactory.forStack(new ItemStack(ItemInit.COMPOSITEARMORMATERIAL));
        ItemStack output2 = new ItemStack(ItemInit.ITEM_COMPOSITEARMOR);
        Recipes.metalformerRolling.addRecipe(input2, Collections.singletonList(output2), null, false);

        IRecipeInput input3 = Recipes.inputFactory.forStack(new ItemStack(Blocks.OBSIDIAN));
        ItemStack output3 = new ItemStack(ItemInit.SMALL_OBSIDIAN, 9);
        Recipes.metalformerCutting.addRecipe(input3, Collections.singletonList(output3), null, false);

        IRecipeInput input4 = Recipes.inputFactory.forStack(IC2Items.getItem("te","mfe"));
        IRecipeInput input4_1 = Recipes.inputFactory.forStack(IC2Items.getItem("dust","lapis"),9);
        ItemStack output4 = new ItemStack(ItemInit.PLASMACONTAINER,4);
        Recipes.cannerBottle.addRecipe(input4, input4_1, output4);

        IRecipeInput input5 = Recipes.inputFactory.forStack(IC2Items.getItem("crafting","advanced_circuit"),6);
        ItemStack output5 = new ItemStack(ItemInit.INTEGRATED_CIRCUIT);
        Recipes.metalformerRolling.addRecipe(input5, Collections.singletonList(output5), null, false);

        IRecipeInput input6 = Recipes.inputFactory.forStack(IC2Items.getItem("dust","coal"));
        ItemStack output6 = new ItemStack(ItemInit.CARBON_DUST);
        Recipes.extractor.addRecipe(input6, Collections.singletonList(output6), null, false);

        IRecipeInput input7 = Recipes.inputFactory.forStack(new ItemStack(ItemInit.ADVCOMPOSITEARMORMATERIAL));
        ItemStack output7 = new ItemStack(ItemInit.ITEM_ADVCOMPOSITEARMOR);
        Recipes.metalformerRolling.addRecipe(input7, Collections.singletonList(output7), null, false);

        IRecipeInput input8 = Recipes.inputFactory.forStack(IC2Items.getItem("misc_resource","iridium_ore"));
        ItemStack output8 = new ItemStack(ItemInit.IRIDIUM_PLATE, 8);
        Recipes.metalformerRolling.addRecipe(input8, Collections.singletonList(output8), null, false);

        SmeltingRecipes.init();


        RegistryHandler.initRegistries();}
    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {RegistryHandler.postInitRegistries();}

}
