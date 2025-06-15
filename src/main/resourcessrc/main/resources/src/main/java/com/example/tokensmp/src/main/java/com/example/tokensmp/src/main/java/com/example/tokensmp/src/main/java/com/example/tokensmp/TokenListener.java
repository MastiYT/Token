package com.example.tokensmp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class TokenListener implements Listener {

    private final TokenManager tokenManager;
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public TokenListener(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            Bukkit.getScheduler().runTaskLater(TokenSMP.getInstance(), () -> {
                tokenManager.giveRandomToken(player);
                player.sendMessage("§aYou received a random Token!");
            }, 40L);
        }
    }

    @EventHandler
    public void onUse(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || item.getType() == Material.AIR) return;
        if (!tokenManager.isToken(item)) return;

        Token token = tokenManager.getTokenFromItem(item);
        if (token == null) return;

        long current = System.currentTimeMillis();
        long lastUsed = cooldowns.getOrDefault(player.getUniqueId(), 0L);
        if ((current - lastUsed) < (token.getCooldown() * 1000L)) {
            player.sendMessage("§cToken is on cooldown!");
            return;
        }

        cooldowns.put(player.getUniqueId(), current);
        player.sendMessage("§bActivated token power: §e" + token.getPower());

        // Simulate power (you can customize this)
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        player.setGlowing(true);

        Bukkit.getSched
          
