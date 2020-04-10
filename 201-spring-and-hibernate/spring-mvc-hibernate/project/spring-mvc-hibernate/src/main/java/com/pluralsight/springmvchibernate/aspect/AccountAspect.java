package com.pluralsight.springmvchibernate.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pluralsight.springmvchibernate.model.Account;
import com.pluralsight.springmvchibernate.repositories.AccountRepository;

@Aspect
@Component
public class AccountAspect {

	@Autowired
	private AccountRepository accountRepository;

	@Before("execution (* com.pluralsight.springmvchibernate.service.AccountService.saveAccount(..))")
	public void validateAccount(JoinPoint joinPoint) {
		System.out.println(String.format("Invoking %s method", joinPoint.getSignature().toShortString()));
		Object[] args = joinPoint.getArgs();
		Account account = (Account) args[0];
		if (accountRepository.findById(account.getAccountNo()).isPresent()) {
			throw new RuntimeException(
					String.format("Account with this account number, %s, already exists", account.getAccountNo()));
		}
	}

}
