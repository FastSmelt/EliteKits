package gg.techquest;

import gg.techquest.commands.CommandManager;
import gg.techquest.items.ItemManager;

import gg.techquest.listeners.ListenerManager;

import gg.techquest.profile.ProfileManager;
import gg.techquest.region.RegionManager;
import gg.techquest.region.cube.Cuboid;
import gg.techquest.sidebar.ElitekitsSidebar;

import gg.techquest.util.database.Config;
import gg.techquest.util.database.MongoStorage;

import io.github.nosequel.menu.MenuHandler;
import io.github.thatkawaiisam.assemble.Assemble;

import lombok.Getter;

import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class EliteKits extends JavaPlugin {

    @Getter private static EliteKits instance;

    private MongoStorage mongoStorage;
    private ProfileManager profileManager;
    private CommandManager commandManager;
    private ItemManager itemManager;
    private ListenerManager listenerManager;
    private RegionManager regionManager;

    @Getter @Setter private Cuboid spawnCuboid;
    private Location spawnLocation;
    private Config locationConfig;

    private Assemble assemble;
    private Config fileSetup;

    @Override
    public void onEnable() {
        instance = this;
        registerSerializableClass(Cuboid.class);

        this.mongoStorage = new MongoStorage();
        this.profileManager = new ProfileManager();
        this.commandManager = new CommandManager(this);
        this.itemManager = new ItemManager(this);
        this.listenerManager = new ListenerManager(this);
        this.regionManager = new RegionManager(this);

        new MenuHandler(this);

        loadConfigs();

        this.assemble = new Assemble(this, new ElitekitsSidebar(this));
        this.assemble.setTicks(20);
    }

    @Override
    public void onDisable() {
        profileManager.saveProfiles();
        saveConfig();

        instance = null;
    }

    void loadConfigs() {
        this.fileSetup = new Config(this, "config");

        locationConfig = new Config(this, "locations");

        World mainWorld = getServer().getWorlds().get(0);

        locationConfig.addDefault("spawn", mainWorld.getSpawnLocation());
        locationConfig.addDefault("spawn-cuboid", new Cuboid(mainWorld.getSpawnLocation()));
        locationConfig.copyDefaults();

        spawnLocation = locationConfig.getLocation("spawn");
        spawnCuboid = (Cuboid) locationConfig.get("spawn-cuboid");
    }

    // Taken from github
    private void registerSerializableClass(Class<?> clazz) {
        if (ConfigurationSerializable.class.isAssignableFrom(clazz)) {
            Class<? extends ConfigurationSerializable> serializable = clazz.asSubclass(ConfigurationSerializable.class);
            ConfigurationSerialization.registerClass(serializable);
        }
    }
}
