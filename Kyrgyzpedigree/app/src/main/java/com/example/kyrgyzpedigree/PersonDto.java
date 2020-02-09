package com.example.kyrgyzpedigree;

import java.util.LinkedHashMap;
import java.util.Map;

public class PersonDto {
    private int id;
    private String name;
    private String email;
    private String mestojitelstva;
    private String godrojdeniya;
    private String namedad;
    private String namemom;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.email = person.getEmail();
        this.mestojitelstva = person.getMestojitelstva();
        this.godrojdeniya = person.getGodrojdeniya();
        this.namedad = person.getNamedad();
        this.namemom = person.getNamemom();
    }

    public PersonDto(Map<String, String> map) {
        this.id = Integer.parseInt(map.get("id"));
        this.name = map.get("name");
        this.email = map.get("email");
        this.mestojitelstva = map.get("mestojitelstva");
        this.godrojdeniya = map.get("godrojdeniya");
        this.namedad = map.get("namedad");
        this.namemom = map.get("namemom");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMestojitelstva() {
        return mestojitelstva;
    }

    public void setMestojitelstva(String mestojitelstva) {
        this.mestojitelstva = mestojitelstva;
    }

    public String getGodrojdeniya() {
        return godrojdeniya;
    }

    public void setGodrojdeniya(String godrojdeniya) {
        this.godrojdeniya = godrojdeniya;
    }

    public String getNamedad() {
        return namedad;
    }

    public void setNamedad(String namedad) {
        this.namedad = namedad;
    }

    public String getNamemom() {
        return namemom;
    }

    public void setNamemom(String namemom) {
        this.namemom = namemom;
    }

    @Override
    public String toString() {
        return
                "ID=" + id +
                        "\n"+"Full name:\t" + name +
                        "\n"+"Email:\t" + email +
                        "\n"+"Address:\t" + mestojitelstva +
                        "\n"+"Year of birth:\t" + godrojdeniya +
                        "\n"+"Dad's name:\t" + namedad +
                        "\n"+"Mom's name:\t" + namemom +"\n"
                       ;
    }
}
