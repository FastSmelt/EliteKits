package gg.techquest.profile.command;

import gg.techquest.EliteKits;

import gg.techquest.profile.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import javax.swing.*;

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

        player.sendMessage("kills" + profile.getKills());
        player.sendMessage("Deaths" + profile.getDeaths());
        player.sendMessage("profile" + profile.getName());

        return false;
    }
}
