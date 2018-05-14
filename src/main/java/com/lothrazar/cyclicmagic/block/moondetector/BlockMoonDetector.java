package com.lothrazar.cyclicmagic.block.moondetector;

import com.lothrazar.cyclicmagic.IHasRecipe;
import com.lothrazar.cyclicmagic.core.block.BlockBaseHasTile;
import com.lothrazar.cyclicmagic.core.registry.RecipeRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMoonDetector extends BlockBaseHasTile implements IHasRecipe {

  //BlockDaylightDetector
  protected static final AxisAlignedBB DAYLIGHT_DETECTOR_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D);
  public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
  public BlockMoonDetector() {
    super(Material.ROCK);
  }

  @Override
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return DAYLIGHT_DETECTOR_AABB;
  }

  @Override
  public TileEntity createTileEntity(World worldIn, IBlockState state) {
    return new TileEntityMoon();
  }

  @Override
  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, POWER);
  }

  @Override
  public IBlockState getStateFromMeta(int meta) {
    return this.getDefaultState().withProperty(POWER, Integer.valueOf(meta));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(POWER).intValue();
  }
  @Override
  public boolean canProvidePower(IBlockState state) {
    return true;
  }

  private int getPower(IBlockState blockState) {
    //https://minecraft.gamepedia.com/Moon
    return blockState.getValue(POWER).intValue();
    //    if (world.getTileEntity(pos) instanceof TileEntityMoon) 
    //      TileEntityMoon target = ((TileEntityMoon) world.getTileEntity(pos));
    //      return target.getPower();
    //    }
    //    return 0;
  }

  @Override
  public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
    return getPower(blockState);
  }

  @Override
  public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
    return getPower(blockState);
  }

  @Override
  public IRecipe addRecipe() {
    return RecipeRegistry.addShapedOreRecipe(new ItemStack(this),
        "iii",
        "sos",
        "iii",
        'i', "nuggetGold",
        'o', Blocks.OBSERVER,
        's', Blocks.STONE_BUTTON);
  }
}