package com.pluralsight.springmvchibernate.ioc.account;

public class SavingAccount implements Account {

	@Override
	public String createAccount() {
		return "Saving account created successfully";
	}

}
