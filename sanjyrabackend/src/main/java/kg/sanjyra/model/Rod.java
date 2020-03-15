package kg.sanjyra.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Rod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "rod", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    @Override
    public String toString() {
        return "Rod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", podrodListSize=" + podrodList.size() +
                '}';
    }
}
