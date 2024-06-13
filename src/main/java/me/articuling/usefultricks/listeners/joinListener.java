package me.articuling.usefultricks.listeners;

import me.articuling.usefultricks.UsefulTricks;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;

public class joinListener implements Listener {
    private final UsefulTricks plugin;

    public joinListener(UsefulTricks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Boolean data = p.getPersistentDataContainer().get(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN);
       // String replaced = PlaceholderAPI.setPlaceholders(p, "%player_name%");

            Boolean value = this.plugin.getConfig().getBoolean("default-message");
          //  e.setJoinMessage(ChatColor.translateAlternateColorCodes('&',"&b(&e+&b) &e") + replaced);
        if (value == true) {
            p.sendMessage(ChatColor.GREEN + "Your default pickup value is " + data);
        }
            if (!p.getPersistentDataContainer().has(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN)) {
                p.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);

        }    if (p.getPersistentDataContainer().get(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"),PersistentDataType.BOOLEAN) == true) {

                    p.setCanPickupItems(true);
        }else {

            p.setCanPickupItems(false);
        }

        }

    }

