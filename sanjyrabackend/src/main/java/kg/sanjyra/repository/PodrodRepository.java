package kg.sanjyra.repository;

import kg.sanjyra.model.Podrod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PodrodRepository extends JpaRepository<Podrod, Integer> {
    @Modifying
    @Query(value = "ALTER TABLE podrod CONVERT TO CHARACTER SET utf8",
            nativeQuery = true)
    void setPodrodTableCharsetUtf8();

    Podrod findPodrodByName(String name);
}
