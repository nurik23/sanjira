package kg.sanjyra.repository;

import kg.sanjyra.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Modifying
    @Query(value = "ALTER TABLE person CONVERT TO CHARACTER SET utf8",
            nativeQuery = true)
    void setPersonTableCharsetUtf8();

    List<Person> findAllByPodrodName(String podrodName);
}
