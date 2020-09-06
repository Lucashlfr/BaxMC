package net.baxmc.lobbysystem.listener;

import net.baxmc.lobbysystem.LobbySystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){

        event.setJoinMessage(null);

        final Player player = event.getPlayer();
        LobbySystem.getInstance().getItemCache().setDefaultItems(player, true);

    }

}
