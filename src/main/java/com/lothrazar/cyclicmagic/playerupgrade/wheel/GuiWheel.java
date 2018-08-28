package com.lothrazar.cyclicmagic.playerupgrade.wheel;

import com.lothrazar.cyclicmagic.core.gui.GuiButtonItemstack;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

//based on my ancient 2015 spellbook concept 
/// https://github.com/PrinceOfAmber/Cyclic/blob/838b9b669a2d1644077d35a91d997a4d5dca0448/src/main/java/com/lothrazar/cyclicmagic/gui/GuiSpellbook.java
public class GuiWheel extends GuiScreen {

  private static final int MIN_RADIUS = 26;
  private static final int BTNCOUNT = 16;
  private static final int YOFFSET = 15;
  private final EntityPlayer entityPlayer;

  // https://github.com/LothrazarMinecraftMods/EnderBook/blob/66363b544fe103d6abf9bcf73f7a4051745ee982/src/main/java/com/lothrazar/enderbook/GuiEnderBook.java
  private int xCenter;
  private int yCenter;
  private int radius;
  private double arc;
  int textureWidth = 200;
  int textureHeight = 180;

  public GuiWheel(EntityPlayer p) {
    
    super();
    this.entityPlayer = p; 
  }

  @Override
  public void initGui() {
    super.initGui();
    xCenter = this.width / 2;
    yCenter = this.height / 2 - YOFFSET;
    radius = xCenter / 3 + MIN_RADIUS;
    arc = (2 * Math.PI) / BTNCOUNT;// SpellRegistry.getSpellbook().size();
    //    this.buttonList.add(new GuiButton(999, xCenter - 15, yCenter - 10));
    double ang = 0;
    double cx, cy;
    int id = 0;
    ang = 0;
    GuiButtonItemstack btn;
    for (int i = 0; i < BTNCOUNT; i++) {
      cx = xCenter + radius * Math.cos(ang) - 2;
      cy = yCenter + radius * Math.sin(ang) - 2;
      btn = new GuiButtonItemstack(id++, (int) cx, (int) cy, 20, 20);
      btn.setTooltip("test" + i);
      btn.setStackRender(new ItemStack(Blocks.STONE));
      this.buttonList.add(btn);
      ang += arc;
    }
  }

  @Override
  public void drawBackground(int tint) {
    super.drawBackground(tint);
    //    ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
    //    int screenWidth = res.getScaledWidth();
    //    int screenHeight = res.getScaledHeight();
    //    int guiLeft = screenWidth / 2 - textureWidth / 2;
    //    int guiTop = screenHeight / 2 - textureHeight / 2;
    //    UtilTextureRender.drawTextureSimple(background, guiLeft, guiTop, 200, 200);
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    super.drawScreen(mouseX, mouseY, partialTicks);
    double ang = 0;
    double cx, cy;
    //TODO: move this to btn class as well ??
    //    int spellSize = 16;
    //    UtilTextureRender.drawTextureSquare(ptr, mouseX - 8, mouseY - 8, spellSize);
    //    for (ISpell s : SpellRegistry.getSpellbook()) {
    //      cx = xCenter + radius * Math.cos(ang);
    //      cy = yCenter + radius * Math.sin(ang);
    //      //TODO: move this to btn class as well? but it would need access to the player props
    //      ResourceLocation header;
    //      if (props.isSpellUnlocked(s.getID())) {// TODO: do we want different icons for these
    //        header = s.getIconDisplayHeaderEnabled();
    //      }
    //      else {
    //        header = s.getIconDisplayHeaderDisabled();
    //      }
    //      UtilTextureRender.drawTextureSimple(header, (int) cx + 1, (int) cy - 6, spellSize - 2, spellSize - 4);
    //      ang += arc;
    //    }
    //    GuiButtonSpell btn;
    //    for (int i = 0; i < buttonList.size(); i++) {
    //      if (buttonList.get(i).isMouseOver() && buttonList.get(i) instanceof GuiButtonSpell) {
    //        btn = (GuiButtonSpell) buttonList.get(i);
    //        drawHoveringText(btn.getTooltipForPlayer(props), mouseX, mouseY, fontRendererObj);
    //        break;//cant hover on 2 at once
    //      }
    //    }
  }

  @Override
  public boolean doesGuiPauseGame() {
    return false;
  }
}
