//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.impl.modules.client;

import me.snow.luigihack.impl.modules.*;
import me.snow.luigihack.api.setting.*;
import net.minecraft.client.*;
import me.snow.luigihack.*;
import java.text.*;
import net.minecraft.potion.*;
import me.snow.luigihack.api.util.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class StreamerMode extends Module
{
    private /* synthetic */ SecondScreenFrame window;
    public /* synthetic */ Setting<Integer> width;
    public /* synthetic */ Setting<Integer> height;
    
    @Override
    public void onLoad() {
        this.disable();
    }
    
    public StreamerMode() {
        super("StreamerMode", "Displays client info in a second window.", Category.CLIENT, false, false, false);
        this.width = (Setting<Integer>)this.register(new Setting("Width", (Object)600, (Object)100, (Object)3160));
        this.height = (Setting<Integer>)this.register(new Setting("Height", (Object)900, (Object)100, (Object)2140));
    }
    
    @Override
    public void onEnable() {
        EventQueue.invokeLater(() -> {
            if (this.window == null) {
                this.window = new SecondScreenFrame();
            }
            this.window.setVisible(true);
        });
    }
    
    @Override
    public void onDisable() {
        if (this.window != null) {
            this.window.setVisible(false);
        }
        this.window = null;
    }
    
    @Override
    public void onUpdate() {
        if (this.window != null) {
            final ArrayList<String> toDraw = new ArrayList<String>();
            toDraw.add("LuigiHack - 1.8.3");
            toDraw.add("");
            toDraw.add(String.valueOf(new StringBuilder().append("Fps: ").append(Minecraft.debugFPS)));
            toDraw.add(String.valueOf(new StringBuilder().append("TPS: ").append(LuigiHack.serverManager.getTPS())));
            toDraw.add(String.valueOf(new StringBuilder().append("Ping: ").append(LuigiHack.serverManager.getPing()).append("ms")));
            toDraw.add(String.valueOf(new StringBuilder().append("Speed: ").append(LuigiHack.speedManager.getSpeedKpH()).append("km/h")));
            toDraw.add(String.valueOf(new StringBuilder().append("Time: ").append(new SimpleDateFormat("h:mm a").format(new Date()))));
            final boolean equals = StreamerMode.mc.world.getBiome(StreamerMode.mc.player.getPosition()).getBiomeName().equals("Hell");
            final int i = (int)StreamerMode.mc.player.posX;
            final int j = (int)StreamerMode.mc.player.posY;
            final int k = (int)StreamerMode.mc.player.posZ;
            final float n = equals ? 8.0f : 0.125f;
            final String value = String.valueOf(new StringBuilder().append("XYZ ").append(i).append(", ").append(j).append(", ").append(k).append(" [").append((int)(StreamerMode.mc.player.posX * n)).append(", ").append((int)(StreamerMode.mc.player.posZ * n)).append("]"));
            final String direction4D = LuigiHack.rotationManager.getDirection4D(false);
            toDraw.add("");
            toDraw.add(direction4D);
            toDraw.add(value);
            toDraw.add("");
            final Iterator<Module> iterator = (Iterator<Module>)LuigiHack.moduleManager.sortedModules.iterator();
            while (iterator.hasNext()) {
                toDraw.add(TextUtil.stripColor(iterator.next().getFullArrayString()));
            }
            toDraw.add("");
            final Iterator<PotionEffect> iterator2 = (Iterator<PotionEffect>)LuigiHack.potionManager.getOwnPotions().iterator();
            while (iterator2.hasNext()) {
                toDraw.add(TextUtil.stripColor(LuigiHack.potionManager.getColoredPotionString((PotionEffect)iterator2.next())));
            }
            toDraw.add("");
            final Map textRadarPlayers = EntityUtil.getTextRadarPlayers();
            if (!textRadarPlayers.isEmpty()) {
                final Iterator<Map.Entry<String, V>> iterator3 = textRadarPlayers.entrySet().iterator();
                while (iterator3.hasNext()) {
                    toDraw.add(TextUtil.stripColor((String)iterator3.next().getKey()));
                }
            }
            this.window.setToDraw(toDraw);
        }
    }
    
    @Override
    public void onUnload() {
        this.disable();
    }
    
    @Override
    public void onLogout() {
        if (this.window != null) {
            final ArrayList<String> toDraw = new ArrayList<String>();
            toDraw.add("LuigiHack - 1.8.3");
            toDraw.add("");
            toDraw.add("No Connection.");
            this.window.setToDraw(toDraw);
        }
    }
    
    public class SecondScreenFrame extends JFrame
    {
        private /* synthetic */ SecondScreen panel;
        
        public SecondScreenFrame() {
            this.initUI();
        }
        
        public void setToDraw(final ArrayList<String> toDraw) {
            this.panel.setToDraw(toDraw);
        }
        
        private void initUI() {
            this.panel = new SecondScreen();
            this.add(this.panel);
            this.setResizable(true);
            this.pack();
            this.setTitle("LuigiHack - Info");
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(2);
        }
    }
    
    public class SecondScreen extends JPanel
    {
        private /* synthetic */ Font font;
        private /* synthetic */ ArrayList<String> toDraw;
        private final /* synthetic */ int B_WIDTH;
        private final /* synthetic */ int B_HEIGHT;
        
        public SecondScreen() {
            this.B_WIDTH = (int)StreamerMode.this.width.getValue();
            this.B_HEIGHT = (int)StreamerMode.this.height.getValue();
            this.font = new Font("Verdana", 0, 20);
            this.toDraw = new ArrayList<String>();
            this.initBoard();
        }
        
        public void paintComponent(final Graphics g) {
            super.paintComponent(g);
            this.drawScreen(g);
        }
        
        public void setWindowSize(final int width, final int height) {
            this.setPreferredSize(new Dimension(width, height));
        }
        
        private void drawScreen(final Graphics graphics) {
            final Font font = this.font;
            final FontMetrics fontMetrics = this.getFontMetrics(font);
            graphics.setColor(Color.white);
            graphics.setFont(font);
            int n = 40;
            for (final String str : this.toDraw) {
                graphics.drawString(str, (this.getWidth() - fontMetrics.stringWidth(str)) / 2, n);
                n += 20;
            }
            Toolkit.getDefaultToolkit().sync();
        }
        
        public void setToDraw(final ArrayList<String> toDraw) {
            this.toDraw = toDraw;
            this.repaint();
        }
        
        private void initBoard() {
            this.setBackground(Color.black);
            this.setFocusable(true);
            this.setPreferredSize(new Dimension(this.B_WIDTH, this.B_HEIGHT));
        }
        
        @Override
        public void setFont(final Font font) {
            this.font = font;
        }
    }
}
