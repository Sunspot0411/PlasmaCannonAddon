package com.ice2670.plasmacannon.blocks;

import com.ice2670.plasmacannon.init.BlockInit;
import com.ice2670.plasmacannon.init.ItemInit;
import com.ice2670.plasmacannon.tileentities.TileEntityTorpedoLauncher;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.valkyrienskies.addon.control.ValkyrienSkiesControl;
import org.valkyrienskies.mod.common.ValkyrienSkiesMod;

import java.util.Random;

public class BlockTorpedoLauncher extends BlockBase
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockTorpedoLauncher(String name)
    {
        super(name, Material.IRON);
        setCreativeTab(ValkyrienSkiesMod.VS_CREATIVE_TAB);
        setSoundType(SoundType.METAL);
        setHardness(1000.0F);
        setResistance(500.0F);
        setHarvestLevel("pickaxe", 3);
        setLightOpacity(1);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer).getOpposite());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = state.getValue(FACING).getIndex();
        return i;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(BlockInit.BLOCK_TORPEDOLAUNCHER);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityTorpedoLauncher();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
        ItemStack heldItem = playerIn.getHeldItem(hand);
        TileEntityTorpedoLauncher torpedolauncher = (TileEntityTorpedoLauncher) worldIn.getTileEntity(pos);



        Item item = heldItem.getItem();
        if(item == ItemInit.INGOT_KEY){
            torpedolauncher.launchtorpedo(worldIn,pos,state,playerIn);
        }
        else if (item == ItemInit.AUTO_TORPEDO){
            torpedolauncher.shoottorpedo(worldIn,pos,state,playerIn,hand);
        }
        else if (item == ValkyrienSkiesControl.INSTANCE.vsWrench){
            worldIn.setBlockState(pos, BlockInit.BLOCK_TORPEDOLAUNCHER.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, playerIn)));
        }



        return true;
    }
}
