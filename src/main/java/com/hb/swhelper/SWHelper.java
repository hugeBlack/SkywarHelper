package com.hb.swhelper;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SWHelper extends JavaPlugin {

    public static boolean randomKitEnabled = false;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new SWHListeners(),this);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new SWIntervals(), 0L, 10L);
        this.getCommand("swh").setExecutor(new SWHCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
