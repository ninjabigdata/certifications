package com.pluralsight.springmvchibernate.ioc.account;

import org.springframework.stereotype.Component;

@Component
public class SavingAccount implements Account {

	@Override
	public String createAccount() {
		return "Saving account created successfully";
	}

}
