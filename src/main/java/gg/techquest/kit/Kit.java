package gg.techquest.kit;

import gg.techquest.EliteKits;
import gg.techquest.profile.Profile;
import gg.techquest.profile.state.PlayerState;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Kit {
    protected String name;
    protected String description;
    protected ItemStack[] items;
    protected long cooldown;
    protected Kit previousKit;
    protected String permission;
    protected List<PotionEffect> effects;

    public Kit(String name, String description, ItemStack[] items, long cooldown, String permission) {
        this.name = name;
        this.description = description;
        this.items = items;
        this.cooldown = cooldown;
        this.permission = permission;
        this.effects = new ArrayList<>();
    }

    public abstract void execute(Player player);

    protected void giveItems(Player player) {
        for (ItemStack item : items) {
            player.getInventory().addItem(item);
        }
    }

    protected void applyEffects(Player player) {
        for (PotionEffect effect : effects) {
            player.addPotionEffect(effect);
        }
    }

    protected void startCooldown(Player player) {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setPreviousKit(Kit previousKit) {
        this.previousKit = previousKit;
    }

    public Kit getPreviousKit() {
        return previousKit;
    }

}