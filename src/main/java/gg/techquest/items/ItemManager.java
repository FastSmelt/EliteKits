package gg.techquest.items;

import gg.techquest.EliteKits;
import gg.techquest.items.builder.ItemBuilder;

import gg.techquest.util.CC;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

    private ItemBuilder playerProfile = ItemBuilder.from(new ItemBuilder(Material.SKULL_ITEM)
            .name(CC.translate("&bYour Stats&7 (Right Click)"))
            .lore(CC.translate("&bProfile Menu"))
            .build());

    private ItemBuilder settingsMenu = ItemBuilder.from(new ItemBuilder(Material.WATCH)
            .name(CC.translate("&bSettings&7 (Right Click)"))
            .lore(CC.translate("&bSettings Menu"))
            .build());

    private ItemBuilder cosmeticMenu = ItemBuilder.from(new ItemBuilder(Material.NETHER_STAR)
            .name(CC.translate("&bCosmetics&7 (Right Click)"))
            .lore(CC.translate("&bCosmetics Menu"))
            .build());

    public void createLobbyloadout(Player player) {
        ItemStack selector = lobbySelector.build();
        ItemStack previous = previousKit.build();
        ItemStack perks = perksMenu.build();
        ItemStack profile = playerProfile.build();
        ItemStack settings = settingsMenu.build();
        ItemStack cosmetics = cosmeticMenu.build();

        player.getInventory().setItem(0, selector);
        player.getInventory().setItem(1, previous);
        player.getInventory().setItem(2, perks);
        player.getInventory().setItem(4, profile);
        player.getInventory().setItem(7, settings);
        player.getInventory().setItem(8, cosmetics);
    }

    public void eventLoadout() {}
}
