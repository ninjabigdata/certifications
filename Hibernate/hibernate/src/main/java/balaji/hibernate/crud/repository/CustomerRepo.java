package balaji.hibernate.crud.repository;

import balaji.hibernate.crud.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
