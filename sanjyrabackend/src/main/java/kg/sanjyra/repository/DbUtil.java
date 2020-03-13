package kg.sanjyra.repository;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class DbUtil {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void setTablesCharsetUtf8() {
        entityManager.createNativeQuery("ALTER TABLE person CONVERT TO CHARACTER SET utf8;").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE rod CONVERT TO CHARACTER SET utf8;").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE podrod CONVERT TO CHARACTER SET utf8;").executeUpdate();
    }
}
