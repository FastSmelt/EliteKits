package gg.techquest.items;

import gg.techquest.EliteKits;
import gg.techquest.items.builder.ItemBuilder;

import gg.techquest.util.CC;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class ItemManager {

    private EliteKits plugin;
    public ItemManager(EliteKits plugin) {
        this.plugin = plugin;
    }

    private ItemBuilder lobbySelector = ItemBuilder.from(new ItemBuilder(Material.ENCHANTED_BOOK)
            .name(CC.translate("&bKit Selector&7 (Right Click)"))
            .lore(CC.translate("&7Kit Selector"))
            .build());

    private ItemBuilder previousKit = ItemBuilder.from(new ItemBuilder(Material.WATCH)
            .name(CC.translate("&bPrevious Kit&7 (Right Click)"))
            .lore(CC.translate("&7&bPrevious Kit&7 Selector"))
            .build());

    private ItemBuilder perksMenu = ItemBuilder.from(new ItemBuilder(Material.CHEST)
            .name(CC.translate("&bPerks &7 (Right Click)"))
            .lore(CC.translate("&bPerks Menu"))
            .build());

    public void createLobbyloadout(Player player) {
        ItemStack selector = lobbySelector.build();
        ItemStack previous = previousKit.build();
        ItemStack perks = perksMenu.build();

        player.getInventory().setItem(0, selector);
        player.getInventory().setItem(1, previous);
        player.getInventory().setItem(8, perks);
    }

    public void eventLoadout() {}
}
