package balaji.hibernate.crud.inheritance.singletable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo repo;

    public List<CreditCard> getCreditCardPayments() {
        return repo.findAllCreditCardPayments().orElse(null);
    }

    public List<Check> getCheckPayments() {
        return repo.findAllCheckPayments().orElse(null);
    }

    public CreditCard save(CreditCard creditCard) {
        return repo.save(creditCard);
    }

    public Check save(Check check) {
        return repo.save(check);
    }

    @PostConstruct
    public void test() {
        System.out.println("Credit Card Payments are: " + getCreditCardPayments());

        System.out.println("Check Payments are: " + getCheckPayments());

        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("12345678901234567890");
        creditCard.setAmount(100.0);

        System.out.println("Credit Card payment added is " + save(creditCard));

        Check check = new Check();
        check.setCheckNumber("12345123451234512347");
        check.setAmount(100.0);

        System.out.println("Credit Card payment added is " + save(check));
    }

}
