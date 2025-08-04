package com.example.friendsplugin.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FriendStorage {

    private final JavaPlugin plugin;
    private final File friendsFile;
    private FileConfiguration friendsConfig;

    public FriendStorage(JavaPlugin plugin) {
        this.plugin = plugin;
        this.friendsFile = new File(plugin.getDataFolder(), "friends.yml");
        this.loadFriendsFile();
    }

    private void loadFriendsFile() {
        // Create the plugin data folder if it doesn't exist
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        // Create the friends.yml file if it doesn't exist
        if (!friendsFile.exists()) {
            try {
                friendsFile.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Could not create friends.yml file!");
                e.printStackTrace();
            }
        }

        // Load the configuration
        friendsConfig = YamlConfiguration.loadConfiguration(friendsFile);
    }

    public void addFriend(UUID playerUUID, String friendName) {
        String playerKey = playerUUID.toString();
        List<String> friends = friendsConfig.getStringList(playerKey);

        // Add the friend if not already in the list
        if (!friends.contains(friendName)) {
            friends.add(friendName);
            friendsConfig.set(playerKey, friends);
            saveFriendsFile();
        }
    }

    public List<String> getFriends(UUID playerUUID) {
        String playerKey = playerUUID.toString();
        return friendsConfig.getStringList(playerKey);
    }

    public boolean areFriends(UUID playerUUID, String friendName) {
        List<String> friends = getFriends(playerUUID);
        return friends.contains(friendName);
    }

    public void removeFriend(UUID playerUUID, String friendName) {
        String playerKey = playerUUID.toString();
        List<String> friends = friendsConfig.getStringList(playerKey);

        if (friends.remove(friendName)) {
            friendsConfig.set(playerKey, friends);
            saveFriendsFile();
        }
    }

    private void saveFriendsFile() {
        try {
            friendsConfig.save(friendsFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save friends.yml file!");
            e.printStackTrace();
        }
    }
}
