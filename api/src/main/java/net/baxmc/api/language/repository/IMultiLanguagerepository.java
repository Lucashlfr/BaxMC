package net.baxmc.api.language.repository;

import net.baxmc.api.language.Languages;

import java.util.Map;
import java.util.UUID;

public interface IMultiLanguagerepository {

    void addMessage(int id, String message, UUID languageId);
    Map<Languages, UUID> getLanguages();
}
