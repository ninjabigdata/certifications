package com.pluralsight.springmvchibernate.ioc.account;

import org.springframework.stereotype.Component;

@Component
public class CurrentAccount implements Account {

	@Override
	public String createAccount() {
		return "Current Account created successfully";
	}

}
