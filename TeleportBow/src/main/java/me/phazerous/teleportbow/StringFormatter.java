package me.phazerous.teleportbow;

import org.bukkit.ChatColor;

public class StringFormatter {
    public static String CodeFormat(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
