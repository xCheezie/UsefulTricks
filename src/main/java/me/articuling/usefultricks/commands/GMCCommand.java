/*
package me.articuling.usefultricks.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GMCCommand implements CommandExecutor {

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("usefultricks.command.gmc")) {
                if (p.getGameMode() != GameMode.CREATIVE) {
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYour gamemode has been set to creative!"));
                } else {
                    p.sendMessage(ChatColor.RED + "Your gamemode is already creative!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "You do not have permission to do that command!");
            }
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "You can't run this!");
        }
        return true;
    }
}
*/