//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.gui.font;

import net.minecraft.client.renderer.texture.*;
import org.lwjgl.opengl.*;
import java.util.*;
import java.awt.*;
import me.snow.luigihack.impl.modules.client.*;
import net.minecraft.client.renderer.*;

public class CustomFont extends CFont
{
    protected /* synthetic */ DynamicTexture texItalicBold;
    protected /* synthetic */ DynamicTexture texBold;
    protected /* synthetic */ CFont.CharData[] boldItalicChars;
    protected /* synthetic */ CFont.CharData[] italicChars;
    protected /* synthetic */ CFont.CharData[] boldChars;
    protected /* synthetic */ DynamicTexture texItalic;
    private final /* synthetic */ int[] colorCode;
    
    public void setFractionalMetrics(final boolean fractionalMetrics) {
        super.setFractionalMetrics(fractionalMetrics);
        this.setupBoldItalicIDs();
    }
    
    public float drawString(final String s, final float n, final float n2, final int n3) {
        return this.drawString(s, n, n2, n3, false);
    }
    
    public int getStringWidth(final String s) {
        if (s == null) {
            return 0;
        }
        int n = 0;
        CFont.CharData[] array = this.charData;
        int n2 = 0;
        int n3 = 0;
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '§' && i < length) {
                final int index = "0123456789abcdefklmnor".indexOf(char1);
                if (index < 16) {
                    n2 = 0;
                    n3 = 0;
                }
                else if (index == 17) {
                    n2 = 1;
                    array = ((n3 != 0) ? this.boldItalicChars : this.boldChars);
                }
                else if (index == 20) {
                    n3 = 1;
                    array = ((n2 != 0) ? this.boldItalicChars : this.italicChars);
                }
                else if (index == 21) {
                    n2 = 0;
                    n3 = 0;
                    array = this.charData;
                }
                ++i;
            }
            else if (char1 < array.length) {
                if (char1 >= '\0') {
                    n += array[char1].width - 8 + this.charOffset;
                }
            }
        }
        return n / 2;
    }
    
    public float drawStringWithShadow(final String s, final double n, final double n2, final int n3) {
        return Math.max(this.drawString(s, n + 1.0, n2 + 1.0, n3, true), this.drawString(s, n, n2, n3, false));
    }
    
    private void drawLine(final double n, final double n2, final double n3, final double n4, final float n5) {
        GL11.glDisable(3553);
        GL11.glLineWidth(n5);
        GL11.glBegin(1);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n3, n4);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    public void setAntiAlias(final boolean antiAlias) {
        super.setAntiAlias(antiAlias);
        this.setupBoldItalicIDs();
    }
    
    private void setupMinecraftColorcodes() {
        for (int i = 0; i < 32; ++i) {
            final int n = (i >> 3 & 0x1) * 85;
            int n2 = (i >> 2 & 0x1) * 170 + n;
            int n3 = (i >> 1 & 0x1) * 170 + n;
            int n4 = (i >> 0 & 0x1) * 170 + n;
            if (i == 6) {
                n2 += 85;
            }
            if (i >= 16) {
                n2 /= 4;
                n3 /= 4;
                n4 /= 4;
            }
            this.colorCode[i] = ((n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | (n4 & 0xFF));
        }
    }
    
    public List<String> wrapWords(final String e, final double n) {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.getStringWidth(e) > n) {
            final String[] split = e.split(" ");
            String s = "";
            char c = '\uffff';
            for (final String str : split) {
                for (int j = 0; j < str.toCharArray().length; ++j) {
                    if (str.toCharArray()[j] == '§') {
                        if (j < str.toCharArray().length - 1) {
                            c = str.toCharArray()[j + 1];
                        }
                    }
                }
                if (this.getStringWidth(String.valueOf(new StringBuilder().append(s).append(str).append(" "))) < n) {
                    s = String.valueOf(new StringBuilder().append(s).append(str).append(" "));
                }
                else {
                    list.add(s);
                    s = String.valueOf(new StringBuilder().append("§").append(c).append(str).append(" "));
                }
            }
            if (s.length() > 0) {
                if (this.getStringWidth(s) < n) {
                    list.add(String.valueOf(new StringBuilder().append("§").append(c).append(s).append(" ")));
                }
                else {
                    final Iterator<String> iterator = this.formatString(s, n).iterator();
                    while (iterator.hasNext()) {
                        list.add(iterator.next());
                    }
                }
            }
        }
        else {
            list.add(e);
        }
        return list;
    }
    
    public List<String> formatString(final String s, final double n) {
        final ArrayList<String> list = new ArrayList<String>();
        String s2 = "";
        char c = '\uffff';
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char c2 = charArray[i];
            if (c2 == '§' && i < charArray.length - 1) {
                c = charArray[i + 1];
            }
            if (this.getStringWidth(String.valueOf(new StringBuilder().append(s2).append(c2))) < n) {
                s2 = String.valueOf(new StringBuilder().append(s2).append(c2));
            }
            else {
                list.add(s2);
                s2 = String.valueOf(new StringBuilder().append("§").append(c).append(c2));
            }
        }
        if (s2.length() > 0) {
            list.add(s2);
        }
        return list;
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.setupBoldItalicIDs();
    }
    
    public float drawString(final String s, final double n, final double n2, int n3, final boolean b) {
        final String s2 = (Media.getInstance().isOn() && (boolean)Media.getInstance().changeOwn.getValue()) ? s.replace(Media.getPlayerName(), (CharSequence)Media.getInstance().ownName.getValue()) : s;
        double n4 = n;
        double n5 = n2;
        if (me.snow.luigihack.impl.modules.client.CustomFont.getInstance().isOn() && !(boolean)me.snow.luigihack.impl.modules.client.CustomFont.getInstance().shadow.getValue() && b) {
            n4 -= 0.5;
            n5 -= 0.5;
        }
        final double n6 = n4 - 1.0;
        if (s2 == null) {
            return 0.0f;
        }
        if (n3 == 553648127) {
            n3 = 16777215;
        }
        if ((n3 & 0xFC000000) == 0x0) {
            n3 |= 0xFF000000;
        }
        if (b) {
            n3 = ((n3 & 0xFCFCFC) >> 2 | (n3 & 0xFF000000));
        }
        CFont.CharData[] array = this.charData;
        final float n7 = (n3 >> 24 & 0xFF) / 255.0f;
        int n8 = 0;
        int n9 = 0;
        boolean b2 = false;
        boolean b3 = false;
        final boolean b4 = true;
        double n10 = n6 * 2.0;
        final double n11 = (n5 - 3.0) * 2.0;
        if (b4) {
            GL11.glPushMatrix();
            GlStateManager.scale(0.5, 0.5, 0.5);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color((n3 >> 16 & 0xFF) / 255.0f, (n3 >> 8 & 0xFF) / 255.0f, (n3 & 0xFF) / 255.0f, n7);
            final int length = s2.length();
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture(this.tex.getGlTextureId());
            GL11.glBindTexture(3553, this.tex.getGlTextureId());
            for (int i = 0; i < length; ++i) {
                final char char1 = s2.charAt(i);
                if (char1 == '§' && i < length) {
                    int index = 21;
                    try {
                        index = "0123456789abcdefklmnor".indexOf(s2.charAt(i + 1));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (index < 16) {
                        n8 = 0;
                        n9 = 0;
                        b3 = false;
                        b2 = false;
                        GlStateManager.bindTexture(this.tex.getGlTextureId());
                        array = this.charData;
                        if (index < 0 || index > 15) {
                            index = 15;
                        }
                        if (b) {
                            index += 16;
                        }
                        final int n12 = this.colorCode[index];
                        GlStateManager.color((n12 >> 16 & 0xFF) / 255.0f, (n12 >> 8 & 0xFF) / 255.0f, (n12 & 0xFF) / 255.0f, n7);
                    }
                    else if (index == 17) {
                        n8 = 1;
                        if (n9 != 0) {
                            GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                            array = this.boldItalicChars;
                        }
                        else {
                            GlStateManager.bindTexture(this.texBold.getGlTextureId());
                            array = this.boldChars;
                        }
                    }
                    else if (index == 18) {
                        b2 = true;
                    }
                    else if (index == 19) {
                        b3 = true;
                    }
                    else if (index == 20) {
                        n9 = 1;
                        if (n8 != 0) {
                            GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                            array = this.boldItalicChars;
                        }
                        else {
                            GlStateManager.bindTexture(this.texItalic.getGlTextureId());
                            array = this.italicChars;
                        }
                    }
                    else if (index == 21) {
                        n8 = 0;
                        n9 = 0;
                        b3 = false;
                        b2 = false;
                        GlStateManager.color((n3 >> 16 & 0xFF) / 255.0f, (n3 >> 8 & 0xFF) / 255.0f, (n3 & 0xFF) / 255.0f, n7);
                        GlStateManager.bindTexture(this.tex.getGlTextureId());
                        array = this.charData;
                    }
                    ++i;
                }
                else if (char1 < array.length) {
                    if (char1 >= '\0') {
                        GL11.glBegin(4);
                        this.drawChar(array, char1, (float)n10, (float)n11);
                        GL11.glEnd();
                        if (b2) {
                            this.drawLine(n10, n11 + array[char1].height / 2, n10 + array[char1].width - 8.0, n11 + array[char1].height / 2, 1.0f);
                        }
                        if (b3) {
                            this.drawLine(n10, n11 + array[char1].height - 2.0, n10 + array[char1].width - 8.0, n11 + array[char1].height - 2.0, 1.0f);
                        }
                        n10 += array[char1].width - 8 + this.charOffset;
                    }
                }
            }
            GL11.glHint(3155, 4352);
            GL11.glPopMatrix();
        }
        return (float)n10 / 2.0f;
    }
    
    private void setupBoldItalicIDs() {
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
        this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
    }
    
    public float drawCenteredString(final String s, final float n, final float n2, final int n3) {
        return this.drawString(s, n - this.getStringWidth(s) / 2, n2, n3);
    }
    
    public float drawCenteredStringWithShadow(final String s, final float n, final float n2, final int n3) {
        this.drawString(s, n - this.getStringWidth(s) / 2 + 1.0, n2 + 1.0, n3, true);
        return this.drawString(s, n - this.getStringWidth(s) / 2, n2, n3);
    }
    
    public CustomFont(final Font font, final boolean b, final boolean b2) {
        super(font, b, b2);
        this.colorCode = new int[32];
        this.boldChars = new CFont.CharData[256];
        this.italicChars = new CFont.CharData[256];
        this.boldItalicChars = new CFont.CharData[256];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }
}
