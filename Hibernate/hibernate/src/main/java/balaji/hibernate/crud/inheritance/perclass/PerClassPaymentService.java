package balaji.hibernate.crud.inheritance.perclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PerClassPaymentService {

    @Autowired
    private PerClassPaymentRepo repo;

    public List<CreditCardPayment> getCreditCardPayments() {
        return repo.findAllCreditCardPayments().orElse(null);
    }

    public List<CheckPayment> getCheckPayments() {
        return repo.findAllCheckPayments().orElse(null);
    }

    public CreditCardPayment save(CreditCardPayment creditCard) {
        return repo.save(creditCard);
    }

    public CheckPayment save(CheckPayment check) {
        return repo.save(check);
    }

    @PostConstruct
    public void test() {
        System.out.println("Credit Card Payments are: " + getCreditCardPayments());

        System.out.println("Check Payments are: " + getCheckPayments());

        CreditCardPayment creditCard = new CreditCardPayment();
        creditCard.setCardNumber("12345678901234567890");
        creditCard.setAmount(100.0);

        System.out.println("Credit Card payment added is " + save(creditCard));

        CheckPayment check = new CheckPayment();
        check.setCheckNumber("12345123451234512347");
        check.setAmount(100.0);

        System.out.println("Credit Card payment added is " + save(check));
    }

}
