package me.mullp.bettercrops.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Set;

public class OnCropClick implements Listener {
    private static final Set<Material> CropTypes = Set.of(Material.WHEAT, Material.CARROTS, Material.POTATOES, Material.BEETROOTS, Material.NETHER_WART, Material.COCOA);
    private static final Set<Material> SeedTypes = Set.of(Material.WHEAT_SEEDS, Material.CARROT, Material.POTATO, Material.BEETROOT_SEEDS, Material.NETHER_WART, Material.COCOA_BEANS);
    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getHand().equals(EquipmentSlot.HAND)) {
                Block block = event.getClickedBlock();
                if (CropTypes.contains(block.getType())) {
                    Ageable age = (Ageable) block.getBlockData();
                    if (age.getAge() == age.getMaximumAge()) {
                        Collection<ItemStack> drops = block.getDrops(event.getPlayer().getInventory().getItemInMainHand());
                        Location location = block.getLocation();
                        boolean removedSeed = false;
                        for (ItemStack drop : drops) {
                            boolean makeDrop = true;
                            if (!removedSeed) {
                                if (SeedTypes.contains(drop.getType())) {
                                    if (drop.getAmount() > 1) {
                                        removedSeed = true;
                                        drop.setAmount(drop.getAmount()-1);
                                    } else {
                                        makeDrop = false;
                                        removedSeed = true;
                                    }
                                }
                            }
                            if (makeDrop)
                                block.getWorld().dropItemNaturally(location, drop);
                        }
                        age.setAge(0);
                        block.setBlockData(age);
                    }
                }
            }
        }
    }
}
