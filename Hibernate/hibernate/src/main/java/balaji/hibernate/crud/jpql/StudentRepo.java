package balaji.hibernate.crud.jpql;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends CrudRepository<Student, Long> {

    @Query("from Student")
    Optional<List<Student>> findAllStudents(Pageable pageable);

    @Query("SELECT st.firstName, st.lastName from Student st")
    Optional<List<Object[]>> findAllNames();

    @Query("from Student WHERE lastName = :lastName")
    Optional<List<Student>> findAllByLastName(@Param("lastName") String lastName);

    @Query("from Student WHERE score BETWEEN :min AND :max")
    Optional<List<Student>> findAllBetweenScores(@Param("min") int min, @Param("max") int max);

    @Transactional
    @Modifying
    @Query("delete from Student where firstName = :firstName")
    void deleteByFirstName(@Param("firstName") String firstName);

}
