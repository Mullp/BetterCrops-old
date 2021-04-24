package me.mullp.bettercrops.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnFarmlandTrample implements Listener {
    @EventHandler
    public void onFarmlandTrample(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block farmland = event.getClickedBlock();
        if (farmland != null && farmland.getType().equals(Material.FARMLAND) && event.getAction().equals(Action.PHYSICAL)) {
            if (player.getInventory().getBoots() != null) {
                if (player.getInventory().getBoots().containsEnchantment(Enchantment.PROTECTION_FALL)) {
                    int rand = (int) (Math.random()*Enchantment.PROTECTION_FALL.getMaxLevel()) + 1;
                    if (rand <= player.getInventory().getBoots().getEnchantmentLevel(Enchantment.PROTECTION_FALL)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
