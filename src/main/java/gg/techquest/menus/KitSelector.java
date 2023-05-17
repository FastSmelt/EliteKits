package gg.techquest.menus;

import gg.techquest.EliteKits;
import gg.techquest.profile.Profile;
import gg.techquest.profile.state.PlayerState;
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
        Player player = getPlayer();
        Profile profile = EliteKits.getInstance().getProfileManager().getProfile(player);

        this.buttons[10] = new Button(new ItemStack(Material.DIAMOND_SWORD))
                .setDisplayName(CC.translate("&bPvP"))
                .setLore(new String[]{
                        CC.translate("&7&m-------------------"),
                        CC.translate("&7Basic PvP Kit"),
                        CC.translate("&7&m-------------------")
                })
                .setClickAction(event -> {
                    event.setCancelled(true);
                    EliteKits.getInstance().getKitManager().giveKit("pvp",getPlayer());
                    profile.setPlayerState(PlayerState.PVP);
                    this.updateMenu();
                });
        this.buttons[12] = new Button(new ItemStack(Material.MUSHROOM_SOUP))
                .setDisplayName(CC.translate("&bSnail"))
                .setLore(new String[]{
                        CC.translate("&7&m-------------------"),
                        CC.translate("&7Every hit gives player a "),
                        CC.translate("&7 a chance to give enemy"),
                        CC.translate("&7Slowness"),
                        CC.translate("&7&m-------------------")
                })
                .setClickAction(event -> {
                    event.setCancelled(true);
                    EliteKits.getInstance().getKitManager().giveKit("snail",getPlayer());
                    profile.setPlayerState(PlayerState.PVP);
                    this.updateMenu();
                });

        this.buttons[14] = new Button(new ItemStack(Material.SNOW_BALL))
                .setDisplayName(CC.translate("&bSwitcher"))
                .setLore(new String[]{
                        CC.translate("&7&m-------------------"),
                        CC.translate("&7Shoot players with snowballs"),
                        CC.translate("&7To switch places with them"),
                        CC.translate("&7&m-------------------")
                })
                .setClickAction(event -> {
                    event.setCancelled(true);
                    EliteKits.getInstance().getKitManager().giveKit("switcher",getPlayer());
                    profile.setPlayerState(PlayerState.PVP);
                    this.updateMenu();
                });
    }
}
