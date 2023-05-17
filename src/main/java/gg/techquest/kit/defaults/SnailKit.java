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

public class SnailKit extends Kit {
    private PotionEffect speedEffect;

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    private ItemStack diamondSword;
    private ItemStack soup;

    public SnailKit(String name, String description, ItemStack[] items, long cooldown, String permission) {
        super(name, description, items, cooldown, permission);

        this.speedEffect = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0);
        addEffect(speedEffect);

        this.helmet = new ItemStack(Material.IRON_HELMET);
        this.chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        this.leggings = new ItemStack(Material.IRON_LEGGINGS);
        this.boots = new ItemStack(Material.LEATHER_BOOTS);
        this.boots.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
        this.boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

        this.diamondSword = new ItemStack(Material.IRON_SWORD);
        this.diamondSword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        this.diamondSword.addEnchantment(Enchantment.DURABILITY, 3);

        this.soup = new ItemStack(Material.MUSHROOM_SOUP);

        setPreviousKit(this);
    }

    @Override
    public void execute(Player player) {
        player.addPotionEffect(speedEffect);
        Inventory inventory = player.getInventory();

        player.getInventory().clear();

        int remainingSpace = inventory.getSize() - inventory.firstEmpty();

        if (remainingSpace > 0) {
            ItemStack soupStack = new ItemStack(Material.MUSHROOM_SOUP, remainingSpace);

            HashMap<Integer, ItemStack> remaining = inventory.addItem(soupStack);

            if (!remaining.isEmpty()) {
                for (ItemStack item : remaining.values()) {
                    player.getWorld().dropItem(player.getLocation(), item);
                }
            }
        }

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);

        player.getInventory().setItem(0, diamondSword);
        player.getInventory().setItem(1, soup);

        setPreviousKit(this);
    }

    public void addEffect(PotionEffect effect) {
        if (effect.getType().equals(PotionEffectType.SPEED)) {
            this.speedEffect = effect;
        }
    }
}
