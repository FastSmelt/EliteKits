package gg.techquest.listeners;

import gg.techquest.EliteKits;
import gg.techquest.items.listener.ItemListener;
import gg.techquest.kit.listener.KitListener;
import gg.techquest.listeners.menu.KitSelectorListener;
import gg.techquest.listeners.player.DeathListener;
import gg.techquest.listeners.player.PlayerStateListener;
import gg.techquest.profile.listener.ProfileListener;

import org.bukkit.plugin.PluginManager;

public class ListenerManager {

    private EliteKits plugin;

    public ListenerManager(EliteKits plugin) {
        this.plugin = plugin;

        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerStateListener(plugin), plugin);
        pluginManager.registerEvents(new KitSelectorListener(), plugin);
        pluginManager.registerEvents(new ProfileListener(plugin), plugin);
        pluginManager.registerEvents(new DeathListener(plugin), plugin);
        pluginManager.registerEvents(new KitListener(), plugin);
        pluginManager.registerEvents(new ItemListener(), plugin);
    }
}
