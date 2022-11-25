//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.custom;

import net.minecraftforge.fml.relauncher.*;
import org.apache.logging.log4j.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.renderer.*;
import me.snow.luigihack.impl.modules.misc.*;
import me.snow.luigihack.*;
import com.google.common.collect.*;
import net.minecraft.util.text.*;
import net.minecraft.client.gui.*;
import java.util.*;
import javax.annotation.*;

@SideOnly(Side.CLIENT)
public class GuiChat extends GuiNewChat implements Util
{
    private static final /* synthetic */ Logger LOGGER;
    public static /* synthetic */ long prevMillis;
    private final /* synthetic */ List<String> sentMessages;
    public static /* synthetic */ int messageAdd;
    private final /* synthetic */ List<ChatLine> drawnChatLines;
    private final /* synthetic */ Minecraft mc;
    public static /* synthetic */ int newLines;
    private final /* synthetic */ Timer messageTimer;
    private /* synthetic */ boolean isScrolled;
    private final /* synthetic */ List<ChatLine> chatLines;
    private /* synthetic */ int scrollPos;
    public static /* synthetic */ float percentComplete;
    public /* synthetic */ boolean configuring;
    
    public int getChatWidth() {
        return calculateChatboxWidth(this.mc.gameSettings.chatWidth);
    }
    
    public void scroll(final int n) {
        this.scrollPos += n;
        final int size = this.drawnChatLines.size();
        if (this.scrollPos > size - this.getLineCount()) {
            this.scrollPos = size - this.getLineCount();
        }
        if (this.scrollPos <= 0) {
            this.scrollPos = 0;
            this.isScrolled = false;
        }
    }
    
    public float getChatScale() {
        return this.mc.gameSettings.chatScale;
    }
    
    public boolean getChatOpen() {
        return this.mc.currentScreen instanceof net.minecraft.client.gui.GuiChat;
    }
    
    public List<String> getSentMessages() {
        return this.sentMessages;
    }
    
    public static int calculateChatboxWidth(final float n) {
        return MathHelper.floor(n * 280.0f + 40.0f);
    }
    
