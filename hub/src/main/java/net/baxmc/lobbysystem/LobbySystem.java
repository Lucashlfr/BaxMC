package net.baxmc.lobbysystem;

import net.baxmc.lobbysystem.cache.ItemCache;
import net.baxmc.lobbysystem.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbySystem extends JavaPlugin {

    private static LobbySystem instance;
    private ItemCache itemCache;

    @Override
    public void onEnable() {
        instance = this;
        this.itemCache = new ItemCache();

        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);

    }

    public static LobbySystem getInstance() {
        return instance;
    }

    public String generateDisplayName(final String name){
        return "" + name;
    }

    public ItemCache getItemCache() {
        return itemCache;
    }
}
