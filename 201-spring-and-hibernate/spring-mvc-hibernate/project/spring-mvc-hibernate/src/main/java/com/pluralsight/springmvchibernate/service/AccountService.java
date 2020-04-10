package com.pluralsight.springmvchibernate.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pluralsight.springmvchibernate.entity.AccountEntity;
import com.pluralsight.springmvchibernate.model.Account;
import com.pluralsight.springmvchibernate.repositories.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public boolean saveAccount(Account account) {
		boolean isSaved = true;

		try {
			AccountEntity accountEntity = new AccountEntity();
			accountEntity.setAccountNumber(account.getAccountNo());
			accountEntity.setAccountHolderName(account.getAccountHolderName());
			accountEntity.setBalance(account.getAccountBalance());
			accountEntity.setAccountType(account.getAccountType());
			accountEntity.setDateOfBirth(account.getDateOfBirth());
			accountEntity.setPsCode(account.getPsCode());

			accountRepository.save(accountEntity);
		} catch (Exception exception) {
			isSaved = false;
		}

		return isSaved;
	}

	public List<Account> getAllAccounts() {
		return accountRepository.findAll().stream().map(accountEntity -> {
			Account account = new Account();
			account.setAccountNo(accountEntity.getAccountNumber());
			account.setAccountHolderName(accountEntity.getAccountHolderName());
			account.setAccountBalance(accountEntity.getBalance());
			return account;
		}).collect(Collectors.toList());
	}

	public Account getAccount(int accountNo) {
		AccountEntity accountEntity = accountRepository.getOne(accountNo);
		Account account = new Account();
		account.setAccountNo(accountEntity.getAccountNumber());
		account.setAccountHolderName(accountEntity.getAccountHolderName());
		account.setAccountBalance(accountEntity.getBalance());
		account.setAccountType(accountEntity.getAccountType());
		account.setDateOfBirth(accountEntity.getDateOfBirth());
		account.setPsCode(accountEntity.getPsCode());

		return account;
	}

	public void deleteAccount(int accountNo) {
		accountRepository.deleteById(accountNo);
	}

}
