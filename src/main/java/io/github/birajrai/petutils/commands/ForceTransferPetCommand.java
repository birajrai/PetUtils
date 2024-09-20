package io.github.birajrai.petutils.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;

public class ForceTransferPetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("petutils.ft") && !player.isOp()) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "Usage: /forcetransferpet {playername}");
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null) {
            player.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        // Get the entity the player is looking at
        Entity entity = player.getTargetEntity(5); // Get the entity the player is looking at within 5 blocks
        if (entity == null || !(entity instanceof Tameable)) {
            player.sendMessage(ChatColor.RED + "You are not looking at a tamed pet.");
            return true;
        }

        Tameable pet = (Tameable) entity;

        // Transfer pet ownership without checking current ownership
        pet.setOwner(targetPlayer);

        // Notify both players
        player.sendMessage(ChatColor.GREEN + "You have forcefully transferred the pet to " + targetPlayer.getName() + ".");
        targetPlayer.sendMessage(ChatColor.GREEN + player.getName() + " has forcefully transferred a pet to you.");

        return true;
    }
}
