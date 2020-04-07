package com.pluralsight.springmvchibernate.ioc.account;

public class CurrentAccount implements Account {

	@Override
	public String createAccount() {
		return "Current Account created successfully";
	}

}
