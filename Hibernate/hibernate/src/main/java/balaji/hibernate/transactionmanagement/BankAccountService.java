package balaji.hibernate.transactionmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepo repo;

    @Transactional
    public void transfer(int amount) {
        List<BankAccount> bankAccounts = new LinkedList<>();
        repo.findAll().forEach(account -> bankAccounts.add(account));
        BankAccount source = bankAccounts.get(0);

        source.setBalance(source.getBalance() - amount);
        repo.save(source);

        BankAccount destination = bankAccounts.get(2);

        destination.setBalance(destination.getBalance() + amount);

        repo.save(destination);
    }

}