    public void printChatMessageWithOptionalDeletion(final ITextComponent textComponent, final int n) {
        GuiChat.percentComplete = 0.0f;
        this.setChatLine(textComponent, n, this.mc.ingameGUI.getUpdateCounter(), false);
        GuiChat.LOGGER.info("[CHAT] {}", (Object)textComponent.getUnformattedText().replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n"));
    }
    
    public void drawChat(final int n) {
        if (this.configuring) {
            return;
        }
        if (GuiChat.prevMillis == -1L) {
            GuiChat.prevMillis = System.currentTimeMillis();
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final long n2 = currentTimeMillis - GuiChat.prevMillis;
        GuiChat.prevMillis = currentTimeMillis;
        updatePercentage(n2);
        final float n3;
        final float clamp = MathUtil.clamp(1.0f - (n3 = GuiChat.percentComplete - 1.0f) * n3 * n3 * n3, 0.0f, 1.0f);
        if (this.mc.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN) {
            final int getLineCount = this.getLineCount();
            final int size = this.drawnChatLines.size();
            final float n4 = this.mc.gameSettings.chatOpacity * 0.9f + 0.1f;
            if (size > 0) {
                final boolean getChatOpen = this.getChatOpen();
                final float getChatScale = this.getChatScale();
                GlStateManager.pushMatrix();
                if (ChatModifier.getInstance().isOn() && (boolean)ChatModifier.getInstance().smoothChat.getValue() && ChatModifier.getInstance().type.getValue() == ChatModifier.Type.VERTICAL && !this.isScrolled) {
                    GlStateManager.translate(2.0f + ((Double)ChatModifier.getInstance().xOffset.getValue()).floatValue(), 8.0f + ((Double)ChatModifier.getInstance().yOffset.getValue()).floatValue() + (9.0f - 9.0f * clamp) * getChatScale, 0.0f);
                }
                else {
                    GlStateManager.translate(2.0f + ((Double)ChatModifier.getInstance().xOffset.getValue()).floatValue(), 8.0f + ((Double)ChatModifier.getInstance().yOffset.getValue()).floatValue(), 0.0f);
                }
                GlStateManager.scale(getChatScale, getChatScale, 1.0f);
                int n5 = 0;
                for (int n6 = 0; n6 + this.scrollPos < this.drawnChatLines.size() && n6 < getLineCount; ++n6) {
                    final ChatLine chatLine = this.drawnChatLines.get(n6 + this.scrollPos);
                    if (chatLine != null) {
                        final int n7;
                        if ((n7 = n - chatLine.getUpdatedCounter()) < 200 || getChatOpen) {
                            final double clamp = MathHelper.clamp((1.0 - n7 / 200.0) * 10.0, 0.0, 1.0);
                            int n8 = (int)(255.0 * (clamp * clamp));
                            if (getChatOpen) {
                                n8 = 255;
                            }
                            final int n9 = (int)(n8 * n4);
                            ++n5;
                            if (n9 > 3) {
                                final int n10 = -n6 * 9;
                                final String getFormattedText = chatLine.getChatComponent().getFormattedText();
                                GlStateManager.enableBlend();
                                if ((boolean)ChatModifier.getInstance().smoothChat.getValue() && n6 <= GuiChat.newLines) {
                                    if (this.messageTimer.passedMs((long)((Double)ChatModifier.getInstance().vSpeed.getValue()).intValue()) && GuiChat.messageAdd < 0) {
                                        if ((GuiChat.messageAdd += ((Double)ChatModifier.getInstance().vIncrements.getValue()).intValue()) > 0) {
                                            GuiChat.messageAdd = 0;
                                        }
                                        this.messageTimer.reset();
                                    }
                                    this.mc.fontRenderer.drawStringWithShadow(getFormattedText, 0.0f + ((ChatModifier.getInstance().type.getValue() == ChatModifier.Type.HORIZONTAL) ? GuiChat.messageAdd : 0), (float)(n10 - 8), 16777215 + (n9 << 24));
                                }
                                else {
                                    this.mc.fontRenderer.drawStringWithShadow(getFormattedText, 0.0f + ((ChatModifier.getInstance().type.getValue() == ChatModifier.Type.VERTICAL) ? GuiChat.messageAdd : 0), (float)(n10 - 8), 16777215 + (n9 << 24));
                                }
                                GlStateManager.disableAlpha();
                                GlStateManager.disableBlend();
                            }
                        }
                    }
                }
                if (getChatOpen) {
                    final int FONT_HEIGHT = this.mc.fontRenderer.FONT_HEIGHT;
                    GlStateManager.translate(-3.0f, 0.0f, 0.0f);
                    final int n11 = size * FONT_HEIGHT + size;
                    final int n12 = n5 * FONT_HEIGHT + n5;
                    final int n13 = this.scrollPos * n12 / size;
                    final int n14 = n12 * n12 / n11;
                    if (n11 != n12) {
                        final int n15 = (n13 > 0) ? 170 : 96;
                        Gui.drawRect(0, -n13, 2, -n13 - n14, (this.isScrolled ? 13382451 : 3355562) + (n15 << 24));
                        Gui.drawRect(2, -n13, 1, -n13 - n14, 13421772 + (n15 << 24));
                    }
                }
                GlStateManager.popMatrix();
            }
        }
    }
    
    public static int calculateChatboxHeight(final float n) {
        return MathHelper.floor(n * 160.0f + 20.0f);
    }
    
    static {
        LOGGER = LuigiHack.LOGGER;
        GuiChat.percentComplete = 0.0f;
        GuiChat.prevMillis = -1L;
    }
    
    public int getLineCount() {
        return this.getChatHeight() / 9;
    }
    
    public GuiChat(final Minecraft mc) {
        super(mc);
        this.messageTimer = new Timer();
        this.sentMessages = (List<String>)Lists.newArrayList();
        this.chatLines = (List<ChatLine>)Lists.newArrayList();
        this.drawnChatLines = (List<ChatLine>)Lists.newArrayList();
        this.mc = mc;
    }
    
    public void addToSentMessages(final String anObject) {
        if (this.sentMessages.isEmpty() || !this.sentMessages.get(this.sentMessages.size() - 1).equals(anObject)) {
            this.sentMessages.add(anObject);
        }
    }
    
    public void refreshChat() {
        this.drawnChatLines.clear();
        this.resetScroll();
        for (int i = this.chatLines.size() - 1; i >= 0; --i) {
            final ChatLine chatLine = this.chatLines.get(i);
            this.setChatLine(chatLine.getChatComponent(), chatLine.getChatLineID(), chatLine.getUpdatedCounter(), true);
        }
    }
    
    @Nullable
    public ITextComponent getChatComponent(final int n, final int n2) {
        if (!this.getChatOpen()) {
            return null;
        }
        final int getScaleFactor = new ScaledResolution(this.mc).getScaleFactor();
        final float getChatScale = this.getChatScale();
        final int n3 = n / getScaleFactor - 2 - ((Double)ChatModifier.getInstance().xOffset.getValue()).intValue();
        final int n4 = n2 / getScaleFactor - 40 + ((Double)ChatModifier.getInstance().yOffset.getValue()).intValue();
        final int floor = MathHelper.floor(n3 / getChatScale);
        final int floor2 = MathHelper.floor(n4 / getChatScale);
        if (floor < 0 || floor2 < 0) {
            return null;
        }
        final int min = Math.min(this.getLineCount(), this.drawnChatLines.size());
        if (floor <= MathHelper.floor(this.getChatWidth() / this.getChatScale()) && floor2 < this.mc.fontRenderer.FONT_HEIGHT * min + min) {
            final int n5 = floor2 / this.mc.fontRenderer.FONT_HEIGHT + this.scrollPos;
            if (n5 >= 0 && n5 < this.drawnChatLines.size()) {
                final ChatLine chatLine = this.drawnChatLines.get(n5);
                int n6 = 0;
                for (final ITextComponent textComponent : chatLine.getChatComponent()) {
                    if (textComponent instanceof TextComponentString) {
                        if ((n6 += this.mc.fontRenderer.getStringWidth(GuiUtilRenderComponents.removeTextColorsIfConfigured(((TextComponentString)textComponent).getText(), false))) <= floor) {
                            continue;
                        }
                        return textComponent;
                    }
                }
            }
            return null;
        }
        return null;
    }
    
    public static void updatePercentage(final long n) {
        if (GuiChat.percentComplete < 1.0f) {
            GuiChat.percentComplete += 0.004f * n;
        }
        GuiChat.percentComplete = MathUtil.clamp(GuiChat.percentComplete, 0.0f, 1.0f);
    }
    
    private void setChatLine(final ITextComponent textComponent, final int n, final int n2, final boolean b) {
        GuiChat.messageAdd = -((Double)ChatModifier.getInstance().vLength.getValue()).intValue();
        if (n != 0) {
            this.deleteChatLine(n);
        }
        final List splitText = GuiUtilRenderComponents.splitText(textComponent, MathHelper.floor(this.getChatWidth() / this.getChatScale()), this.mc.fontRenderer, false, false);
        final boolean getChatOpen = this.getChatOpen();
        GuiChat.newLines = splitText.size() - 1;
        for (final ITextComponent textComponent2 : splitText) {
            if (getChatOpen && this.scrollPos > 0) {
                this.isScrolled = true;
                this.scroll(1);
            }
            this.drawnChatLines.add(0, new ChatLine(n2, textComponent2, n));
        }
        while (this.drawnChatLines.size() > 100) {
            this.drawnChatLines.remove(this.drawnChatLines.size() - 1);
        }
        if (!b) {
            this.chatLines.add(0, new ChatLine(n2, textComponent, n));
            while (this.chatLines.size() > 100) {
                this.chatLines.remove(this.chatLines.size() - 1);
            }
        }
    }
    
    public void printChatMessage(final ITextComponent textComponent) {
        this.printChatMessageWithOptionalDeletion(textComponent, 0);
    }
    
    public void resetScroll() {
        this.scrollPos = 0;
        this.isScrolled = false;
    }
    
    public void deleteChatLine(final int n) {
        final Iterator<ChatLine> iterator = this.drawnChatLines.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getChatLineID() != n) {
                continue;
            }
            iterator.remove();
        }
        final Iterator<ChatLine> iterator2 = this.chatLines.iterator();
        while (iterator2.hasNext()) {
            if (iterator2.next().getChatLineID() != n) {
                continue;
            }
            iterator2.remove();
            break;
        }
    }
    
    public int getChatHeight() {
        return calculateChatboxHeight(this.getChatOpen() ? this.mc.gameSettings.chatHeightFocused : this.mc.gameSettings.chatHeightUnfocused);
    }
}
