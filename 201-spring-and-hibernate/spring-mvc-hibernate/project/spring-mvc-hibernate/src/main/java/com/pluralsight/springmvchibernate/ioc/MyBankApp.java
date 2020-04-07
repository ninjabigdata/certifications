package com.pluralsight.springmvchibernate.ioc;

import com.pluralsight.springmvchibernate.ioc.account.Account;
import com.pluralsight.springmvchibernate.ioc.account.CurrentAccount;
import com.pluralsight.springmvchibernate.ioc.account.SavingAccount;

public class MyBankApp {

	public static void main(String[] args) {
		Account savingAccount = new SavingAccount();
		System.out.println(savingAccount.createAccount());

		Account currentAccount = new CurrentAccount();
		System.out.println(currentAccount.createAccount());
	}

}
