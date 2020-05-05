package kg.sanjyra.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String mestojitelstva;
    private String godrojdeniya;
    private String namedad;
    private String namemom;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "podrod_id")
    private Podrod podrod;
    private String rodName;
    private String podrodName;


    public Person() {
    }

    public Person(String name, String email, String mestojitelstva, String godrojdeniya, String namedad, String namemom, Podrod podrod) {
        this.name = name;
        this.email = email;
        this.mestojitelstva = mestojitelstva;
        this.godrojdeniya = godrojdeniya;
        this.namedad = namedad;
        this.namemom = namemom;
        this.podrod = podrod;
    }

    public Person(int id, String name, String email, String mestojitelstva, String godrojdeniya, String namedad, String namemom, Podrod podrod) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mestojitelstva = mestojitelstva;
        this.godrojdeniya = godrojdeniya;
        this.namedad = namedad;
        this.namemom = namemom;
        this.podrod = podrod;
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

    public Podrod getPodrod() {
        return podrod;
    }

    public void setPodrod(Podrod podrod) {
        this.podrod = podrod;
    }

    public String getRodName() {
        return rodName;
    }

    public void setRodName(String rodName) {
        this.rodName = rodName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mestojitelstva='" + mestojitelstva + '\'' +
                ", godrojdeniya='" + godrojdeniya + '\'' +
                ", namedad='" + namedad + '\'' +
                ", namemom='" + namemom + '\'' +
                ", rodName='" + rodName + '\'' +
                ", podrodName='" + podrodName + '\'' +
                '}';
    }
}
