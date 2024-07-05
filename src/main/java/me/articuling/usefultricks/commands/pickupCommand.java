package me.articuling.usefultricks.commands;

import me.articuling.usefultricks.UsefulTricks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class pickupCommand implements CommandExecutor {
    private UsefulTricks plugin;

    public pickupCommand(UsefulTricks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String gotdisabled = this.plugin.getConfig().get("got-disabled").toString();
        String madedisabled = this.plugin.getConfig().get("made-disabled").toString();

        String gotenabled = this.plugin.getConfig().get("got-enabled").toString();
        String madeenabled = this.plugin.getConfig().get("made-enabled").toString();

        String targetenabled = this.plugin.getConfig().get("target-enabled").toString();
        String targetdisabled = this.plugin.getConfig().get("target-disabled").toString();

        String canpickup = this.plugin.getConfig().get("can-pickup").toString();
        String cannotpickup = this.plugin.getConfig().get("cannot-pickup").toString();

        String playeroffline = this.plugin.getConfig().get("player-offline").toString();

        String selfconsolepickup = this.plugin.getConfig().get("self-pickup-console").toString();
        String notplayer = this.plugin.getConfig().get("not-console-or-player").toString();

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("usefultricks.command.pickup")) {
                PersistentDataContainer data = p.getPersistentDataContainer();
                if (args.length == 0) {
                    if (p.getCanPickupItems()) {
                        p.setCanPickupItems(false);
                        p.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',cannotpickup));
                    } else {
                        p.setCanPickupItems(true);
                        p.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',canpickup));
                    }
                } else {
                    String playerName = args[0];
                    Player target = Bukkit.getServer().getPlayerExact(playerName);
                    String name = p.getName();

                    if (playerName.equals("*")) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (players.isOnline()) {
                                if (Boolean.TRUE.equals(players.getPersistentDataContainer().get(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN))) {
                                    players.setCanPickupItems(false);
                                    players.sendMessage(ChatColor.translateAlternateColorCodes('&', gotdisabled.replaceAll("%player%", p.getDisplayName())));
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', madedisabled));
                                    players.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                                } else {
                                    players.setCanPickupItems(true);
                                    players.sendMessage(ChatColor.translateAlternateColorCodes('&', gotenabled.replaceAll("%player%", p.getDisplayName())));
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', madeenabled));
                                    players.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                                }
                            }
                        }
                    } else if (target != null) {
                        if (target.getCanPickupItems()) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', targetdisabled.replaceAll("%player%", target.getDisplayName())));
                            target.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                            target.setCanPickupItems(false);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', gotdisabled.replaceAll("%player%", p.getDisplayName())));
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', targetenabled.replaceAll("%player%", target.getDisplayName())));
                            target.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                            target.setCanPickupItems(true);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', gotenabled.replaceAll("%player%", p.getDisplayName())));
                        }
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', playeroffline));
                    }
                }
            } else {
                String noperm = plugin.getConfig().get("no-permission").toString();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', noperm));
            }
        } else if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            if (args.length > 0) {
                String playerName = args[0];
                String name = console.getName();

                if (playerName.equals("*")) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (players.isOnline()) {
                            if (Boolean.TRUE.equals(players.getPersistentDataContainer().get(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN))) {
                                players.setCanPickupItems(false);
                                players.sendMessage(ChatColor.translateAlternateColorCodes('&', gotdisabled.replaceAll("%player%", console.getName())));
                                console.sendMessage(ChatColor.translateAlternateColorCodes('&', madedisabled.replaceAll("%player%", console.getName())));
                                players.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                            } else {
                                players.setCanPickupItems(true);
                                players.sendMessage(ChatColor.translateAlternateColorCodes('&', gotenabled.replaceAll("%player%", console.getName())));
                                console.sendMessage(ChatColor.translateAlternateColorCodes('&', madeenabled.replaceAll("%player%", console.getName())));
                                players.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                            }
                        }
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayerExact(playerName);
                    if (target != null) {
                        if (target.getCanPickupItems()) {
                            console.sendMessage(ChatColor.translateAlternateColorCodes('&', targetdisabled.replaceAll("%player%", target.getDisplayName())));
                            target.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                            target.setCanPickupItems(false);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', gotdisabled.replaceAll("%player%", console.getName())));
                        } else {
                            console.sendMessage(ChatColor.translateAlternateColorCodes('&', targetenabled.replaceAll("%player%", target.getDisplayName())));
                            target.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                            target.setCanPickupItems(true);
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', gotenabled.replaceAll("%player%", console.getName())));
                        }
                    } else {
                        console.sendMessage(ChatColor.translateAlternateColorCodes('&', playeroffline));
                    }
                }
            } else {
                console.sendMessage(ChatColor.translateAlternateColorCodes('&', selfconsolepickup));
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', notplayer));
        }
        return true;
    }
}
