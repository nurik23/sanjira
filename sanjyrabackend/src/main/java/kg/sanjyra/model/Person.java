package kg.sanjyra.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private String rod;
    private String podrod;

    public Person() {
    }

    public Person(String name, String email, String mestojitelstva, String godrojdeniya, String namedad, String namemom, String rod, String podrod) {
        this.name = name;
        this.email = email;
        this.mestojitelstva = mestojitelstva;
        this.godrojdeniya = godrojdeniya;
        this.namedad = namedad;
        this.namemom = namemom;
        this.rod = rod;
        this.podrod = podrod;
    }

    public Person(int id, String name, String email, String mestojitelstva, String godrojdeniya, String namedad, String namemom, String rod, String podrod) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mestojitelstva = mestojitelstva;
        this.godrojdeniya = godrojdeniya;
        this.namedad = namedad;
        this.namemom = namemom;
        this.rod = rod;
        this.podrod = podrod;
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

    public String getRod() {
        return rod;
    }

    public void setRod(String rod) {
        this.rod = rod;
    }

    public String getPodrod() {
        return podrod;
    }

    public void setPodrod(String podrod) {
        this.podrod = podrod;
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
                ", rod='" + rod + '\'' +
                ", podrod='" + podrod + '\'' +
                '}';
    }
}
