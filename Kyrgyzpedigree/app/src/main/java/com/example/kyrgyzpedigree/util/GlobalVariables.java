package com.example.kyrgyzpedigree.util;

import com.example.kyrgyzpedigree.models.PersonDto;

import java.util.List;

public class GlobalVariables {
    private static GlobalVariables globalVariables;
    private boolean isAdmin;
    private Class previousActivityForDeletePersonActivity;
    private String podrodNameForPreviousActivityInShowSanjyraTreeActivity;
    private List<PersonDto> personListFromSearch;
    private String personNameRegexFromSearch;

    public static GlobalVariables getInstance() {
        if (globalVariables == null) {
            globalVariables = new GlobalVariables();
        }
        return globalVariables;
    }

    public String getPersonNameRegexFromSearch() {
        return personNameRegexFromSearch;
    }

    public void setPersonNameRegexFromSearch(String personNameRegexFromSearch) {
        this.personNameRegexFromSearch = personNameRegexFromSearch;
    }

    public List<PersonDto> getPersonListFromSearch() {
        return personListFromSearch;
    }

    public void setPersonListFromSearch(List<PersonDto> personListFromSearch) {
        this.personListFromSearch = personListFromSearch;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Class getPreviousActivityForDeletePersonActivity() {
        return previousActivityForDeletePersonActivity;
    }

    public void setPreviousActivityForDeletePersonActivity(Class previousActivityForDeletePersonActivity) {
        this.previousActivityForDeletePersonActivity = previousActivityForDeletePersonActivity;
    }

    public String getPodrodNameForPreviousActivityInShowSanjyraTreeActivity() {
        String temp = podrodNameForPreviousActivityInShowSanjyraTreeActivity;
        podrodNameForPreviousActivityInShowSanjyraTreeActivity = null;
        return temp;
    }

    public void setPodrodNameForPreviousActivityInShowSanjyraTreeActivity(String podrodNameForPreviousActivityInShowSanjyraTreeActivity) {
        this.podrodNameForPreviousActivityInShowSanjyraTreeActivity = podrodNameForPreviousActivityInShowSanjyraTreeActivity;
    }
}
