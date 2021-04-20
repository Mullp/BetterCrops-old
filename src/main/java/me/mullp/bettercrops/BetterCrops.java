package me.mullp.bettercrops;

import me.mullp.bettercrops.events.OnCropClick;
import me.mullp.bettercrops.events.OnFarmlandTrample;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterCrops extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new OnCropClick(), this);
        getServer().getPluginManager().registerEvents(new OnFarmlandTrample(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
