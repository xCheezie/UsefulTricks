package me.articuling.usefultricks;

import me.articuling.usefultricks.commands.pickupCommand;
import me.articuling.usefultricks.commands.pickupTabCompletion;
import me.articuling.usefultricks.commands.reloadCommand;
import me.articuling.usefultricks.commands.reloadTabCompleter;
import me.articuling.usefultricks.listeners.joinListener;
import me.articuling.usefultricks.listeners.leaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class UsefulTricks extends JavaPlugin {


    private static UsefulTricks plugin;
    @Override
    public void onEnable() {

        plugin = this;

        // Plugin startup logic
        getLogger().warning("UsefulTricks by Articuling has loaded!");
        // Config
        saveDefaultConfig();

        // COMMANDS

      //  getCommand("gmc").setExecutor(new gmc());
        getCommand("usefultricks").setExecutor(new reloadCommand(this));
        getCommand("pickup").setExecutor(new pickupCommand());
        getCommand("pickup").setTabCompleter(new pickupTabCompletion());
        getCommand("usefultricks").setTabCompleter(new reloadTabCompleter());

        // LISTENERS
        this.getServer().getPluginManager().registerEvents(new joinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new leaveListener(), this);

        // Manager
        int pluginId = 22261;
        Metrics metrics = new Metrics(this, 22261);
        metrics.addCustomChart(new Metrics.SimplePie("servers", () -> "Servers"));
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
