package balaji.hibernate.crud.inheritance.perclass;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PerClassPaymentRepo extends CrudRepository<PaymentPerClass, Integer> {

    @Query(value = "from CreditCardPayment")
    Optional<List<CreditCardPayment>> findAllCreditCardPayments();

    @Query(value = "from CheckPayment")
    Optional<List<CheckPayment>> findAllCheckPayments();

}
