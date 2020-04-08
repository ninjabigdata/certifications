package com.pluralsight.springmvchibernate.model;

public class Account {

	private int accountNo;
	private String accountHolderName;
	private int accountBalance;

	public Account() {
		super();
		accountNo = 0;
		accountHolderName = "";
		accountBalance = 0;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Account(int accountNo, String accountHolderName, int accountBalance) {
		super();
		this.accountNo = accountNo;
		this.accountHolderName = accountHolderName;
		this.accountBalance = accountBalance;
	}

}
