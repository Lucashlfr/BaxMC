package net.baxmc.api.bootstrapper;

import net.baxmc.api.BaxAPI;
import net.baxmc.api.commons.server.manager.AsyncPlayerChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotBootstrapper extends JavaPlugin {

    @Override
    public void onEnable() {
        new BaxAPI(false);

        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new AsyncPlayerChatListener(), this);

    }

    @Override
    public void onDisable() {

    }


}
