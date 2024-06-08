package me.articuling.plugindev1.listeners;

import me.articuling.plugindev1.PluginDev1;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class joinListener implements Listener {
    private final PluginDev1 plugin;

    public joinListener(PluginDev1 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Boolean data = p.getPersistentDataContainer().get(new NamespacedKey(PluginDev1.getPlugin(), "pickup"), PersistentDataType.BOOLEAN);
        String replaced = PlaceholderAPI.setPlaceholders(p, "%player_name%");


            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&',"&b(&e+&b) &e") + replaced);
            p.sendMessage(ChatColor.GREEN + "Your default pickup value is " + data);

            if (!p.getPersistentDataContainer().has(new NamespacedKey(PluginDev1.getPlugin(), "pickup"), PersistentDataType.BOOLEAN)) {
                p.getPersistentDataContainer().set(new NamespacedKey(PluginDev1.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);

        }    if (p.getPersistentDataContainer().get(new NamespacedKey(PluginDev1.getPlugin(), "pickup"),PersistentDataType.BOOLEAN) == true) {

                    p.setCanPickupItems(true);
        }else {

            p.setCanPickupItems(false);
        }

        }

    }

