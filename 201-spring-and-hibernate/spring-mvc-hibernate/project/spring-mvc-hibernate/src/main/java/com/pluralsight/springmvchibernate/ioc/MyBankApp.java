package com.pluralsight.springmvchibernate.ioc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pluralsight.springmvchibernate.ioc.account.Account;

@Component
public class MyBankApp {

	@Autowired
	private Account savingAccount;
	@Autowired
	private Account currentAccount;

	@PostConstruct
	public void postConstruct() {
		System.out.println(savingAccount.createAccount());

		System.out.println(currentAccount.createAccount());
	}

}
