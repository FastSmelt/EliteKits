package gg.techquest.listeners.player;

import gg.techquest.EliteKits;
import gg.techquest.profile.Profile;
import gg.techquest.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private EliteKits plugin;

    public DeathListener(EliteKits plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        Profile profile = plugin.getProfileManager().getProfile(killer);

        if (killer != null) {
            int amount = 100; // Set the amount of money to give
            int bal = profile.getEconomy();

            profile.setEconomy(bal + amount);
            killer.sendMessage(CC.translate("&aYou received $" + amount + " for killing " + victim.getName()));
        }
    }
}
