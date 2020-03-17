package com.example.kyrgyzpedigree.models;

import com.example.kyrgyzpedigree.Person;

import java.util.List;

public class Podrod {
    private int id;
    private String name;
    private Rod rod;
    private List<Person> personList;

    public Podrod() {
    }

    public Podrod(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Podrod(String name) {
        this.name = name;
    }

    public Podrod(String name, Rod rod) {
        this.name = name;
        this.rod = rod;
    }

    public Podrod(String name, Rod rod, List<Person> personList) {
        this.name = name;
        this.rod = rod;
        this.personList = personList;
    }

    public Podrod(int id, String name, Rod rod, List<Person> personList) {
        this.id = id;
        this.name = name;
        this.rod = rod;
        this.personList = personList;
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

    public Rod getRod() {
        return rod;
    }

    public void setRod(Rod rod) {
        this.rod = rod;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

}
