//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import java.util.regex.*;
import java.util.*;

public class TextUtil
{
    private static final /* synthetic */ Pattern STRIP_COLOR_PATTERN;
    public static /* synthetic */ String shrug;
    public static /* synthetic */ String disability;
    
    static {
        BLUE = "§9";
        WHITE = "§f";
        BLACK = "§0";
        pword = "  \u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\n \u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\n \u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2592\n \u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\n \u2588\u2592\u2592\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2592\u2592\u2588\n \u2588\u2592\u2592\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\n \u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592";
        GRAY = "§7";
        BOLD = "§l";
        STRIKE = "§m";
        line1 = " \u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588";
        OBFUSCATED = "§k";
        GREEN = "§a";
        DARK_BLUE = "§1";
        ITALIC = "§o";
        blank = " \u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592\u2592";
        UNDERLINE = "§n";
        GOLD = "§6";
        line4 = " \u2588\u2592\u2592\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2592\u2592\u2588";
        SECTIONSIGN = "§";
        line3 = " \u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588";
        AQUA = "§b";
        RED = "§c";
        DARK_AQUA = "§3";
        DARK_RED = "§4";
        line2 = " \u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2588\u2592\u2592";
        RESET = "§r";
        DARK_GRAY = "§8";
        line5 = " \u2588\u2592\u2592\u2592\u2588\u2592\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588\u2592\u2588\u2588\u2588";
        RAINBOW = "§+";
        YELLOW = "§e";
        DARK_GREEN = "§2";
        LIGHT_PURPLE = "§d";
        DARK_PURPLE = "§5";
        STRIP_COLOR_PATTERN = Pattern.compile("(?i)§[0-9A-FK-OR]");
        rand = new Random();
        TextUtil.shrug = "¯\\_(\u30c4)_/¯";
        TextUtil.disability = "\u267f";
    }
    
    public static String stripColor(final String input) {
        if (input == null) {
            return null;
        }
        return TextUtil.STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }
    
    public static String coloredString(final String s, final Color color) {
        String s2 = s;
        switch (color) {
            case AQUA: {
                s2 = String.valueOf(new StringBuilder().append("§b").append(s2).append("§r"));
                break;
            }
            case WHITE: {
                s2 = String.valueOf(new StringBuilder().append("§f").append(s2).append("§r"));
                break;
            }
            case BLACK: {
                s2 = String.valueOf(new StringBuilder().append("§0").append(s2).append("§r"));
                break;
            }
            case DARK_BLUE: {
                s2 = String.valueOf(new StringBuilder().append("§1").append(s2).append("§r"));
                break;
            }
            case DARK_GREEN: {
                s2 = String.valueOf(new StringBuilder().append("§2").append(s2).append("§r"));
                break;
            }
            case DARK_AQUA: {
                s2 = String.valueOf(new StringBuilder().append("§3").append(s2).append("§r"));
                break;
            }
            case DARK_RED: {
                s2 = String.valueOf(new StringBuilder().append("§4").append(s2).append("§r"));
                break;
            }
            case DARK_PURPLE: {
                s2 = String.valueOf(new StringBuilder().append("§5").append(s2).append("§r"));
                break;
            }
            case GOLD: {
                s2 = String.valueOf(new StringBuilder().append("§6").append(s2).append("§r"));
                break;
            }
            case DARK_GRAY: {
                s2 = String.valueOf(new StringBuilder().append("§8").append(s2).append("§r"));
                break;
            }
            case GRAY: {
                s2 = String.valueOf(new StringBuilder().append("§7").append(s2).append("§r"));
                break;
            }
            case BLUE: {
                s2 = String.valueOf(new StringBuilder().append("§9").append(s2).append("§r"));
                break;
            }
            case RED: {
                s2 = String.valueOf(new StringBuilder().append("§c").append(s2).append("§r"));
                break;
            }
            case GREEN: {
                s2 = String.valueOf(new StringBuilder().append("§a").append(s2).append("§r"));
                break;
            }
            case LIGHT_PURPLE: {
                s2 = String.valueOf(new StringBuilder().append("§d").append(s2).append("§r"));
                break;
            }
            case YELLOW: {
                s2 = String.valueOf(new StringBuilder().append("§e").append(s2).append("§r"));
                break;
            }
        }
        return s2;
    }
    
    public static String cropMaxLengthMessage(final String s, final int n) {
        String substring = "";
        if (s.length() >= 256 - n) {
            substring = s.substring(0, 256 - n);
        }
        return substring;
    }
    
    public enum Color
    {
        DARK_GREEN, 
        DARK_PURPLE, 
        BLACK, 
        WHITE, 
        LIGHT_PURPLE, 
        DARK_RED, 
        GOLD, 
        NONE, 
        BLUE, 
        GREEN, 
        RED, 
        DARK_AQUA, 
        DARK_BLUE, 
        AQUA, 
        GRAY, 
        DARK_GRAY, 
        YELLOW;
    }
}
