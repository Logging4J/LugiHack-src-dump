//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.render;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.entity.player.*;
import net.minecraft.nbt.*;
import me.snow.luigihack.*;
import net.minecraft.client.renderer.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraft.entity.*;
import net.minecraft.client.network.*;
import java.util.*;
import me.snow.luigihack.impl.modules.client.*;
import java.awt.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import org.lwjgl.opengl.*;
import net.minecraft.enchantment.*;
import net.minecraft.util.text.*;
import me.snow.luigihack.api.util.*;

public class Nametags extends Module
{
    private final /* synthetic */ Setting<Float> scaling;
    private final /* synthetic */ Setting<Float> factor;
    private final /* synthetic */ Setting<Boolean> rect;
    private final /* synthetic */ Setting<Boolean> health;
    private final /* synthetic */ Setting<Integer> blueSetting;
    private final /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Mode> mode;
    private static /* synthetic */ Nametags INSTANCE;
    private final /* synthetic */ Setting<Boolean> invisibles;
    private final /* synthetic */ Setting<Boolean> ping;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Boolean> heldStackName;
    private final /* synthetic */ Setting<Boolean> entityID;
    private final /* synthetic */ Setting<Boolean> onlyFov;
    private final /* synthetic */ Setting<Integer> alphaSetting;
    private final /* synthetic */ Setting<Integer> redSetting;
    private final /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Setting<Boolean> gamemode;
    private final /* synthetic */ Setting<Integer> greenSetting;
    private final /* synthetic */ Setting<Boolean> scaleing;
    private final /* synthetic */ Setting<Boolean> smartScale;
    private final /* synthetic */ Setting<Boolean> totemPops;
    private final /* synthetic */ Setting<Boolean> armor;
    
