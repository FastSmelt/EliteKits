package gg.techquest.region.listener;

import gg.techquest.EliteKits;
import gg.techquest.profile.Profile;
import gg.techquest.profile.state.PlayerState;
import gg.techquest.region.cube.data.RegionData;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class RegionListener implements Listener {
    private final EliteKits plugin;

    public RegionListener(EliteKits plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        final Action action = event.getAction();

        if (action == Action.LEFT_CLICK_AIR || action == Action.RIGHT_CLICK_AIR || !event.hasItem() || !event.hasBlock())
            return;

        final ItemStack item = event.getItem();

        if (item.getType() != Material.GOLD_AXE) return;

        Player player = event.getPlayer();

        if (!plugin.getRegionManager().isEditingRegion(player)) return;

        RegionData data = plugin.getRegionManager().getData(player);
        Location location = event.getClickedBlock().getLocation();
        boolean a = action == Action.LEFT_CLICK_BLOCK;

        if (a) {
            data.setA(location);
            player.sendMessage("Set point a.");
        } else {
            data.setB(location);
            player.sendMessage("Set point b.");
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Profile profile = plugin.getProfileManager().getProfile(player);

        if (profile.getPlayerState() != PlayerState.LOBBY) {
            return;
        }

        Location to = event.getTo();
        Location from = event.getFrom();

        if (to.getBlockX() == from.getBlockX() && to.getBlockZ() == from.getBlockZ()) {
            return;
        }

        if (!plugin.getSpawnCuboid().contains(to)) {

            if (!plugin.getKitManager().hasKit(player)) {
                plugin.getKitManager().giveKit("pvp", player);
            }

            plugin.getRegionManager().loseSpawnProtection(player);

            profile.setPlayerState(PlayerState.PVP);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        plugin.getRegionManager().stopEditingRegion(player);
    }
}