package gg.techquest.region.cube.tasks;

import gg.techquest.profile.Profile;

import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.material.Wool;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class VulnerableTask implements Runnable {

    public final Player player;
    public final String statusChange;
    private final Collection<PotionEffect> potionEffects;
    private final Wool woolColor = new Wool(DyeColor.RED);
    private final Profile profile;

    public VulnerableTask(Player player, String statusChange, Profile profile) {
        this.player = player;
        this.profile = profile;
        this.statusChange = statusChange.toLowerCase();
        this.potionEffects = player.getActivePotionEffects();

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getActivePotionEffects().clear();
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
        player.setHealth(0.1);
        player.getInventory().setHelmet(woolColor.toItemStack());
    }

    @Override
    public void run() {

    }
}
