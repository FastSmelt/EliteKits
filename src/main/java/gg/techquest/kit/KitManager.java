package gg.techquest.kit;

import gg.techquest.EliteKits;
import gg.techquest.kit.defaults.PvP;
import gg.techquest.profile.Profile;
import gg.techquest.profile.state.PlayerState;
import gg.techquest.util.CC;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class KitManager {

    private EliteKits plugin;
    private Map<String, Kit> kits;

    public KitManager(EliteKits plugin) {
        this.plugin = plugin;

        kits = new HashMap<>();
        kits.put("pvp", new PvP("My Kit", "Gives you a diamond sword and speed boost", null, 60000, "mykit.use"));
    }

    public void getDefaultKit(Player player) {
        player.sendMessage(CC.translate("You hae been given default kit."));

        plugin.getKitManager().giveKit("PvP", player);
    }

    public boolean hasKit(Player player) {
        Profile profile = EliteKits.getInstance().getProfileManager().getProfile(player);
        return profile.getPlayerState() == PlayerState.PVP;
    }
    public Kit getKit(String name) {
        return kits.get(name.toLowerCase());
    }
    public void giveKit(String kitName, Player player) {
        Kit kit = getKit(kitName);

        if (kit != null) kit.execute(player);
        else {
            // You can display a message to the player or take some other action
        }
    }
}
