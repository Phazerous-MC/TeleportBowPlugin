package me.phazerous.teleportbow;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private BowFabric fabric;

    @Override
    public void onEnable() {
        CreateBowFabric();

        SetCommands();
        SetEventHandler();

        System.out.println("[TeleportBowPlugin]: ACTIVATED.");
    }

    private void SetCommands() {
        getServer().getPluginCommand("teleportbow").setExecutor(new TeleportBowCommand(this, fabric));
    }

    private void SetEventHandler() {
        getServer().getPluginManager().registerEvents(new BowEventHandler(this, fabric), this);
    }

    private void CreateBowFabric() {
        fabric = new BowFabric(this);
    }
}
