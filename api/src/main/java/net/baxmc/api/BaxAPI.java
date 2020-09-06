package net.baxmc.api;

import net.baxmc.api.language.MultiLanguageAPI;
import net.baxmc.api.sql.SQLAdapter;
import net.baxmc.api.sql.SQLAuthentification;
import net.baxmc.api.utils.ItemBuilder;
import org.bukkit.Material;

public class BaxAPI {

    private static BaxAPI instance;
    private boolean bungeecord;

    private SQLAdapter sqlAdapter;
    private MultiLanguageAPI multiLanguageAPI;

    public BaxAPI(boolean bungeecord){
        instance = this;
        this.bungeecord = bungeecord;
        onEnable();
    }

    public static BaxAPI getInstance() {
        return instance;
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
}
