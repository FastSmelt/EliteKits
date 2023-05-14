package gg.techquest;

import gg.techquest.commands.CommandManager;
import gg.techquest.items.ItemManager;
import gg.techquest.listeners.ListenerManager;
import gg.techquest.profile.listener.ProfileListener;
import gg.techquest.profile.ProfileManager;
import gg.techquest.sidebar.ElitekitsSidebar;
import gg.techquest.util.database.Config;
import gg.techquest.util.database.MongoStorage;
import io.github.thatkawaiisam.assemble.Assemble;

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
    private ItemManager itemManager;
    private ListenerManager listenerManager;

    private Assemble assemble;
    private Config fileSetup;

    @Override
    public void onEnable() {
        instance = this;

        this.mongoStorage = new MongoStorage();
        this.profileManager = new ProfileManager();
        this.commandManager = new CommandManager(this);
        this.itemManager = new ItemManager(this);
        this.listenerManager = new ListenerManager(this);

        this.fileSetup = new Config(this, "config");

        this.assemble = new Assemble(this, new ElitekitsSidebar(this));
        this.assemble.setTicks(0);
    }

    @Override
    public void onDisable() {
        instance = null;
        profileManager.saveProfiles();

        saveConfig();
    }
}
