package gg.techquest.items.listener;

import gg.techquest.EliteKits;
import gg.techquest.kit.Kit;
import gg.techquest.menus.KitSelector;
import gg.techquest.profile.Profile;
import gg.techquest.util.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        Profile profile = EliteKits.getInstance().getProfileManager().getProfile(player);
        Kit previousKit = profile.getPreviousKit();

        if ((action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_AIR) && player.getInventory().getItemInHand().getType() == Material.WATCH) {
            if (previousKit != null) {
                previousKit.execute(player);
            } else {
                player.sendMessage(CC.translate("&aNo Previous Kit"));
            }
        }
    }
}
