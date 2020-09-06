package net.baxmc.api.language;

import net.baxmc.api.sql.SQLAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MultiLanguageAPI {

    private SQLAdapter sqlAdapter;
    private final Map<Languages, UUID> languagesMap = new HashMap<Languages, UUID>();

    public MultiLanguageAPI(SQLAdapter sqlAdapter){
        this.sqlAdapter = sqlAdapter;

        createDatabase();
        loadLanguages();
        loadMessages();

    }

    private void createDatabase(){
        getSqlAdapter().sendQuery("CREATE TABLE IF NOT EXISTS api_language_messages (id VARCHAR(100), message_id INT(100), message VARCHAR(1000), language_id VARCHAR(100))");
        getSqlAdapter().sendQuery("CREATE TABLE IF NOT EXISTS api_language_languages (language VARCHAR(100), language_id VARCHAR(100))");
    }

    private void loadMessages(){

    }

    private void loadLanguages(){

    }

    public SQLAdapter getSqlAdapter() {
        return sqlAdapter;
    }
}
