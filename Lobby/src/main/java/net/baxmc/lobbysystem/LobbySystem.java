package net.baxmc.lobbysystem;

import net.baxmc.api.BaxAPI;
import net.baxmc.lobbysystem.cache.ItemCache;
import net.baxmc.lobbysystem.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbySystem extends JavaPlugin {

    private static LobbySystem instance;
    private ItemCache itemCache;
    private BaxAPI baxAPI;

    @Override
    public void onEnable() {
        instance = this;
        this.itemCache = new ItemCache();

        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);

        baxAPI = new BaxAPI(false);

    }

    public static LobbySystem getInstance() {
        return instance;
    }

    public String generateDisplayName(final String name){
        return "§8» §6§l" + name + " §8▏ §7Rechtsklick";
    }

    public String generateItemName(final String name){
        return "§8» §6§l" + name;
    }

    public ItemCache getItemCache() {
        return itemCache;
    }

    public BaxAPI getBaxAPI() {
        return baxAPI;
    }
}
