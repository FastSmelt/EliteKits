package gg.techquest.profile.command;

import com.google.common.base.Strings;

import gg.techquest.EliteKits;

import gg.techquest.profile.Profile;
import gg.techquest.util.CC;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

public class ProfileCommand implements CommandExecutor {

    private EliteKits plugin;

    public ProfileCommand(EliteKits plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(!(commandSender instanceof Player)) {
            return true;
        }

        Player player = (Player) commandSender;

        Profile profile = plugin.getProfileManager().getProfile(player.getUniqueId());

        player.sendMessage(CC.translate("&7&m" + Strings.repeat("---", 10)));
        player.sendMessage(CC.translate("&7Reviewing &b(" + profile.getName() + ")"));
        player.sendMessage(" ");
        player.sendMessage(CC.translate("&7Kills:&b " + profile.getKills()));
        player.sendMessage(CC.translate("&7Deaths:&b " + profile.getDeaths()));
        player.sendMessage(CC.translate("&7Balance:&b " + profile.getEconomy()));
        player.sendMessage(CC.translate("&7&m" + Strings.repeat("---", 10)));

        return false;
    }
}
