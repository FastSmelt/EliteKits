package gg.techquest.sidebar;

import com.google.common.base.Strings;
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
        List<String> toReturn = new ArrayList<>();

        Profile profile = plugin.getProfileManager().getProfile(player);

        switch (profile.getPlayerState()) {
            case LOBBY:
                toReturn.add(CC.translate("&7&m" + Strings.repeat("-", 20)));
                toReturn.add("&fKills:&b " + profile.getKills());
                toReturn.add("&fDeaths:&b " + profile.getDeaths());
                toReturn.add("&fCredits:&b " + profile.getKillstreak());
                toReturn.add("");
                toReturn.add(CC.translate("&7tech.quest"));
                toReturn.add(CC.translate("&7&m" + Strings.repeat("-", 20)));
                break;
            case PVP:
                toReturn.add(CC.translate("&7&m" + Strings.repeat("-", 20)));
                toReturn.add("&fKills:&b " + profile.getKills());
                toReturn.add("&fDeaths:&b " + profile.getDeaths());
                toReturn.add(CC.translate("&7&m" + Strings.repeat("-", 20)));
        }
        return toReturn;
    }
}