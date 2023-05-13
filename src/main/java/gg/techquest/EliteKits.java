package gg.techquest;

import gg.techquest.commands.CommandManager;
import gg.techquest.profile.listener.ProfileListener;
import gg.techquest.profile.manager.ProfileManager;
import gg.techquest.util.database.MongoStorage;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class EliteKits extends JavaPlugin implements Listener {

    @Getter private static EliteKits instance;

    private MongoStorage mongoStorage;
    private ProfileManager profileManager;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;

        this.mongoStorage = new MongoStorage();
        this.profileManager = new ProfileManager();
        this.commandManager = new CommandManager(this);

        loadListeners();
    }

    @Override
    public void onDisable() {
        profileManager.saveProfiles();
    }

    void loadListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ProfileListener(this), this);
    }
}
