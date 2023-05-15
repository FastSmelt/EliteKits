package gg.techquest.listeners.menu;

import gg.techquest.menus.KitSelector;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class KitSelectorListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_AIR) {
            if (player.getInventory().getItemInHand().getType() == Material.ENCHANTED_BOOK) {
                new KitSelector(player).updateMenu();
            }
        }
    }
}
