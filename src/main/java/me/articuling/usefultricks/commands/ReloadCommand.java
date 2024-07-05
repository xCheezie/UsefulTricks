package me.articuling.usefultricks.commands;

import me.articuling.usefultricks.UsefulTricks;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReloadCommand implements TabExecutor {
    private final UsefulTricks plugin;

    public ReloadCommand(UsefulTricks plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("usefultricks.command.reload")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("Reload")) {
                    plugin.reloadConfig();
                    plugin.saveConfig();
                    String reloadCommand = this.plugin.getConfig().getString("reload-message", "&aUsefulTricks has been reloaded!");
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', reloadCommand));
                } else {
                    sender.sendMessage(ChatColor.RED + "You need to specify \"reload\"");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Use \"/usefultricks reload\"");
            }
        } else {
            String noPerm = this.plugin.getConfig().getString("no-permission", "&cYou do not have permission to do that command!");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPerm));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("reload");
        }
        return List.of();
    }
}
