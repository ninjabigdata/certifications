package balaji.hibernate.crud.repository;

import balaji.hibernate.crud.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findAllByName(String name, Pageable pageable);

}
