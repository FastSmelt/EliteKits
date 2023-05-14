package gg.techquest.kit.defaults;

import gg.techquest.kit.Kit;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class PvP extends Kit {
    private PotionEffect speedEffect;

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    private ItemStack diamondSword;
    private ItemStack soup;

    public PvP(String name, String description, ItemStack[] items, long cooldown, String permission) {
        super(name, description, items, cooldown, permission);

        this.speedEffect = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1);
        addEffect(speedEffect);

        this.helmet = new ItemStack(Material.IRON_HELMET);
        this.chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        this.leggings = new ItemStack(Material.IRON_LEGGINGS);
        this.boots = new ItemStack(Material.IRON_BOOTS);

        this.diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        this.diamondSword.addEnchantment(Enchantment.DAMAGE_ALL, 1);

        this.soup = new ItemStack(Material.MUSHROOM_SOUP);
    }

    @Override
    public void execute(Player player) {
        player.addPotionEffect(speedEffect);
        Inventory inventory = player.getInventory();

        for (int i = 0; i < inventory.getSize(); i++) {
            HashMap<Integer, ItemStack> remaining = inventory.addItem(soup);
            if (remaining.isEmpty()) {
                break;
            }
        }

        player.getInventory().clear(); // Clear player's inventory before adding items
        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);

        player.getInventory().setItem(0, diamondSword);
        player.getInventory().setItem(1, soup);
    }

    public void addEffect(PotionEffect effect) {
        if (effect.getType().equals(PotionEffectType.SPEED)) {
            this.speedEffect = effect;
        }
    }
}
