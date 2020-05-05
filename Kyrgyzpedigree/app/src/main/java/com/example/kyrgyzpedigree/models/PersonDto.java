package com.example.kyrgyzpedigree.models;

import java.util.Map;

public class PersonDto {
    private int id;
    private String name;
    private String email;
    private String mestojitelstva;
    private String godrojdeniya;
    private String namedad;
    private String namemom;
    private String podrodName;
    private String rodName;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.email = person.getEmail();
        this.mestojitelstva = person.getMestojitelstva();
        this.godrojdeniya = person.getGodrojdeniya();
        this.namedad = person.getNamedad();
        this.namemom = person.getNamemom();
    }

    public PersonDto(Map<String, Object> map) {
        this.id = (int) map.get("id");
        this.name = (String) map.get("name");
        this.email = (String) map.get("email");
        this.mestojitelstva = (String) map.get("mestojitelstva");
        this.godrojdeniya = (String) map.get("godrojdeniya");
        this.namedad = (String) map.get("namedad");
        this.namemom = (String) map.get("namemom");
        this.podrodName = (String) map.get("podrodName");
        this.rodName = (String) map.get("rodName");
    }

    public String getRodName() {
        return rodName;
    }

    public void setRodName(String rodName) {
        this.rodName = rodName;
    }

    public String getPodrodName() {
        return podrodName;
    }

    public void setPodrodName(String podrodName) {
        this.podrodName = podrodName;
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
        String result = "ID :" + id +
                "\n" + "ФИО :\t" + name +
                "\n" + "Почта :\t" + email +
                "\n" + "Место проживания :\t" + mestojitelstva +
                "\n" + "Дата рождения :\t" + godrojdeniya +
                "\n" + "ФИО отца :\t" + namedad +
                "\n" + "ФИО матери :\t" + namemom;
        if (podrodName != null) {
            result += "\n" + "Подрод :\t" + podrodName;
        }
        if (rodName != null) {
            result += "\n" + "Род :\t" + rodName;
        }
        result += "\n\n";
        return result;
    }
}
