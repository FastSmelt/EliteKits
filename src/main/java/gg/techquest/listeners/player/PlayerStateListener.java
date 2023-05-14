package gg.techquest.listeners.player;

import gg.techquest.EliteKits;
import gg.techquest.profile.Profile;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerStateListener implements Listener {

    private EliteKits plugin;

    public PlayerStateListener(EliteKits plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPLayerBlockPlace(BlockPlaceEvent e) {
        final Player player = e.getPlayer();
        final Profile profile = plugin.getProfileManager().getProfile(player);

        switch (profile.getPlayerState()) {
            case LOBBY:
            case PVP:
                e.setCancelled(true);
                break;
            case STAFFMODE:
                e.setCancelled(false);
                break;
        }
    }
    @EventHandler
    public void onPlayerBlockBreak(BlockBreakEvent e) {
        final Player player = e.getPlayer();
        final Profile profile = plugin.getProfileManager().getProfile(player);

        switch (profile.getPlayerState()) {
            case LOBBY:
            case PVP:
                e.setCancelled(true);
                break;
            case STAFFMODE:
                e.setCancelled(false);
                break;
        }
    }

    @EventHandler
    public void onPlayerEntityDamage(EntityDamageEvent e) {
        final Player player = (Player) e.getEntity();
        final Profile profile = plugin.getProfileManager().getProfile(player);

        switch (profile.getPlayerState()) {
            case LOBBY:
                e.setCancelled(true);
                break;
        }
    }
}
