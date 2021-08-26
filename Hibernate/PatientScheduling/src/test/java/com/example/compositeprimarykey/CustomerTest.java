package com.example.compositeprimarykey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerTest {

    @Autowired
    private CustomerRepo customerRepo;

//    @Test
//    public void testSaveCustomerUsingIdClass() {
//        Customer customer = new Customer();
//        customer.setId(1);
//        customer.setEmail("custone@shop.co.in");
//        customer.setName("Customer One");
//
//        customerRepo.save(customer);
//    }

    @Test
    public void testSaveCustomerUsingEmbeddedId() {
        Customer customer = new Customer();
        CustomerId customerId = new CustomerId();
        customerId.setId(2);
        customerId.setEmail("custtwo@shop.co.in");
        customer.setId(customerId);
        customer.setName("Customer Two");

        customerRepo.save(customer);
    }

}
