package gg.techquest.listeners;

import gg.techquest.EliteKits;
import gg.techquest.listeners.player.PlayerStateListener;
import gg.techquest.profile.listener.ProfileListener;

import org.bukkit.plugin.PluginManager;

public class ListenerManager {

    private EliteKits plugin;

    public ListenerManager(EliteKits plugin) {
        this.plugin = plugin;

        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerStateListener(plugin), plugin);
        pluginManager.registerEvents(new ProfileListener(plugin), plugin);
    }
}
