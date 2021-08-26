package balaji.hibernate.crud.inheritance.joined;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JoinedPaymentRepo extends CrudRepository<PaymentJoined, Integer> {

    @Query(value = "from CreditCardPaymentJoined")
    Optional<List<CreditCardPaymentJoined>> findAllCreditCardPayments();

    @Query(value = "from CheckPaymentJoined")
    Optional<List<CheckPaymentJoined>> findAllCheckPayments();

}
