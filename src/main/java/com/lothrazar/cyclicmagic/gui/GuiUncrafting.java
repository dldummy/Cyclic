package com.lothrazar.cyclicmagic.gui;

import com.lothrazar.cyclicmagic.block.TileEntityUncrafting;
import com.lothrazar.cyclicmagic.util.Const;
import com.lothrazar.cyclicmagic.util.UtilUncraft;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiUncrafting extends GuiContainer {
	private TileEntityUncrafting tile;

	public GuiUncrafting(InventoryPlayer inventoryPlayer, TileEntityUncrafting tileEntity) {
		super(new ContainerUncrafting(inventoryPlayer, tileEntity));
		tile = tileEntity;
	}

	public GuiUncrafting(Container c) {
		super(c);
	}

	@SideOnly(Side.CLIENT)
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}

	private static final String folder = "textures/gui/";
	private static final ResourceLocation table = new ResourceLocation(Const.MODID, folder + "table.png");
	private static final ResourceLocation slot = new ResourceLocation(Const.MODID, folder + "inventory_slot.png");
	private static final ResourceLocation progress = new ResourceLocation(Const.MODID, folder + "progress.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(table);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		int texture_width = 176;
		int texture_height = 166;
		int u = 0, v = 0;
		Gui.drawModalRectWithCustomSizedTexture(i, j, u, v, this.xSize, this.ySize, texture_width, texture_height);

		int sq = 18;

		this.mc.getTextureManager().bindTexture(slot);
		Gui.drawModalRectWithCustomSizedTexture(this.guiLeft + ContainerUncrafting.SLOTX - 1, this.guiTop + ContainerUncrafting.SLOTY - 1, u, v, sq, sq, sq, sq);
		Gui.drawModalRectWithCustomSizedTexture(this.guiLeft + ContainerUncrafting.SLOTX - 1, this.guiTop + ContainerUncrafting.SLOTY - 1 + 18, u, v, sq, sq, sq, sq);
		Gui.drawModalRectWithCustomSizedTexture(this.guiLeft + ContainerUncrafting.SLOTX - 1, this.guiTop + ContainerUncrafting.SLOTY - 1 + 2 * 18, u, v, sq, sq, sq, sq);

		if (tile.getTimer() > 0 && tile.getStackInSlot(ContainerUncrafting.SLOT) != null) {
			this.mc.getTextureManager().bindTexture(progress);

			float percent = ((float) tile.getTimer()) / ((float) UtilUncraft.TIMER_FULL);
			// maximum progress bar is 156, since the whole texture is 176 minus
			// 10 padding on each side
			int belowSlots = this.guiTop + ContainerUncrafting.SLOTY - 1 + 3 * 18;
			// Args: x, y, u, v, width, height, textureWidth, textureHeight
			Gui.drawModalRectWithCustomSizedTexture(this.guiLeft + 10, belowSlots + 5, u, v, (int) (156 * percent), 7, 156, 7);
		}
	}
}