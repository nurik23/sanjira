package kg.sanjyra.service;

import kg.sanjyra.model.Person;
import kg.sanjyra.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public void updatePerson(Person person) {
        personRepository.save(person);
    }

    public void deletePersonById(int id) {
        personRepository.deleteById(id);
    }

    public Person getPersonById(int id) {
        return personRepository.findById(id).orElse(new Person());
    }

    public List<Person> getPersonList() {
        return personRepository.findAll();
    }

    public List<Person> getPersonListByPodrod(String podrod) {
        return personRepository.findAllByPodrod(podrod);
    }
}
