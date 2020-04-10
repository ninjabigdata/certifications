package com.pluralsight.springmvchibernate.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.pluralsight.springmvchibernate.validations.PSCode;

public class Account {

	@NotNull(message = "Account # cannot be blank")
	private Integer accountNo;
	@NotNull(message = "Account Holder Name cannot be blank")
	@Size(min = 2, max = 50, message = "Invalid length for Account Holder Name")
	@Pattern(regexp = "[A-Za-z(\\s)]+", message = "Invalid Account Holder Name")
	private String accountHolderName;
	@NotNull(message = "Account Balance cannot be blank")
	@Min(value = 5000, message = "Minimum balance required is 5000")
	private Integer accountBalance;
	@NotNull(message = "Account Type cannot be blank")
	private String accountType;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@NotNull(message = "Date of Birth cannot be blank")
	@Past(message = "Account cannot be opened for person not born")
	private Date dateOfBirth;
	@NotNull(message = "PS Code cannot be blank")
	@PSCode(value = "PSNEW", message = "PS Code should start with PSNEW")
	private String psCode;

	public Account() {
		super();
		accountNo = 0;
		accountHolderName = "";
		accountBalance = 0;
		accountType = "";
		dateOfBirth = new Date();
		psCode = "";
	}

	public Account(int accountNo, String accountHolderName, int accountBalance) {
		super();
		this.accountNo = accountNo;
		this.accountHolderName = accountHolderName;
		this.accountBalance = accountBalance;
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

}
