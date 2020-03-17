package kg.sanjyra.repository;

import kg.sanjyra.model.Rod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RodRepository extends JpaRepository<Rod, Integer> {

    @Modifying
    @Query(value = "ALTER TABLE rod CONVERT TO CHARACTER SET utf8",
            nativeQuery = true)
    void setRodTableCharsetUtf8();

    Rod findRodByName(String name);
}
