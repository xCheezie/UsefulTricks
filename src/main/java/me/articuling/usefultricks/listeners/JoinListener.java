package me.articuling.usefultricks.listeners;

import me.articuling.usefultricks.UsefulTricks;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;

public class JoinListener implements Listener {
    private final UsefulTricks plugin;

    public JoinListener(UsefulTricks plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Boolean data = p.getPersistentDataContainer().get(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN);
        boolean defaultMessage = this.plugin.getConfig().getBoolean("default-message", false);
         /*
         String replaced = PlaceholderAPI.setPlaceholders(p, "%player_name%");
         e.setJoinMessage("");
         e.setJoinMessage(ChatColor.translateAlternateColorCodes('&',"&b(&e+&b) &e") + replaced);
         */
        if (defaultMessage) {
            p.sendMessage(ChatColor.GREEN + "Your default pickup value is " + data);
        }
        Boolean pickupBool = p.getPersistentDataContainer().get(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN);
        if (pickupBool != null) {
            p.getPersistentDataContainer().set(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN, true);
            p.setCanPickupItems(pickupBool);
        }
    }

}

