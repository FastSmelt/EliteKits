package gg.techquest.region.command;

import gg.techquest.EliteKits;
import gg.techquest.region.cube.Cuboid;
import gg.techquest.region.data.RegionData;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RegionCommand implements CommandExecutor {

    private EliteKits plugin;

    public RegionCommand(EliteKits plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        Player player = (Player) commandSender;

        if (args.length < 1) {
        }

        switch (args[0].toLowerCase()) {
            case "start":
                if (plugin.getRegionManager().isEditingRegion(player)) {
                    player.sendMessage("You're already editing a region!");
                }

                plugin.getRegionManager().startEditingRegion(player);
                player.sendMessage("Begun editing region. Use the wand to select points.");
                player.getInventory().clear();
                player.getInventory().setItem(1, new ItemStack(Material.GOLD_AXE));
                break;
            case "stop":
                if (!plugin.getRegionManager().isEditingRegion(player)) {
                    player.sendMessage("You aren't editing a region!");
                }

                plugin.getRegionManager().stopEditingRegion(player);
                player.sendMessage("Stopped editing region.");
                break;
            case "finish":
                if (!plugin.getRegionManager().isEditingRegion(player)) {
                    player.sendMessage("You aren't editing a region!");
                }

                if (!plugin.getRegionManager().isDataValid(player)) {
                    player.sendMessage("You must set both points with the wand before you can finish!");
                }

                if (args.length < 2) {
                    player.sendMessage("You must define a name for this region! (spawn|koth)");
                }

                RegionData data = plugin.getRegionManager().getData(player);
                Cuboid cuboid = new Cuboid(data.getA(), data.getB());

                switch (args[1].toLowerCase()) {
                    case "spawn":
                        plugin.setSpawnCuboid(cuboid);
                        plugin.getLocationConfig().set("spawn-cuboid", cuboid);
                        plugin.getLocationConfig().save();
                        break;
                }

                plugin.getRegionManager().stopEditingRegion(player);

                player.sendMessage("Finished editing region.");
                break;
        }
        return true;
    }
}
