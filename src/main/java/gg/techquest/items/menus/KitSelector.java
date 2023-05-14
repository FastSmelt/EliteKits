package gg.techquest.items.menus;

import gg.techquest.EliteKits;
import gg.techquest.util.CC;

import io.github.nosequel.menu.Menu;

import io.github.nosequel.menu.buttons.Button;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitSelector extends Menu {
    public KitSelector(Player player) {
        super(player, CC.translate("&bKit Selector"), 27);
    }

    @Override
    public void tick() {
        this.buttons[10] = new Button(new ItemStack(Material.DIAMOND_SWORD))
                .setDisplayName(CC.translate("&bPvP"))
                .setLore(new String[] {
                        "",
                        CC.translate("&7Your Gems is:&d"),
                        ""
                })
                .setClickAction(event -> {
                    event.setCancelled(true);

                    EliteKits.getInstance().getKitManager().giveKit("pvp",getPlayer());

                    this.updateMenu();
                });
    }
}
