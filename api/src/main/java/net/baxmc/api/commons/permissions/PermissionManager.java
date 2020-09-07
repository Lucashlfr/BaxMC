package net.baxmc.api.commons.permissions;

import org.bukkit.entity.Player;

public class PermissionManager {

    public enum Groups {
        ADMIN, MOD, SUP, DEV, VIP, PREMIUM, DEFAULT
    }

    public boolean hasPermission(final Player player, final String perm){
        return player.hasPermission(perm);
    }


}
