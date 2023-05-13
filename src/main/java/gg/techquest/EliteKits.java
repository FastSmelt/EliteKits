package gg.techquest;

import gg.techquest.database.MongoStorage;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class EliteKits extends JavaPlugin implements Listener {

    @Getter private static EliteKits instance;

    private MongoStorage mongoStorage;

    @Override
    public void onEnable() {
        instance = this;

        this.mongoStorage = new MongoStorage();
    }
}
