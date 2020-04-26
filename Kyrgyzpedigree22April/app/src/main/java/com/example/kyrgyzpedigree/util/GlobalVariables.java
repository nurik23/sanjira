package com.example.kyrgyzpedigree.util;

public class GlobalVariables {
    private static GlobalVariables globalVariables;
    private boolean isAdmin;
    private Class previousActivityForDeletePersonActivity;
    private String podrodNameForPreviousActivityInShowSanjyraTreeActivity;

    public static GlobalVariables getInstance() {
        if (globalVariables == null) {
            globalVariables = new GlobalVariables();
        }
        return globalVariables;
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
