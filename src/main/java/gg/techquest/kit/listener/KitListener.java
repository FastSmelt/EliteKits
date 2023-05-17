package gg.techquest.kit.listener;

import gg.techquest.EliteKits;
import gg.techquest.util.CC;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class KitListener implements Listener {

    private final Material switcherMaterial = Material.SNOW_BALL;
    private static final Map<Player, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player damagedPlayer = (Player) event.getEntity();
            Player attacker = (Player) event.getDamager();

            double chance = 0.5;

            if (EliteKits.getInstance().getKitManager().getKit("snail") == null) {
                return;
            }

            if (Math.random() < chance) {
                PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, 5 * 20, 1);
                damagedPlayer.addPotionEffect(slowness);

                attacker.sendMessage(CC.translate("&a" + damagedPlayer.getName() + " has been slowed for 5 seconds by " + attacker.getName()));
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();

        if (EliteKits.getInstance().getKitManager().getKit("switcher") != null) {
            if (item != null && item.getType() == switcherMaterial && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
                if (hasCooldown(player)) {
                    player.sendMessage("You must wait before using this again.");
                    event.setCancelled(true);
                    return;
                }

                setCooldown(player, 10);

                Player nearbyPlayer = findNearbyPlayer(player);

                if (nearbyPlayer != null) {
                    Vector playerPosition = player.getLocation().toVector();
                    Vector nearbyPlayerPosition = nearbyPlayer.getLocation().toVector();

                    player.teleport(nearbyPlayerPosition.toLocation(player.getWorld()));
                    nearbyPlayer.teleport(playerPosition.toLocation(nearbyPlayer.getWorld()));

                    player.sendMessage(CC.translate("&aYou have switched positions with " + nearbyPlayer.getName() + "!"));
                    nearbyPlayer.sendMessage(CC.translate("&aYou have switched positions with " + player.getName() + "!"));
                }
            }
        }
    }



    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile instanceof Snowball) {
            if (projectile.getShooter() instanceof Player) {
                Player player = (Player) projectile.getShooter();
                if (hasCooldown(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    private boolean hasCooldown(Player player) {
        if (cooldowns.containsKey(player)) {
            long cooldownTime = cooldowns.get(player);
            long currentTime = System.currentTimeMillis();
            return currentTime < cooldownTime;
        }
        return false;
    }

    private void setCooldown(Player player, int seconds) {
        long cooldownTime = System.currentTimeMillis() + (seconds * 1000L);
        cooldowns.put(player, cooldownTime);

        new BukkitRunnable() {
            @Override
            public void run() {
                cooldowns.remove(player);
            }
        }.runTaskLaterAsynchronously(EliteKits.getInstance(), seconds * 20L); // Delay is in ticks, 20 ticks = 1 second
    }

    // will fix
    public static int getCooldownSeconds(Player player) {
        if (cooldowns.containsKey(player)) {
            long cooldownTime = cooldowns.get(player);
            long currentTime = System.currentTimeMillis();
            long remainingTime = cooldownTime - currentTime;
            int seconds = (int) Math.ceil(remainingTime / 1000.0);

            return Math.max(seconds, 0);
        }
        return 0;
    }

    private Player findNearbyPlayer(Player player) {
        return player.getWorld().getPlayers().stream().filter(nearby -> nearby != player && nearby.getLocation().distance(player.getLocation()) <= 5.0).findFirst().orElse(null);
    }
}