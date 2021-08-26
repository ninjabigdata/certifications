package balaji.hibernate.crud.inheritance.singletable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepo extends CrudRepository<Payment, Integer> {

    @Query(value = "from CreditCard")
    Optional<List<CreditCard>> findAllCreditCardPayments();

    @Query(value = "from Check")
    Optional<List<Check>> findAllCheckPayments();

}
