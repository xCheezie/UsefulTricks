package me.articuling.usefultricks;

import me.articuling.usefultricks.commands.PickupCommand;
import me.articuling.usefultricks.commands.ReloadCommand;
import me.articuling.usefultricks.listeners.JoinListener;
import me.articuling.usefultricks.listeners.LeaveListener;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class UsefulTricks extends JavaPlugin {

    private static UsefulTricks plugin;
    public NamespacedKey pickupNamespacedKey;

    @Override
    public void onEnable() {
        plugin = this;

        // Plugin startup logic
        getLogger().info("UsefulTricks by Articuling has loaded!");

        // Config
        saveDefaultConfig();

        // COMMANDS

        // getCommand("gmc").setExecutor(new GMCCommand());
        getCommand("usefultricks").setExecutor(new ReloadCommand(this));
        getCommand("pickup").setExecutor(new PickupCommand(this));
        getCommand("pickup").setTabCompleter(new PickupCommand(this));
        getCommand("usefultricks").setTabCompleter(new ReloadCommand(this));

        // LISTENERS
        this.getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new LeaveListener(), this);

        // Manager
        Metrics metrics = new Metrics(this, 22261);
        metrics.addCustomChart(new Metrics.SimplePie("servers", () -> "Servers"));

        plugin.pickupNamespacedKey = new NamespacedKey(UsefulTricks.getPlugin(), "pickup");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().warning("UsefulTricks by Articuling has been unloaded!");
    }

    public static UsefulTricks getPlugin() {
        return plugin;
    }


}
