package me.articuling.usefultricks.commands;

import me.articuling.usefultricks.UsefulTricks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PickupCommand implements TabExecutor {
    private final UsefulTricks plugin;

    public PickupCommand(UsefulTricks plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String gotDisabled = this.plugin.getConfig().getString("got-disabled", "&c%player% has disabled your pickup!");
        String madeDisabled = this.plugin.getConfig().getString("made-disabled", "&cYou have successfully disabled all player's pickup!");

        String gotEnabled = this.plugin.getConfig().getString("got-enabled", "&a%player% has enabled your pickup!");
        String madeEnabled = this.plugin.getConfig().getString("made-enabled", "&aYou have successfully enabled all player's pickup!");

        String targetEnabled = this.plugin.getConfig().getString("target-enabled", "&aYou have successfully enabled %player%'s pickup!");
        String targetDisabled = this.plugin.getConfig().getString("target-disabled", "&cYou have successfully disabled %player%'s pickup!");

        String canPickup = this.plugin.getConfig().getString("can-pickup", "&aYou can pickup items now!");
        String cannotPickup = this.plugin.getConfig().getString("cannot-pickup", "&cYou cannot pickup items now!");

        String playerOffline = this.plugin.getConfig().getString("player-offline", "&cYou have not specified a player or the player is offline!");

        String selfConsolePickup = this.plugin.getConfig().getString("self-pickup-console", "&cYou cannot do that command!");
        String notPlayer = this.plugin.getConfig().getString("not-console-or-player", "&cThis command can only be run by a player or console!");

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("usefultricks.command.pickup")) {
                PersistentDataContainer data = p.getPersistentDataContainer();
                if (args.length == 0) {
                    if (p.getCanPickupItems()) {
                        p.setCanPickupItems(false);
                        data.set(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN, false);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',cannotPickup));
                    } else {
                        p.setCanPickupItems(true);
                        data.set(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN, true);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',canPickup));
                    }
                } else {
                    String playerName = args[0];
                    Player target = Bukkit.getServer().getPlayerExact(playerName);
                    if (playerName.equals("*")) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (players.isOnline()) {
                                if (Boolean.TRUE.equals(players.getPersistentDataContainer().get(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN))) {
                                    players.setCanPickupItems(false);
                                    players.sendMessage(ChatColor.translateAlternateColorCodes('&', gotDisabled.replaceAll("%player%", p.getDisplayName())));
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', madeDisabled));
                                    players.getPersistentDataContainer().set(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN, false);
                                } else {
                                    players.setCanPickupItems(true);
                                    players.sendMessage(ChatColor.translateAlternateColorCodes('&', gotEnabled.replaceAll("%player%", p.getDisplayName())));
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', madeEnabled));
                                    players.getPersistentDataContainer().set(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN, true);
                                }
                            }
                        }
                    } else if (target != null) {
                        if (target.getCanPickupItems()) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', targetDisabled.replaceAll("%player%", target.getDisplayName())));
                            target.getPersistentDataContainer().set(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN, false);
                            target.setCanPickupItems(false);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', gotDisabled.replaceAll("%player%", p.getDisplayName())));
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', targetEnabled.replaceAll("%player%", target.getDisplayName())));
                            target.getPersistentDataContainer().set(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN, true);
                            target.setCanPickupItems(true);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', gotEnabled.replaceAll("%player%", p.getDisplayName())));
                        }
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', playerOffline));
                    }
                }
            } else {
                String noperm = plugin.getConfig().getString("no-permission", "&cYou do not have permission to do that command!");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', noperm));
            }
        } else if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            if (args.length > 0) {
                String playerName = args[0];
                if (playerName.equals("*")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (Boolean.TRUE.equals(player.getPersistentDataContainer().get(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN))) {
                            player.setCanPickupItems(false);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', gotDisabled.replaceAll("%player%", console.getName())));
                            console.sendMessage(ChatColor.translateAlternateColorCodes('&', madeDisabled.replaceAll("%player%", console.getName())));
                            player.getPersistentDataContainer().set(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN, false);
                        } else {
                            player.setCanPickupItems(true);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', gotEnabled.replaceAll("%player%", console.getName())));
                            console.sendMessage(ChatColor.translateAlternateColorCodes('&', madeEnabled.replaceAll("%player%", console.getName())));
                            player.getPersistentDataContainer().set(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN, true);
                        }
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayerExact(playerName);
                    if (target != null) {
                        if (target.getCanPickupItems()) {
                            console.sendMessage(ChatColor.translateAlternateColorCodes('&', targetDisabled.replaceAll("%player%", target.getDisplayName())));
                            target.getPersistentDataContainer().set(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN, false);
                            target.setCanPickupItems(false);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', gotDisabled.replaceAll("%player%", console.getName())));
                        } else {
                            console.sendMessage(ChatColor.translateAlternateColorCodes('&', targetEnabled.replaceAll("%player%", target.getDisplayName())));
                            target.getPersistentDataContainer().set(plugin.pickupNamespacedKey, PersistentDataType.BOOLEAN, true);
                            target.setCanPickupItems(true);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', gotEnabled.replaceAll("%player%", console.getName())));
                        }
                    } else {
                        console.sendMessage(ChatColor.translateAlternateColorCodes('&', playerOffline));
                    }
                }
            } else {
                console.sendMessage(ChatColor.translateAlternateColorCodes('&', selfConsolePickup));
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', notPlayer));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> players = new ArrayList<>(Bukkit.getServer().getOnlinePlayers().stream().map(Player::getName).toList());
            players.add("*");
            return players;
        }
        return List.of();
    }
}
