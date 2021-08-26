package com.example.compositeprimarykey;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, CustomerId> {
}
