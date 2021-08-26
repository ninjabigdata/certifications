package balaji.hibernate.associationmapping.manytomany;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProgrammerRepo extends CrudRepository<Programmer, Integer> {

    @Query("FROM Programmer p join fetch p.projects WHERE p.id = :id")
    Optional<Programmer> eagerFetch(@Param("id") Integer id);

}
