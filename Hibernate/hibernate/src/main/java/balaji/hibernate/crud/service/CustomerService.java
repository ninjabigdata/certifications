package balaji.hibernate.crud.service;

import balaji.hibernate.crud.entity.Customer;
import balaji.hibernate.crud.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer save(Customer customer) {
        return customerRepo.saveAndFlush(customer);
    }

    public Customer get(long id) {
        return  customerRepo.findById(id).orElse(null);
    }

    public Customer update(Customer customer) {
        Customer customerToBeUpdated = get(customer.getId());

        if (customerToBeUpdated == null) {
            return null;
        }

        customerToBeUpdated.setName(StringUtils.hasText(customer.getName()) ? customer.getName() : customerToBeUpdated.getName());
        customerToBeUpdated.setEmail(StringUtils.hasText(customer.getEmail()) ? customer.getEmail() : customerToBeUpdated.getEmail());

        return save(customerToBeUpdated);
    }

    public void delete(Long id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
        }
    }

}
