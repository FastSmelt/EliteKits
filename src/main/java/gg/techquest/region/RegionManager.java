package gg.techquest.region;
import gg.techquest.EliteKits;
import gg.techquest.profile.Profile;
import gg.techquest.profile.state.PlayerState;
import gg.techquest.region.command.RegionCommand;
import gg.techquest.region.data.RegionData;
import gg.techquest.region.listener.RegionListener;
import gg.techquest.region.tasks.VulnerableTask;
import gg.techquest.util.CC;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RegionManager {

    private EliteKits plugin;

    public RegionManager(EliteKits plugin) {
        this.plugin = plugin;

        this.plugin.getCommand("editregion").setExecutor(new RegionCommand(plugin));
        this.plugin.getServer().getPluginManager().registerEvents(new RegionListener(plugin), plugin);
    }

    private final Map<UUID, RegionData> regionData = new HashMap<>();

    public void startEditingRegion(Player player) {
        regionData.put(player.getUniqueId(), new RegionData());
    }

    public boolean isEditingRegion(Player player) {
        return regionData.containsKey(player.getUniqueId());
    }

    public boolean isDataValid(Player player) {
        return isEditingRegion(player) && regionData.get(player.getUniqueId()).getA() != null
                && regionData.get(player.getUniqueId()).getB() != null;
    }

    public RegionData getData(Player player) {
        return regionData.get(player.getUniqueId());
    }

    public void stopEditingRegion(Player player) {
        regionData.remove(player.getUniqueId());
    }

    public void loseSpawnProtection(Player player) {
        if (player.getGameMode() == GameMode.CREATIVE) {
            return;
        }

        Profile profile = plugin.getProfileManager().getProfile(player);

        profile.setPlayerState(PlayerState.PVP);

        player.sendMessage(CC.translate("&cYou no longer have spawn protection!"));
    }

    public void acquireSpawnProtection(Player player) {
        Profile profile = plugin.getProfileManager().getProfile(player);

        profile.setPlayerState(PlayerState.LOBBY);

        player.sendMessage(CC.translate("&aYou have acquired spawn protection."));
    }
    public void makeVulnerable(Player player, String statusChange) {
        Profile profile = plugin.getProfileManager().getProfile(player);

        VulnerableTask vulnerableTask = new VulnerableTask(player, statusChange, profile);
        profile.setVulnerableTask(vulnerableTask);
        plugin.getServer().getScheduler().runTaskLater(plugin, vulnerableTask, 100);
        player.sendMessage(CC.translate("&cYou are vulnerable for 5 seconds!"));
    }
}
