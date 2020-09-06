package net.baxmc.lobbysystem.cache;

import net.baxmc.api.BaxAPI;
import net.baxmc.lobbysystem.LobbySystem;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ItemCache {

    private ItemStack navigator, soupModes, playerHiderVisible, playerHiderHided, soon;

    public ItemCache(){
        navigator = BaxAPI.getInstance().generateItem(Material.COMPASS)
                .setDisplayName(LobbySystem.getInstance().generateDisplayName("Navigator")).build();

        soupModes = BaxAPI.getInstance().generateItem(Material.MUSHROOM_SOUP)
                .setDisplayName(LobbySystem.getInstance().generateDisplayName("Soup Modes")).build();

        playerHiderVisible = BaxAPI.getInstance().generateItem(Material.GLASS_BOTTLE).setData((byte)2)
                .setDisplayName(LobbySystem.getInstance().generateDisplayName("PlayerHider visible")).build();
        playerHiderHided = BaxAPI.getInstance().generateItem(Material.GLASS_BOTTLE)
                .setDisplayName(LobbySystem.getInstance().generateDisplayName("PlayerHider visible")).build();


        soon = BaxAPI.getInstance().generateItem(Material.BARRIER)
                .setDisplayName(LobbySystem.getInstance().generateDisplayName("Soon")).build();
    }

    public ItemStack getNavigator() {
        return navigator;
    }

    public ItemStack getSoupModes() {
        return soupModes;
    }

    public ItemStack getPlayerHiderVisible() {
        return playerHiderVisible;
    }

    public ItemStack getPlayerHiderHided() {
        return playerHiderHided;
    }

    public ItemStack getSoon() {
        return soon;
    }

    public void setDefaultItems(final Player player, final boolean changeGamemode) {
        if (changeGamemode) {
            player.setGameMode(GameMode.SURVIVAL);
        }
        final Inventory inv = player.getInventory();

        inv.clear();
        inv.setItem(1, getNavigator());
        inv.setItem(2, getSoupModes());

        inv.setItem(6, getPlayerHiderVisible());
        inv.setItem(7, getSoon());

        if (player.hasPermission("Lobby.ExtraItems")) {
            inv.setItem(4, getSoon());
        }


        player.updateInventory();



    }

}
