package me.articuling.plugindev1.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.EventListener;

public class leaveListener implements Listener {

    @EventHandler
    public void OnPlayerLeave(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        String replaced = PlaceholderAPI.setPlaceholders(p, "%player_name%");
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&',"&b(&e-&b) &e") + replaced);

    }
}
