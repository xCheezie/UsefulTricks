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
    private final UsefulTricks plugin;

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
                sender.sendMessage(ChatColor.GREEN + "UsefulTricks has been reloaded!");


            } else {
                sender.sendMessage(ChatColor.RED + "You need to specify \"reload\"");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Use \"/usefultricks reload\"");
        }


    } else {
        sender.sendMessage(ChatColor.RED + "You do not have permission to do that command!");
    }


        return true;
    }
}
