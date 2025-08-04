package com.example.friendsplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.example.friendsplugin.storage.FriendStorage;

public class FriendCommand implements CommandExecutor {

    private final FriendStorage friendStorage;

    public FriendCommand(FriendStorage friendStorage) {
        this.friendStorage = friendStorage;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Check if sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        // Check if the command has the correct arguments
        if (args.length != 2 || !args[0].equalsIgnoreCase("add")) {
            player.sendMessage(ChatColor.RED + "Usage: /friend add <username>");
            return true;
        }

        String friendName = args[1];

        // Check if the player is trying to add themselves
        if (friendName.equalsIgnoreCase(player.getName())) {
            player.sendMessage(ChatColor.RED + "You cannot add yourself as a friend!");
            return true;
        }

        // Check if the target player exists (has played before)
        Player targetPlayer = Bukkit.getPlayer(friendName);
        if (targetPlayer == null && Bukkit.getOfflinePlayer(friendName).getLastPlayed() == 0) {
            player.sendMessage(ChatColor.RED + "Player '" + friendName + "' has never played on this server!");
            return true;
        }

        // Check if the player is already friends with the target
        if (friendStorage.areFriends(player.getUniqueId(), friendName)) {
            player.sendMessage(ChatColor.YELLOW + "You are already friends with " + friendName + "!");
            return true;
        }

        // Add the friend
        friendStorage.addFriend(player.getUniqueId(), friendName);
        player.sendMessage(ChatColor.GREEN + "Successfully added " + friendName + " to your friends list!");

        return true;
    }
}
