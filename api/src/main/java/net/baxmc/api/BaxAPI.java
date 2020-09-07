package net.baxmc.api;

import net.baxmc.api.language.MultiLanguageAPI;
import net.baxmc.api.sql.SQLAdapter;
import net.baxmc.api.sql.SQLAuthentification;
import net.baxmc.api.utils.ItemBuilder;
import org.bukkit.Material;

public class BaxAPI {

    private static BaxAPI baxAPI;
    private boolean bungeecord;
    private String errorPrefix, warningPrefix, infoPrefix, prefix;

    private SQLAdapter sqlAdapter;
    private MultiLanguageAPI multiLanguageAPI;

    public BaxAPI(boolean bungeecord){
        baxAPI = this;
        this.bungeecord = bungeecord;

        this.errorPrefix = "§8[§4§lError§8] §f";
        this.warningPrefix = "§8[§6§lWarning§8] §f";
        this.infoPrefix = "§8[§2§lInformation§8] §f";
        this.prefix = "§8[§f§lBaxMC§8] §f";

        onEnable();
    }

    public static BaxAPI getInstance() {
        return baxAPI;
    }

    private void onEnable(){
        final SQLAuthentification sqlAuthentification = new SQLAuthentification("127.0.0.1", "baxmc","admin", "root", 3306);
        this.sqlAdapter = new SQLAdapter(sqlAuthentification);
        this.multiLanguageAPI = new MultiLanguageAPI(this.sqlAdapter);
    }

    public SQLAdapter getSqlAdapter() {
        return sqlAdapter;
    }

    public MultiLanguageAPI getMultiLanguageAPI() {
        return multiLanguageAPI;
    }

    public ItemBuilder generateItem(Material material) {
        return new ItemBuilder(material);
    }

    public static BaxAPI getBaxAPI() {
        return baxAPI;
    }

    public boolean isBungeecord() {
        return bungeecord;
    }

    public String getErrorPrefix() {
        return errorPrefix;
    }

    public String getWarningPrefix() {
        return warningPrefix;
    }

    public String getInfoPrefix() {
        return infoPrefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
