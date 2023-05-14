package gg.techquest.sidebar;

import gg.techquest.EliteKits;
import gg.techquest.profile.Profile;
import gg.techquest.profile.state.PlayerState;
import gg.techquest.util.CC;
import io.github.thatkawaiisam.assemble.AssembleAdapter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ElitekitsSidebar implements AssembleAdapter {

    private EliteKits plugin;

    public ElitekitsSidebar(EliteKits plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getTitle(Player player) {
        return ChatColor.translateAlternateColorCodes('&',
                plugin.getFileSetup().getString("SCOREBOARD.TITLE"));
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> lines = plugin.getFileSetup().getStringList("LOBBY.SCOREBOARD.LINES");
        List<String> translatedLines = new ArrayList<>();

        List<String> linesPvP = plugin.getFileSetup().getStringList("LOBBY.SCOREBOARD.LINES");
        List<String> translatedLinesPvP = new ArrayList<>();

        Profile profile = plugin.getProfileManager().getProfile(player);

        for (String line : lines) {
            String translatedLine = CC.translate(line
                    .replace("%player%", player.getName())
                    .replace("%kills%", String.valueOf(profile.getKills()))
                    .replace("%deaths%", String.valueOf(profile.getDeaths()))
                    .replace("%economy%", String.valueOf(profile.getEconomy())));

            translatedLines.add(translatedLine);
        }
        return translatedLines;
    }
}