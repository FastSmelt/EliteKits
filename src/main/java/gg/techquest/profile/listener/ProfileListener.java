package gg.techquest.profile.listener;

import gg.techquest.EliteKits;
import gg.techquest.profile.Profile;

import gg.techquest.profile.state.PlayerState;
import gg.techquest.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.*;

public class ProfileListener implements Listener {

    private EliteKits plugin;
    private final Map<UUID, Profile> profiles = new HashMap<>();

    public ProfileListener(EliteKits plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        if (event.getLoginResult() == AsyncPlayerPreLoginEvent.Result.ALLOWED) {
            plugin.getProfileManager().createProfile(event.getName(), event.getUniqueId());
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        final Player player = event.getPlayer();
        final Profile profile = plugin.getProfileManager().getProfile(player);

        if (profile == null) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Your data failed to load for KitPvP. Try logging in again.");
        } else if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
            plugin.getProfileManager().removeProfile(player.getUniqueId());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final Profile profile = plugin.getProfileManager().getProfile(player);

        plugin.getFileSetup().getStringList("WELCOME.MESSAGE").stream().map(CC::translate).forEach(player::sendMessage);

        plugin.getRegionManager().acquireSpawnProtection(player);

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);

        plugin.getItemManager().createLobbyloadout(player);
        profile.setPlayerState(PlayerState.LOBBY);
        player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final Profile profile = plugin.getProfileManager().getProfile(player);

        if (profile == null) return;

        profile.save(false);
        plugin.getProfileManager().removeProfile(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        final Player player = event.getPlayer();
        final Profile profile = plugin.getProfileManager().getProfile(player);

        profile.setPlayerState(PlayerState.LOBBY);

        plugin.getRegionManager().acquireSpawnProtection(player);
        plugin.getItemManager().createLobbyloadout(player);
        player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
    }
}
