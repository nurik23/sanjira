package kg.sanjyra.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Podrod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "rod_id")
    private Rod rod;
    @OneToMany(mappedBy = "podrod", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Person> personList;

    public Podrod() {
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
