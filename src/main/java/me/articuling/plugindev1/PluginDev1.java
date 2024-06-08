package me.articuling.plugindev1;

import me.articuling.plugindev1.commands.gmc;
import me.articuling.plugindev1.commands.pickupCommand;
import me.articuling.plugindev1.listeners.joinListener;
import me.articuling.plugindev1.listeners.leaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginDev1 extends JavaPlugin {


    private static PluginDev1 plugin;
    @Override
    public void onEnable() {

        plugin = this;

        // Plugin startup logic
        getLogger().warning("PluginDev1 by Articuling has loaded!");
        // Config
        saveDefaultConfig();

        // COMMANDS

        getCommand("gmc").setExecutor(new gmc());
        getCommand("pickup").setExecutor(new pickupCommand(this));

        // LISTENERS
        this.getServer().getPluginManager().registerEvents(new joinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new leaveListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getLogger().warning("PluginDev1 by Articuling has been unloaded!");
    }
    public static PluginDev1 getPlugin() {
        return plugin;
    }


}
