package net.baxmc.api.commons.server.manager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event){
        final Player player = event.getPlayer();

    }

}
