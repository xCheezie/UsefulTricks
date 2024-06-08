package me.articuling.plugindev1.commands;

import me.articuling.plugindev1.PluginDev1;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class pickupCommand implements CommandExecutor {

    // GET CONFIG
    private final PluginDev1 plugin;

    public pickupCommand(PluginDev1 plugin) {
        this.plugin = plugin;
        //
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Boolean pickup = this.plugin.getConfig().getBoolean("pickup");
        Player p = (Player) sender;
        PersistentDataContainer data = p.getPersistentDataContainer();
        if (args.length == 0) {
            if (p.getCanPickupItems() == true) {
                p.setCanPickupItems(false);
                p.getPersistentDataContainer().set(new NamespacedKey(PluginDev1.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                p.sendMessage(ChatColor.RED + "You cannot pickup items now!");
            } else {
                p.setCanPickupItems(true);
                p.getPersistentDataContainer().set(new NamespacedKey(PluginDev1.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                p.sendMessage(ChatColor.GREEN + "You can pickup items now!");
            }

        } else {


            String playerName = args[0];
            Player target = Bukkit.getServer().getPlayerExact(playerName);
            String name = p.getName();

            if (playerName.equals("*")) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    if (players.isOnline()) {
                        players.setCanPickupItems(true);
                        if (players.getPersistentDataContainer().get(new NamespacedKey(PluginDev1.getPlugin(), "pickup"),PersistentDataType.BOOLEAN) == true) {
                            players.setCanPickupItems(false);
                            players.sendMessage(ChatColor.RED + name + " has disabled your pickup!");
                            players.getPersistentDataContainer().set(new NamespacedKey(PluginDev1.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                            {
                            }

                        } else {
                            players.setCanPickupItems(true);
                            players.sendMessage(ChatColor.GREEN + name + " has enabled your pickup!");
                            players.getPersistentDataContainer().set(new NamespacedKey(PluginDev1.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                        }

                    }



                    }

                }else if (target != null) {

                if (target.getCanPickupItems() == true) {
                    p.sendMessage(ChatColor.RED + "You have sucessfully disable " + target.getDisplayName() + "'s pickup!");
                    target.getPersistentDataContainer().set(new NamespacedKey(PluginDev1.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, false);
                    target.setCanPickupItems(false);
                    target.sendMessage(ChatColor.RED + name + " has disabled your pickup!");

                } else {

                    if (target.getCanPickupItems() == false) {
                        p.sendMessage(ChatColor.GREEN + "You have sucessfully enabled " + target.getDisplayName() + "'s pickup!");
                        target.getPersistentDataContainer().set(new NamespacedKey(PluginDev1.getPlugin(), "pickup"), PersistentDataType.BOOLEAN, true);
                        target.setCanPickupItems(true);
                        target.sendMessage(ChatColor.GREEN + name + " has enabled your pickup!");


                    }
                }




            }else {
                p.sendMessage("You have not specified a player or the player is offline!");

        }

    }
        return true;
}

}


