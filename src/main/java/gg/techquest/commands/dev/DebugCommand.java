package gg.techquest.commands.dev;

import gg.techquest.EliteKits;
import gg.techquest.profile.Profile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            return true;
        }

        Player player = (Player) commandSender;

        if (!player.isOp()) {
            player.sendMessage("Nope");
            return true;
        }

        Profile profile = EliteKits.getInstance().getProfileManager().getProfile(player.getUniqueId());

        profile.setKills(100);

        profile.setEconomy(100);

        return false;
    }
}
