package me.phazerous.teleportbow;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportBowCommand implements CommandExecutor {

    private final Main plugin;
    private final BowFabric fabric;

    public TeleportBowCommand(Main plugin, BowFabric fabric) {
        this.plugin = plugin;
        this.fabric = fabric;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
        }
        Player player = (Player) sender;

        fabric.GiveTeleportBow(player);

        if (!fabric.CheckIfHasArrow(player)) fabric.GiveArrow(player);

        player.sendMessage(StringFormatter.CodeFormat(plugin.getConfig().getString("plugin-prefix") + " " + plugin.getConfig().getString("bow-added-message")));

        return true;
    }
}
