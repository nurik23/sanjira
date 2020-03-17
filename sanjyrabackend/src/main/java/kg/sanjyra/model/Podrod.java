package kg.sanjyra.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class Podrod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "rod_id")
    private Rod rod;
    @JsonManagedReference
    @JsonIgnore
    @Fetch(value = FetchMode.JOIN)
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

    @Override
    public String toString() {
        return "Podrod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rod=" + rod.getName() +
                '}';
    }
}
