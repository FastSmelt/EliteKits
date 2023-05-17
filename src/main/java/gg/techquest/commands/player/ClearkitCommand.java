package gg.techquest.commands.player;

import gg.techquest.EliteKits;
import gg.techquest.profile.Profile;
import gg.techquest.profile.state.PlayerState;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearkitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            Player player = (Player) commandSender;
            Profile profile = EliteKits.getInstance().getProfileManager().getProfile(player);

            EliteKits.getInstance().getRegionManager().acquireSpawnProtection(player);

            player.getInventory().setHelmet(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setLeggings(null);
            player.getInventory().setBoots(null);

            player.getInventory().clear();
            player.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());

            profile.setPlayerState(PlayerState.LOBBY);

            EliteKits.getInstance().getItemManager().createLobbyloadout(player);
        }
        return false;
    }
}
