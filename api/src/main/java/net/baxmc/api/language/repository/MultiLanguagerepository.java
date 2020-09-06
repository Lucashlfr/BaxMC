package net.baxmc.api.language.repository;

import net.baxmc.api.language.Languages;
import net.baxmc.api.sql.SQLAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MultiLanguagerepository implements IMultiLanguagerepository {

    private SQLAdapter sqlAdapter;

    public MultiLanguagerepository(SQLAdapter sqlAdapter){
        this.sqlAdapter = sqlAdapter;
    }

    public Map<Languages, UUID> getLanguages() {
        final ResultSet resultSet = sqlAdapter.getResult("SELECT * FROM api_language_languages");
        final Map<Languages, UUID> languagesUUIDMap = new HashMap<Languages, UUID>();

        try{

            while (resultSet.next()){
                languagesUUIDMap.put(Languages.valueOf(resultSet.getString("language")),
                        UUID.fromString(resultSet.getString("language_id")));
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return languagesUUIDMap;
    }

    public void addMessage(int id, String message, UUID languageId) {
        String query = "INSERT INTO api_language_languages (id, message_id, message, language_id) VALUES ('" + UUID.randomUUID() +
                "','" + id + "','" + message + "','" + languageId + "')";
        this.sqlAdapter.sendQuery(query);
    }
}
