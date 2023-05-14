package gg.techquest.kit;

import gg.techquest.kit.defaults.PvP;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class KitManager {
    private Map<String, Kit> kits;

    public KitManager() {
        kits = new HashMap<>();
        kits.put("pvp", new PvP("My Kit", "Gives you a diamond sword and speed boost", null, 60000, "mykit.use"));
    }

    public Kit getKit(String name) {
        return kits.get(name.toLowerCase());
    }
    public void giveKit(String kitName, Player player) {
        Kit kit = getKit(kitName);
        if (kit != null) {
            kit.execute(player);
        } else {
            // You can display a message to the player or take some other action
        }
    }
}