    public void drawRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)this.lineWidth.getValue());
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    private float getBiggestArmorTag(final EntityPlayer entityPlayer) {
        float n = 0.0f;
        boolean b = false;
        for (final ItemStack itemStack : entityPlayer.inventory.armorInventory) {
            float n2 = 0.0f;
            if (itemStack != null) {
                final NBTTagList getEnchantmentTagList = itemStack.getEnchantmentTagList();
                for (int i = 0; i < getEnchantmentTagList.tagCount(); ++i) {
                    if (Enchantment.getEnchantmentByID((int)getEnchantmentTagList.getCompoundTagAt(i).getShort("id")) != null) {
                        n2 += 8.0f;
                        b = true;
                    }
                }
            }
            if (n2 <= n) {
                continue;
            }
            n = n2;
        }
        final ItemStack copy = entityPlayer.getHeldItemMainhand().copy();
        if (copy.hasEffect()) {
            float n3 = 0.0f;
            final NBTTagList getEnchantmentTagList2 = copy.getEnchantmentTagList();
            for (int j = 0; j < getEnchantmentTagList2.tagCount(); ++j) {
                if (Enchantment.getEnchantmentByID((int)getEnchantmentTagList2.getCompoundTagAt(j).getShort("id")) != null) {
                    n3 += 8.0f;
                    b = true;
                }
            }
            if (n3 > n) {
                n = n3;
            }
        }
        final ItemStack copy2;
        if ((copy2 = entityPlayer.getHeldItemOffhand().copy()).hasEffect()) {
            float n4 = 0.0f;
            final NBTTagList getEnchantmentTagList3 = copy2.getEnchantmentTagList();
            for (int k = 0; k < getEnchantmentTagList3.tagCount(); ++k) {
                if (Enchantment.getEnchantmentByID((int)getEnchantmentTagList3.getCompoundTagAt(k).getShort("id")) != null) {
                    n4 += 8.0f;
                    b = true;
                }
            }
            if (n4 > n) {
                n = n4;
            }
        }
        return (b ? 0 : 20) + n;
    }
    
    private int getDisplayColour(final EntityPlayer entityPlayer) {
        int rgba = -1;
        if (LuigiHack.friendManager.isFriend(entityPlayer)) {
            return -10027009;
        }
        if (entityPlayer.isInvisible()) {
            rgba = -1113785;
        }
        else if (entityPlayer.isSneaking()) {
            rgba = ColorUtil.toRGBA(221, 124, 30, 255);
        }
        return rgba;
    }
    
    private void renderItemStack(final ItemStack itemStack, final int n) {
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        Nametags.mc.getRenderItem().zLevel = -150.0f;
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.disableCull();
        Nametags.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, -26);
        Nametags.mc.getRenderItem().renderItemOverlays(Nametags.mc.fontRenderer, itemStack, n, -26);
        Nametags.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        GlStateManager.disableDepth();
        if (this.mode.getValue() != Mode.NONE) {
            this.renderEnchantmentText(itemStack, n);
        }
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GlStateManager.popMatrix();
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!fullNullCheck()) {
            for (final EntityPlayer entityPlayer : Nametags.mc.world.playerEntities) {
                if (entityPlayer != null && !entityPlayer.equals((Object)Nametags.mc.player) && entityPlayer.isEntityAlive() && (!entityPlayer.isInvisible() || (boolean)this.invisibles.getValue())) {
                    if ((boolean)this.onlyFov.getValue() && RotationUtil.isInFov((Entity)entityPlayer)) {
                        continue;
                    }
                    this.renderNameTag(entityPlayer, this.interpolate(entityPlayer.lastTickPosX, entityPlayer.posX, render3DEvent.getPartialTicks()) - Nametags.mc.getRenderManager().renderPosX, this.interpolate(entityPlayer.lastTickPosY, entityPlayer.posY, render3DEvent.getPartialTicks()) - Nametags.mc.getRenderManager().renderPosY, this.interpolate(entityPlayer.lastTickPosZ, entityPlayer.posZ, render3DEvent.getPartialTicks()) - Nametags.mc.getRenderManager().renderPosZ, render3DEvent.getPartialTicks());
                }
            }
        }
    }
    
    private String getDisplayTag(final EntityPlayer entityPlayer) {
        String str = entityPlayer.getDisplayName().getFormattedText();
        if (str.contains(Nametags.mc.getSession().getUsername())) {
            str = Nametags.mc.player.getDisplayNameString();
        }
        if (!(boolean)this.health.getValue()) {
            return str;
        }
        final float health = EntityUtil.getHealth((Entity)entityPlayer);
        final String s = (health > 18.0f) ? "§a" : ((health > 16.0f) ? "§2" : ((health > 12.0f) ? "§e" : ((health > 8.0f) ? "§6" : ((health > 5.0f) ? "§c" : "§4"))));
        String value = "";
        if (this.ping.getValue()) {
            try {
                value = String.valueOf(new StringBuilder().append(value).append(" ").append(Objects.requireNonNull(Nametags.mc.getConnection()).getPlayerInfo(entityPlayer.getUniqueID()).getResponseTime()).append("ms"));
            }
            catch (Exception ex) {}
        }
        String value2 = " ";
        if (this.totemPops.getValue()) {
            value2 = String.valueOf(new StringBuilder().append(value2).append(LuigiHack.totemPopManager.getTotemPopString(entityPlayer)));
        }
        String value3 = "";
        if (this.entityID.getValue()) {
            value3 = String.valueOf(new StringBuilder().append(value3).append(" ID: ").append(entityPlayer.getEntityId()).append(" "));
        }
        String s2 = "";
        if (this.gamemode.getValue()) {
            if (entityPlayer.isCreative()) {
                String.valueOf(new StringBuilder().append(s2).append("[C] "));
            }
            else {
                s2 = ((entityPlayer.isSpectator() || entityPlayer.isInvisible()) ? String.valueOf(new StringBuilder().append(s2).append("[I] ")) : String.valueOf(new StringBuilder().append(s2).append("[S] ")));
            }
        }
        return String.valueOf(new StringBuilder().append(str).append(value3).append(s2).append(value).append((Math.floor(health) == health) ? String.valueOf(new StringBuilder().append(s).append(" ").append((health > 0.0f) ? Integer.valueOf((int)Math.floor(health)) : "dead")) : String.valueOf(new StringBuilder().append(s).append(" ").append((health > 0.0f) ? Integer.valueOf((int)health) : "dead"))).append(value2));
    }
    
    private void renderNameTag(final EntityPlayer entityPlayer, final double n, final double n2, final double n3, final float n4) {
        final double n5 = n2 + (entityPlayer.isSneaking() ? 0.5 : 0.7);
        final Entity getRenderViewEntity = Nametags.mc.getRenderViewEntity();
        assert getRenderViewEntity != null;
        final double posX = getRenderViewEntity.posX;
        final double posY = getRenderViewEntity.posY;
        final double posZ = getRenderViewEntity.posZ;
        getRenderViewEntity.posX = this.interpolate(getRenderViewEntity.prevPosX, getRenderViewEntity.posX, n4);
        getRenderViewEntity.posY = this.interpolate(getRenderViewEntity.prevPosY, getRenderViewEntity.posY, n4);
        getRenderViewEntity.posZ = this.interpolate(getRenderViewEntity.prevPosZ, getRenderViewEntity.posZ, n4);
        final String displayTag = this.getDisplayTag(entityPlayer);
        final double getDistance = getRenderViewEntity.getDistance(n + Nametags.mc.getRenderManager().viewerPosX, n2 + Nametags.mc.getRenderManager().viewerPosY, n3 + Nametags.mc.getRenderManager().viewerPosZ);
        final int n6 = this.renderer.getStringWidth(displayTag) / 2;
        double n7 = (0.0018 + (float)this.scaling.getValue() * (getDistance * (float)this.factor.getValue())) / 1000.0;
        if (getDistance <= 8.0 && (boolean)this.smartScale.getValue()) {
            n7 = 0.0245;
        }
        if (!(boolean)this.scaleing.getValue()) {
            n7 = (float)this.scaling.getValue() / 100.0;
        }
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.translate((float)n, (float)n5 + 1.4f, (float)n3);
        GlStateManager.rotate(-Nametags.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(Nametags.mc.getRenderManager().playerViewX, (Nametags.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n7, -n7, n7);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.enableBlend();
        if (this.rect.getValue()) {
            this.drawRect((float)(-n6 - 2), (float)(-(this.renderer.getFontHeight() + 1)), n6 + 2.0f, 1.5f, 1426063360);
            if (this.outline.getValue()) {
                this.drawOutlineRect((float)(-n6 - 2), (float)(-(Nametags.mc.fontRenderer.FONT_HEIGHT + 1)), n6 + 2.0f, 1.5f, ((boolean)this.colorSync.getValue()) ? Colors.INSTANCE.getCurrentColorHex() : new Color((int)this.redSetting.getValue(), (int)this.greenSetting.getValue(), (int)this.blueSetting.getValue(), (int)this.alphaSetting.getValue()).getRGB());
            }
        }
        GlStateManager.disableBlend();
        final ItemStack copy = entityPlayer.getHeldItemMainhand().copy();
        if (copy.hasEffect() && (copy.getItem() instanceof ItemTool || copy.getItem() instanceof ItemArmor)) {
            copy.stackSize = 1;
        }
        if ((boolean)this.heldStackName.getValue() && !copy.isEmpty && copy.getItem() != Items.AIR) {
            final String getDisplayName = copy.getDisplayName();
            final int n8 = this.renderer.getStringWidth(getDisplayName) / 2;
            GL11.glPushMatrix();
            GL11.glScalef(0.75f, 0.75f, 0.0f);
            this.renderer.drawStringWithShadow(getDisplayName, (float)(-n8), -(this.getBiggestArmorTag(entityPlayer) + 20.0f), -1);
            GL11.glScalef(1.5f, 1.5f, 1.0f);
            GL11.glPopMatrix();
        }
        if (this.armor.getValue()) {
            GlStateManager.pushMatrix();
            int n9 = -8;
            final Iterator iterator = entityPlayer.inventory.armorInventory.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    continue;
                }
                n9 -= 8;
            }
            n9 -= 8;
            final ItemStack copy2 = entityPlayer.getHeldItemOffhand().copy();
            this.renderItemStack(copy, n9);
            n9 += 16;
            for (int i = 3; i >= 0; --i) {
                final ItemStack itemStack = (ItemStack)entityPlayer.inventory.armorInventory.get(i);
                if (itemStack != null) {
                    this.renderItemStack(itemStack.copy(), n9);
                    n9 += 16;
                }
            }
            this.renderItemStack(copy2, n9);
            GlStateManager.popMatrix();
        }
        this.renderer.drawStringWithShadow(displayTag, (float)(-n6), (float)(-(this.renderer.getFontHeight() - 1)), this.getDisplayColour(entityPlayer));
        getRenderViewEntity.posX = posX;
        getRenderViewEntity.posY = posY;
        getRenderViewEntity.posZ = posZ;
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
    }
    
    public void drawOutlineRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)this.lineWidth.getValue());
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        getBuffer.begin(2, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    private void renderEnchantmentText(final ItemStack itemStack, final int n) {
        int n2 = -34;
        if (itemStack.getItem() == Items.GOLDEN_APPLE && itemStack.hasEffect()) {
            this.renderer.drawStringWithShadow("god", (float)(n * 2), (float)n2, -3977919);
            n2 -= 8;
        }
        final NBTTagList getEnchantmentTagList = itemStack.getEnchantmentTagList();
        for (int i = 0; i < getEnchantmentTagList.tagCount(); ++i) {
            final short getShort = getEnchantmentTagList.getCompoundTagAt(i).getShort("id");
            final short getShort2 = getEnchantmentTagList.getCompoundTagAt(i).getShort("lvl");
            final Enchantment getEnchantmentByID = Enchantment.getEnchantmentByID((int)getShort);
            if (getEnchantmentByID != null) {
                if (this.mode.getValue() == Mode.MINIMAL) {
                    if (!(getEnchantmentByID instanceof EnchantmentProtection)) {
                        continue;
                    }
                    final EnchantmentProtection enchantmentProtection = (EnchantmentProtection)getEnchantmentByID;
                    if (enchantmentProtection.protectionType != EnchantmentProtection.Type.EXPLOSION && enchantmentProtection.protectionType != EnchantmentProtection.Type.ALL) {
                        continue;
                    }
                }
                this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(getEnchantmentByID.isCurse() ? String.valueOf(new StringBuilder().append(TextFormatting.RED).append(getEnchantmentByID.getTranslatedName((int)getShort2).substring(11).substring(0, 1).toLowerCase())) : getEnchantmentByID.getTranslatedName((int)getShort2).substring(0, 1).toLowerCase()).append(getShort2)), (float)(n * 2), (float)n2, -1);
                n2 -= 8;
            }
        }
        if (DamageUtil.hasDurability(itemStack)) {
            final int roundedDamage = DamageUtil.getRoundedDamage(itemStack);
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append((roundedDamage >= 60) ? "§a" : ((roundedDamage >= 25) ? "§e" : "§c")).append(roundedDamage).append("%")), (float)(n * 2), (float)n2, -1);
        }
    }
    
    private double interpolate(final double n, final double n2, final float n3) {
        return n + (n2 - n) * n3;
    }
    
    private void setInstance() {
        Nametags.INSTANCE = this;
    }
    
    static {
        Nametags.INSTANCE = new Nametags();
    }
    
    public static Nametags getInstance() {
        if (Nametags.INSTANCE == null) {
            Nametags.INSTANCE = new Nametags();
        }
        return Nametags.INSTANCE;
    }
    
    public Nametags() {
        super("Nametags", "Better Nametags.", Module.Category.RENDER, false, false, false);
        this.health = (Setting<Boolean>)this.register(new Setting("Health", (Object)true));
        this.armor = (Setting<Boolean>)this.register(new Setting("Armor", (Object)true));
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (Object)Mode.MINIMAL));
        this.invisibles = (Setting<Boolean>)this.register(new Setting("Invisibles", (Object)false));
        this.ping = (Setting<Boolean>)this.register(new Setting("Ping", (Object)true));
        this.totemPops = (Setting<Boolean>)this.register(new Setting("TotemPops", (Object)true));
        this.gamemode = (Setting<Boolean>)this.register(new Setting("Gamemode", (Object)false));
        this.entityID = (Setting<Boolean>)this.register(new Setting("ID", (Object)false));
        this.rect = (Setting<Boolean>)this.register(new Setting("Rectangle", (Object)true));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (Object)Boolean.FALSE, p0 -> (boolean)this.rect.getValue()));
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Sync", (Object)Boolean.FALSE, p0 -> (boolean)this.outline.getValue()));
        this.redSetting = (Setting<Integer>)this.register(new Setting("Red", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.outline.getValue()));
        this.greenSetting = (Setting<Integer>)this.register(new Setting("Green", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.outline.getValue()));
        this.blueSetting = (Setting<Integer>)this.register(new Setting("Blue", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.outline.getValue()));
        this.alphaSetting = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.outline.getValue()));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (Object)1.5f, (Object)0.1f, (Object)5.0f, p0 -> (boolean)this.outline.getValue()));
        this.heldStackName = (Setting<Boolean>)this.register(new Setting("StackName", (Object)false));
        this.onlyFov = (Setting<Boolean>)this.register(new Setting("OnlyFov", (Object)false));
        this.scaleing = (Setting<Boolean>)this.register(new Setting("Scale", (Object)true));
        this.scaling = (Setting<Float>)this.register(new Setting("Size", (Object)3.0f, (Object)0.1f, (Object)20.0f));
        this.factor = (Setting<Float>)this.register(new Setting("Factor", (Object)1.0f, (Object)0.1f, (Object)1.0f, p0 -> (boolean)this.scaleing.getValue()));
        this.smartScale = (Setting<Boolean>)this.register(new Setting("SmartScale", (Object)Boolean.TRUE, p0 -> (boolean)this.scaleing.getValue()));
        this.setInstance();
    }
    
    public enum Mode
    {
        FULL, 
        NONE, 
        MINIMAL;
    }
}
