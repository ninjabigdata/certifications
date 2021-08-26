package balaji.hibernate.associationmapping.onetomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OneToManyService {

    @Autowired
    private CustomerOneToManyRepo customerRepo;

    public CustomerOneToMany save(CustomerOneToMany customer) {
        return customerRepo.saveAndFlush(customer);
    }

    public CustomerOneToMany findById(Integer id) {
        return customerRepo.findByIdAndFetchEager(id);
    }

    public void delete(Integer id) {
        customerRepo.deleteById(id);
    }

    @PostConstruct
    public void test() {
        System.out.println("From OneToManyService");

        CustomerOneToMany customer = new CustomerOneToMany();
        customer.setName("balaji");
        List<PhoneNumberManyToOne> phoneNumbers = new ArrayList<>();
        PhoneNumberManyToOne phoneNumber = new PhoneNumberManyToOne();
        phoneNumber.setNumber("9898987474");
        phoneNumber.setType("personal");
        phoneNumber.setCustomer(customer);
        phoneNumbers.add(phoneNumber);
        phoneNumber = new PhoneNumberManyToOne();
        phoneNumber.setNumber("9696968585");
        phoneNumber.setType("work");
        phoneNumber.setCustomer(customer);
        phoneNumbers.add(phoneNumber);
        customer.setPhoneNumbers(phoneNumbers);

        System.out.println("Saved customer details are " + save(customer));

        customer = findById(customer.getId());

        System.out.println("Customer details from DB is " + customer);

        customer.setName("Balaji Sridharan");
        customer.getPhoneNumbers().forEach(number -> number.setNumber(number.getNumber().replaceFirst("9", "8")));

        System.out.println("Updated customer details are " + save(customer));

        delete(customer.getId());

        System.out.println("Deleted the customer");
    }

}
