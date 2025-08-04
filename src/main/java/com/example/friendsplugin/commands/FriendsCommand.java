package com.example.friendsplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.example.friendsplugin.storage.FriendStorage;
import java.util.List;

public class FriendsCommand implements CommandExecutor {

    private final FriendStorage friendStorage;

    public FriendsCommand(FriendStorage friendStorage) {
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

        // Get the player's friends list
        List<String> friends = friendStorage.getFriends(player.getUniqueId());

        // Display the friends list
        player.sendMessage(ChatColor.GOLD + "========== Friends List ==========");
        player.sendMessage(ChatColor.YELLOW + "You have " + ChatColor.GREEN + friends.size() + ChatColor.YELLOW + " friend(s):");

        if (friends.isEmpty()) {
            player.sendMessage(ChatColor.GRAY + "No friends added yet. Use /friend add <username> to add friends!");
        } else {
            for (String friendName : friends) {
                Player friendPlayer = Bukkit.getPlayer(friendName);
                String status = (friendPlayer != null && friendPlayer.isOnline()) ? 
                    ChatColor.GREEN + "[ONLINE]" : ChatColor.RED + "[OFFLINE]";
                player.sendMessage(ChatColor.WHITE + "- " + friendName + " " + status);
            }
        }

        player.sendMessage(ChatColor.GOLD + "================================");

        return true;
    }
}
