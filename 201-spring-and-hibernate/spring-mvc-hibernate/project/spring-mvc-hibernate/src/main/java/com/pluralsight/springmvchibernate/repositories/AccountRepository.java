package com.pluralsight.springmvchibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.springmvchibernate.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

}
