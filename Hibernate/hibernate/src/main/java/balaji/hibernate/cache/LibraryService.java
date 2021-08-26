package balaji.hibernate.cache;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepo repo;
    @Autowired
    private EntityManager entityManager;

    public Library get(Integer id) {
        return repo.findById(id).orElseGet(null);
    }

    @PostConstruct
    @Transactional
    public void test() {
        System.out.println("From LibraryService - caching");

        Session session = entityManager.unwrap(Session.class);

        Library library = get(1);

        get(1);

        session.evict(library);

        get(1);
    }



}
