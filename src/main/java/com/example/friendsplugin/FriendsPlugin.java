package com.example.friendsplugin;

import org.bukkit.plugin.java.JavaPlugin;
import com.example.friendsplugin.commands.FriendCommand;
import com.example.friendsplugin.commands.FriendsCommand;
import com.example.friendsplugin.storage.FriendStorage;

public class FriendsPlugin extends JavaPlugin {

    private FriendStorage friendStorage;

    @Override
    public void onEnable() {
        // Initialize friend storage
        friendStorage = new FriendStorage(this);

        // Register commands
        this.getCommand("friend").setExecutor(new FriendCommand(friendStorage));
        this.getCommand("friends").setExecutor(new FriendsCommand(friendStorage));

        getLogger().info("FriendsPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("FriendsPlugin has been disabled!");
    }
}
