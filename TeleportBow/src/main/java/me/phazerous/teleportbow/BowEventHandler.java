package me.phazerous.teleportbow;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class BowEventHandler implements Listener {

    private final Main plugin;
    private final BowFabric fabric;

    public BowEventHandler(Main plugin, BowFabric fabric) {
        this.plugin = plugin;
        this.fabric = fabric;
    }

    @EventHandler
    public void OnBowShoot(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        if (!e.getBow().getItemMeta().getDisplayName().equals(StringFormatter.CodeFormat(plugin.getConfig().getString("bow-name"))))
            return;

        Arrow arrow = (Arrow) e.getProjectile();


        arrow.setMetadata("type", new FixedMetadataValue(plugin, "TeleportBowArrow"));
    }

    @EventHandler
    public void OnArrowHit(ProjectileHitEvent e) {
        if (!(e.getEntity() instanceof Arrow)) return;

        Arrow arrow = (Arrow) e.getEntity();
        Player player = (Player) arrow.getShooter();

        if (!arrow.getMetadata("type").get(0).value().toString().equals("TeleportBowArrow")) return;

        Location location = arrow.getLocation();
        location.setYaw(player.getLocation().getYaw());
        location.setPitch(player.getLocation().getPitch());

        e.getEntity().remove();
        player.teleport(location);
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e) {
        if (!plugin.getConfig().getBoolean("bow-on-join")) return;

        Player player = e.getPlayer();
        if (!fabric.CheckIfHasTeleportBow(player)) fabric.GiveTeleportBow(player);
        if (!fabric.CheckIfHasArrow(player)) fabric.GiveArrow(player);
    }
}

