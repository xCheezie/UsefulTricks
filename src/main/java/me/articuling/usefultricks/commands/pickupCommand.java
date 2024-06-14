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

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("usefultricks.command.pickup")) {
                PersistentDataContainer data = p.getPersistentDataContainer();
                if (args.length == 0) {
                    if (p.getCanPickupItems()) {
                        p.setCanPickupItems(false);
                        p.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                        p.sendMessage(ChatColor.RED + "You cannot pickup items now!");
                    } else {
                        p.setCanPickupItems(true);
                        p.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                        p.sendMessage(ChatColor.GREEN + "You can pickup items now!");
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
                                    players.sendMessage(ChatColor.RED + name + " has disabled your pickup!");
                                    players.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                                } else {
                                    players.setCanPickupItems(true);
                                    players.sendMessage(ChatColor.GREEN + name + " has enabled your pickup!");
                                    players.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                                }
                            }
                        }
                    } else if (target != null) {
                        if (target.getCanPickupItems()) {
                            p.sendMessage(ChatColor.RED + "You have successfully disabled " + target.getDisplayName() + "'s pickup!");
                            target.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                            target.setCanPickupItems(false);
                            target.sendMessage(ChatColor.RED + name + " has disabled your pickup!");
                        } else {
                            p.sendMessage(ChatColor.GREEN + "You have successfully enabled " + target.getDisplayName() + "'s pickup!");
                            target.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                            target.setCanPickupItems(true);
                            target.sendMessage(ChatColor.GREEN + name + " has enabled your pickup!");
                        }
                    } else {
                        p.sendMessage("You have not specified a player or the player is offline!");
                    }
                }
            } else {
                p.sendMessage(ChatColor.RED + "You do not have permission to do that command!");
            }
        } else if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender c = (ConsoleCommandSender) sender;
            if (args.length > 0) {
                String playerName = args[0];
                String name = c.getName();

                if (playerName.equals("*")) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (players.isOnline()) {
                            if (Boolean.TRUE.equals(players.getPersistentDataContainer().get(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN))) {
                                players.setCanPickupItems(false);
                                players.sendMessage(ChatColor.RED + name + " has disabled your pickup!");
                                c.sendMessage(ChatColor.RED + "You have successfully disabled all player's pickup!");
                                players.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                            } else {
                                players.setCanPickupItems(true);
                                players.sendMessage(ChatColor.GREEN + name + " has enabled your pickup!");
                                c.sendMessage(ChatColor.GREEN + "You have successfully enabled all player's pickup!");
                                players.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                            }
                        }
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayerExact(playerName);
                    if (target != null) {
                        if (target.getCanPickupItems()) {
                            c.sendMessage(ChatColor.RED + "You have successfully disabled " + target.getDisplayName() + "'s pickup!");
                            target.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                            target.setCanPickupItems(false);
                            target.sendMessage(ChatColor.RED + name + " has disabled your pickup!");
                        } else {
                            c.sendMessage(ChatColor.GREEN + "You have successfully enabled " + target.getDisplayName() + "'s pickup!");
                            target.getPersistentDataContainer().set(new NamespacedKey(UsefulTricks.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                            target.setCanPickupItems(true);
                            target.sendMessage(ChatColor.GREEN + name + " has enabled your pickup!");
                        }
                    } else {
                        c.sendMessage(ChatColor.RED + "You have not specified a player or the player is offline!");
                    }
                }
            } else {
                c.sendMessage(ChatColor.RED + "You cannot do that command!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "This command can only be run by a player or console!");
        }
        return true;
    }
}
