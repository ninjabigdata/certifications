package balaji.hibernate.associationmapping.onetomany;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerOneToManyRepo extends JpaRepository<CustomerOneToMany, Integer> {

    @Query("FROM CustomerOneToMany c JOIN FETCH c.phoneNumbers phoneNumbers WHERE c.id = :id")
    public CustomerOneToMany findByIdAndFetchEager(@Param("id") Integer id);

}
