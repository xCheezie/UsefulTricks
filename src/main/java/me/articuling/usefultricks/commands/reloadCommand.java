package me.articuling.usefultricks.commands;

import me.articuling.usefultricks.UsefulTricks;
import org.bukkit.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class reloadCommand implements CommandExecutor {
    private UsefulTricks plugin;

    public reloadCommand(UsefulTricks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

    if (sender.hasPermission("usefultricks.command.reload")) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("Reload")) {
                plugin.reloadConfig();
                plugin.saveConfig();
                String reloadcommand = this.plugin.getConfig().get("reload-message").toString();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', reloadcommand));


            } else {
                sender.sendMessage(ChatColor.RED + "You need to specify \"reload\"");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Use \"/usefultricks reload\"");
        }


    } else {
        String noperm = this.plugin.getConfig().get("no-permission").toString();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noperm));
    }


        return true;
    }
}
