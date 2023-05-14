package gg.techquest.profile.listener;

import gg.techquest.EliteKits;
import gg.techquest.profile.Profile;

import gg.techquest.profile.state.PlayerState;
import gg.techquest.util.CC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        Player player = event.getPlayer();
        Profile profile = plugin.getProfileManager().getProfile(player);

        if (profile == null) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Your data failed to load for KitPvP. Try logging in again.");
        } else if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
            plugin.getProfileManager().removeProfile(player.getUniqueId());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Profile profile = plugin.getProfileManager().getProfile(player);

        plugin.getFileSetup().getStringList("WELCOME.MESSAGE").stream().map(CC::translate).forEach(player::sendMessage);

        plugin.getItemManager().createLobbyloadout(player);
        profile.setPlayerState(PlayerState.LOBBY);
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Profile profile = plugin.getProfileManager().getProfile(player);

        if (profile == null) {
            return;
        }

        profile.save(false);
        plugin.getProfileManager().removeProfile(player.getUniqueId());
    }
}
