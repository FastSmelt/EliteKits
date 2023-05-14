package gg.techquest.items;

import gg.techquest.EliteKits;
import gg.techquest.items.builder.ItemBuilder;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemManager {

    private EliteKits plugin;
    private ItemBuilder lobbySelector;

    public ItemManager(EliteKits plugin) {
        plugin = plugin;

        this.lobbySelector = ItemBuilder.from(new ItemBuilder(Material.COMPASS).build());
    }

    public void createLobbyloadout(Player player) {
        ItemStack selector = lobbySelector.build();

        player.getInventory().setItem(0, selector);
    }

    public void EventLoadout() {}
}
