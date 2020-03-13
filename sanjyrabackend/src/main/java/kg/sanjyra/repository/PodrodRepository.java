package kg.sanjyra.repository;

import kg.sanjyra.model.Podrod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PodrodRepository extends JpaRepository<Podrod, Integer> {

    Podrod findPodrodByNameLike(String name);

    List<Podrod> findPodrodsByRodNameLike(String rodName);
}
