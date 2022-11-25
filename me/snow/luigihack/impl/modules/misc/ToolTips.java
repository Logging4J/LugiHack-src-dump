//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.misc;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.client.event.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.world.storage.*;
import net.minecraft.client.renderer.*;
import net.minecraft.tileentity.*;
import net.minecraft.nbt.*;
import net.minecraft.item.*;
import net.minecraft.client.gui.inventory.*;
import org.lwjgl.input.*;
import net.minecraft.inventory.*;
import java.util.*;
import java.awt.*;
import me.snow.luigihack.api.util.*;
import net.minecraft.util.*;
import java.util.concurrent.*;
import me.snow.luigihack.api.event.events.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ToolTips extends Module
{
    private final /* synthetic */ Setting<Integer> yPerPlayer;
    public /* synthetic */ Setting<Boolean> offsets;
    public /* synthetic */ Setting<Boolean> shulkerSpy;
    public /* synthetic */ Setting<Bind> peek;
    public /* synthetic */ Setting<Boolean> shulkers;
    private final /* synthetic */ Setting<Integer> blue;
    private static final /* synthetic */ ResourceLocation MAP;
    public /* synthetic */ Setting<Boolean> maps;
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Integer> red;
    private static final /* synthetic */ ResourceLocation SHULKER_GUI_TEXTURE;
    private final /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Integer> cooldown;
    public /* synthetic */ Setting<Boolean> textColor;
    private final /* synthetic */ Setting<Integer> xOffset;
    public /* synthetic */ Setting<Boolean> render;
    private static /* synthetic */ ToolTips INSTANCE;
    private /* synthetic */ int textRadarY;
    public /* synthetic */ Setting<Integer> invH;
    public /* synthetic */ Setting<Boolean> own;
    public /* synthetic */ Map<EntityPlayer, ItemStack> spiedPlayers;
    private final /* synthetic */ Setting<Integer> yOffset;
    public /* synthetic */ Map<EntityPlayer, Timer> playerTimers;
    private final /* synthetic */ Setting<Integer> trOffset;
    
    public static ToolTips getInstance() {
        if (ToolTips.INSTANCE == null) {
            ToolTips.INSTANCE = new ToolTips();
        }
        return ToolTips.INSTANCE;
    }
    
    static {
        MAP = new ResourceLocation("textures/map/map_background.png");
        SHULKER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/shulker_box.png");
        ToolTips.INSTANCE = new ToolTips();
    }
    
    @SubscribeEvent
    public void renderTooltip(final RenderTooltipEvent.PostText postText) {
        final MapData getMapData;
        if ((boolean)this.maps.getValue() && !postText.getStack().isEmpty() && postText.getStack().getItem() instanceof ItemMap && (getMapData = Items.FILLED_MAP.getMapData(postText.getStack(), (World)ToolTips.mc.world)) != null) {
            GlStateManager.pushMatrix();
            GlStateManager.color(1.0f, 1.0f, 1.0f);
            RenderHelper.disableStandardItemLighting();
            ToolTips.mc.getTextureManager().bindTexture(ToolTips.MAP);
            final Tessellator getInstance = Tessellator.getInstance();
            final BufferBuilder getBuffer = getInstance.getBuffer();
            final int n = 7;
            final float n2 = 135.0f;
            final float n3 = 0.5f;
            GlStateManager.translate((float)postText.getX(), postText.getY() - n2 * n3 - 5.0f, 0.0f);
            GlStateManager.scale(n3, n3, n3);
            getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
            getBuffer.pos((double)(-n), (double)n2, 0.0).tex(0.0, 1.0).endVertex();
            getBuffer.pos((double)n2, (double)n2, 0.0).tex(1.0, 1.0).endVertex();
            getBuffer.pos((double)n2, (double)(-n), 0.0).tex(1.0, 0.0).endVertex();
            getBuffer.pos((double)(-n), (double)(-n), 0.0).tex(0.0, 0.0).endVertex();
            getInstance.draw();
            ToolTips.mc.entityRenderer.getMapItemRenderer().renderMap(getMapData, false);
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
    
    public static void displayInv(final ItemStack itemStack, final String s) {
        try {
            final Item getItem = itemStack.getItem();
            final TileEntityShulkerBox tileEntityShulkerBox = new TileEntityShulkerBox();
            tileEntityShulkerBox.blockType = ((ItemShulkerBox)getItem).getBlock();
            tileEntityShulkerBox.setWorld((World)ToolTips.mc.world);
            ItemStackHelper.loadAllItems(Objects.requireNonNull(itemStack.getTagCompound()).getCompoundTag("BlockEntityTag"), tileEntityShulkerBox.items);
            tileEntityShulkerBox.readFromNBT(itemStack.getTagCompound().getCompoundTag("BlockEntityTag"));
            tileEntityShulkerBox.setCustomName((s == null) ? itemStack.getDisplayName() : s);
            final IInventory inventory;
            new Thread(() -> {
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException ex) {}
                ToolTips.mc.player.displayGUIChest(inventory);
            }).start();
        }
        catch (Exception ex2) {}
    }
    
    @Override
    public void onUpdate() {
        if (fullNullCheck() || !(boolean)this.shulkerSpy.getValue()) {
            return;
        }
        final Slot slotUnderMouse;
        final ItemStack getStack;
        if (((Bind)this.peek.getValue()).getKey() != -1 && ToolTips.mc.currentScreen instanceof GuiContainer && Keyboard.isKeyDown(((Bind)this.peek.getValue()).getKey()) && (slotUnderMouse = ((GuiContainer)ToolTips.mc.currentScreen).getSlotUnderMouse()) != null && (getStack = slotUnderMouse.getStack()) != null && getStack.getItem() instanceof ItemShulkerBox) {
            displayInv(getStack, null);
        }
        for (final EntityPlayer entityPlayer : ToolTips.mc.world.playerEntities) {
            if (entityPlayer != null && entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox && !EntityUtil.isFakePlayer(entityPlayer)) {
                if (!(boolean)this.own.getValue() && ToolTips.mc.player.equals((Object)entityPlayer)) {
                    continue;
                }
                this.spiedPlayers.put(entityPlayer, entityPlayer.getHeldItemMainhand());
            }
        }
    }
    
    public void renderShulkerToolTip(final ItemStack itemStack, final int n, final int n2, final String s) {
        final NBTTagCompound getTagCompound = itemStack.getTagCompound();
        final NBTTagCompound getCompoundTag;
        if (getTagCompound != null && getTagCompound.hasKey("BlockEntityTag", 10) && (getCompoundTag = getTagCompound.getCompoundTag("BlockEntityTag")).hasKey("Items", 9)) {
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            ToolTips.mc.getTextureManager().bindTexture(ToolTips.SHULKER_GUI_TEXTURE);
            RenderUtil.drawTexturedRect(n, n2, 0, 0, 176, 16, 500);
            RenderUtil.drawTexturedRect(n, n2 + 16, 0, 16, 176, 54 + (int)this.invH.getValue(), 500);
            RenderUtil.drawTexturedRect(n, n2 + 16 + 54, 0, 160, 176, 8, 500);
            GlStateManager.disableDepth();
            Color color = new Color(0, 0, 0, 255);
            if (this.textColor.getValue()) {
                color = new Color((int)this.red.getValue(), (int)this.green.getValue(), (int)this.blue.getValue(), (int)this.alpha.getValue());
            }
            this.renderer.drawStringWithShadow((s == null) ? itemStack.getDisplayName() : s, (float)(n + 8), (float)(n2 + 6), ColorUtil.toRGBA(color));
            GlStateManager.enableDepth();
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableColorMaterial();
            GlStateManager.enableLighting();
            final NonNullList withSize = NonNullList.withSize(27, (Object)ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(getCompoundTag, withSize);
            for (int i = 0; i < withSize.size(); ++i) {
                final int n3 = n + i % 9 * 18 + 8;
                final int n4 = n2 + i / 9 * 18 + 18;
                final ItemStack itemStack2 = (ItemStack)withSize.get(i);
                ToolTips.mc.getRenderItem().zLevel = 501.0f;
                RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack2, n3, n4);
                RenderUtil.itemRender.renderItemOverlayIntoGUI(ToolTips.mc.fontRenderer, itemStack2, n3, n4, (String)null);
                ToolTips.mc.getRenderItem().zLevel = 0.0f;
            }
            GlStateManager.disableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    public int getTextRadarY() {
        return this.textRadarY;
    }
    
    public ToolTips() {
        super("ToolTips", "Several tweaks for tooltips.", Category.MISC, true, false, false);
        this.maps = (Setting<Boolean>)this.register(new Setting("Maps", (Object)true));
        this.shulkers = (Setting<Boolean>)this.register(new Setting("ShulkerViewer", (Object)true));
        this.peek = (Setting<Bind>)this.register(new Setting("Peek", (Object)new Bind(-1)));
        this.shulkerSpy = (Setting<Boolean>)this.register(new Setting("ShulkerSpy", (Object)true));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (Object)Boolean.TRUE, p0 -> (boolean)this.shulkerSpy.getValue()));
        this.own = (Setting<Boolean>)this.register(new Setting("OwnShulker", (Object)Boolean.TRUE, p0 -> (boolean)this.shulkerSpy.getValue()));
        this.cooldown = (Setting<Integer>)this.register(new Setting("ShowForS", (Object)2, (Object)0, (Object)5, p0 -> (boolean)this.shulkerSpy.getValue()));
        this.textColor = (Setting<Boolean>)this.register(new Setting("TextColor", (Object)Boolean.FALSE, p0 -> (boolean)this.shulkers.getValue()));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.textColor.getValue()));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.textColor.getValue()));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (Object)0, (Object)0, (Object)255, p0 -> (boolean)this.textColor.getValue()));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (Object)255, (Object)0, (Object)255, p0 -> (boolean)this.textColor.getValue()));
        this.offsets = (Setting<Boolean>)this.register(new Setting("Offsets", (Object)false));
        this.yPerPlayer = (Setting<Integer>)this.register(new Setting("Y/Player", (Object)18, p0 -> (boolean)this.offsets.getValue()));
        this.xOffset = (Setting<Integer>)this.register(new Setting("XOffset", (Object)4, p0 -> (boolean)this.offsets.getValue()));
        this.yOffset = (Setting<Integer>)this.register(new Setting("YOffset", (Object)2, p0 -> (boolean)this.offsets.getValue()));
        this.trOffset = (Setting<Integer>)this.register(new Setting("TROffset", (Object)2, p0 -> (boolean)this.offsets.getValue()));
        this.invH = (Setting<Integer>)this.register(new Setting("InvH", (Object)3, p0 -> (boolean)this.offsets.getValue()));
        this.spiedPlayers = new ConcurrentHashMap<EntityPlayer, ItemStack>();
        this.playerTimers = new ConcurrentHashMap<EntityPlayer, Timer>();
        this.setInstance();
    }
    
    private void setInstance() {
        ToolTips.INSTANCE = this;
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (fullNullCheck() || !(boolean)this.shulkerSpy.getValue() || !(boolean)this.render.getValue()) {
            return;
        }
        final int n = -4 + (int)this.xOffset.getValue();
        int n2 = 10 + (int)this.yOffset.getValue();
        this.textRadarY = 0;
        for (final EntityPlayer entityPlayer : ToolTips.mc.world.playerEntities) {
            if (this.spiedPlayers.get(entityPlayer) == null) {
                continue;
            }
            entityPlayer.getHeldItemMainhand();
            if (!(entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox)) {
                final Timer timer = this.playerTimers.get(entityPlayer);
                if (timer == null) {
                    final Timer timer2 = new Timer();
                    timer2.reset();
                    this.playerTimers.put(entityPlayer, timer2);
                }
                else if (timer.passedS((double)(int)this.cooldown.getValue())) {
                    continue;
                }
            }
            else {
                final Timer timer3;
                if (entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox && (timer3 = this.playerTimers.get(entityPlayer)) != null) {
                    timer3.reset();
                    this.playerTimers.put(entityPlayer, timer3);
                }
            }
            this.renderShulkerToolTip(this.spiedPlayers.get(entityPlayer), n, n2, entityPlayer.getName());
            this.textRadarY = (n2 += (int)this.yPerPlayer.getValue() + 60) - 10 - (int)this.yOffset.getValue() + (int)this.trOffset.getValue();
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void makeTooltip(final ItemTooltipEvent itemTooltipEvent) {
    }
}
