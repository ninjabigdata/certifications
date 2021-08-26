package balaji.hibernate.transactionmanagement;

import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepo extends CrudRepository<BankAccount, Integer> {
}
