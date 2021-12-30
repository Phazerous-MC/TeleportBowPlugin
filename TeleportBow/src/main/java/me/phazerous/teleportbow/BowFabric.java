package me.phazerous.teleportbow;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BowFabric {

    private final Main plugin;

    public BowFabric(Main plugin) {
        this.plugin = plugin;
    }

    public ItemStack CreateTeleportBow() {
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);

        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName(StringFormatter.CodeFormat(plugin.getConfig().getString("bow-name")));
        meta.setUnbreakable(true);

        ArrayList<String> lore = new ArrayList<>();
        lore.add(StringFormatter.CodeFormat(plugin.getConfig().getString("bow-description")));

        meta.setLore(lore);
        bow.setItemMeta(meta);

        return bow;
    }

    public ItemStack CreateArrow() {
        ItemStack arrow = new ItemStack(Material.ARROW);

        return arrow;
    }

    public void GiveTeleportBow(Player player) {
        player.getInventory().addItem(CreateTeleportBow());
    }

    public void GiveArrow(Player player) {
        player.getInventory().setItem(17, CreateArrow());
    }

    public void GiveTeleportSet(Player player) {
        GiveTeleportBow(player);

        if (!player.getInventory().contains(CreateArrow())) GiveArrow(player);
    }

    public boolean CheckIfHasArrow(Player player) {
        ItemStack arrow = new ItemStack(Material.ARROW);

        return player.getInventory().contains(arrow);
    }

    public boolean CheckIfHasTeleportBow(Player player) {
        return player.getInventory().contains(CreateTeleportBow());
    }
}
