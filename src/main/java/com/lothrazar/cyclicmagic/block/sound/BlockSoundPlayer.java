package com.lothrazar.cyclicmagic.block.sound;

import com.lothrazar.cyclicmagic.IHasRecipe;
import com.lothrazar.cyclicmagic.core.block.BlockBaseHasTile;
import com.lothrazar.cyclicmagic.core.registry.RecipeRegistry;
import com.lothrazar.cyclicmagic.gui.ForgeGuiHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSoundPlayer extends BlockBaseHasTile implements IHasRecipe {

  public BlockSoundPlayer() {
    super(Material.ROCK);

    super.setGuiId(ForgeGuiHandler.GUI_INDEX_SOUNDPL);

  }

  @Override
  public TileEntity createTileEntity(World worldIn, IBlockState state) {
    return new TileEntitySoundPlayer();
  }

  @Override
  public IRecipe addRecipe() {
    return RecipeRegistry.addShapedRecipe(new ItemStack(this), "rsr", "gbg", "ooo",
        'o', "cobblestone",
        'g', "nuggetIron",
        's', Blocks.ANVIL,
        'r', "blockRedstone",
        'b', Items.FLINT);
  }

  //  @Override
  //  public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
  //    boolean powered = worldIn.isBlockPowered(pos);
  //    ModCyclic.logger.log("nbr changed and powerd=" + powered);
  //    if (powered) {
  //      TileEntitySoundPlayer te = (TileEntitySoundPlayer) worldIn.getTileEntity(pos);
  //      te.triggerSoundIfSet();
  //    }
  //  }
}
