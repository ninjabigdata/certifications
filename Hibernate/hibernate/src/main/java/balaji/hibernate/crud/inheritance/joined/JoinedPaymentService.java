package balaji.hibernate.crud.inheritance.joined;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class JoinedPaymentService {

    @Autowired
    private JoinedPaymentRepo repo;

    public List<CreditCardPaymentJoined> getCreditCardPayments() {
        return repo.findAllCreditCardPayments().orElse(null);
    }

    public List<CheckPaymentJoined> getCheckPayments() {
        return repo.findAllCheckPayments().orElse(null);
    }

    public CreditCardPaymentJoined save(CreditCardPaymentJoined creditCard) {
        return repo.save(creditCard);
    }

    public CheckPaymentJoined save(CheckPaymentJoined check) {
        return repo.save(check);
    }

    @PostConstruct
    public void test() {
        System.out.println("Credit Card Payments are: " + getCreditCardPayments());

        System.out.println("Check Payments are: " + getCheckPayments());

        CreditCardPaymentJoined creditCard = new CreditCardPaymentJoined();
        creditCard.setCardNumber("12345678901234567890");
        creditCard.setAmount(100.0);

        System.out.println("Credit Card payment added is " + save(creditCard));

        CheckPaymentJoined check = new CheckPaymentJoined();
        check.setCheckNumber("12345123451234512347");
        check.setAmount(100.0);

        System.out.println("Credit Card payment added is " + save(check));
    }

}
