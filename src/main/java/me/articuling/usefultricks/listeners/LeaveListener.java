package me.articuling.usefultricks.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        /*
        Player p = e.getPlayer();
        String replaced = PlaceholderAPI.setPlaceholders(p, "%player_name%");
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&',"&b(&e-&b) &e") + replaced);
         */
    }
}
