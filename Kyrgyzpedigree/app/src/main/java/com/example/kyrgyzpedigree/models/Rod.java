package com.example.kyrgyzpedigree.models;

import java.util.List;

public class Rod {
    private int id;
    private String name;
    private List<Podrod> podrodList;

    public Rod() {
    }

    public Rod(String name) {
        this.name = name;
    }


    public Rod(String name, List<Podrod> podrodList) {
        this.name = name;
        this.podrodList = podrodList;
    }

    public Rod(int id, String name, List<Podrod> podrodList) {
        this.id = id;
        this.name = name;
        this.podrodList = podrodList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Podrod> getPodrodList() {
        return podrodList;
    }

    public void setPodrodList(List<Podrod> podrodList) {
        this.podrodList = podrodList;
    }
}
