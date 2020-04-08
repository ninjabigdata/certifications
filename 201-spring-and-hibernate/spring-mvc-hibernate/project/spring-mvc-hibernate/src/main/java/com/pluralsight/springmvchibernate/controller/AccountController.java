package com.pluralsight.springmvchibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pluralsight.springmvchibernate.model.Account;

@Controller
public class AccountController {

	@GetMapping("/")
	public String showHomePage() {
		return "index";
	}

	@GetMapping("/new")
	public String newAccount(Model model) {
		model.addAttribute("account", new Account());
		return "newAccount";
	}

	@GetMapping("/showAccount")
	public String createAccount() {
		return "showAccount";
	}

	@PostMapping("/saveAccount")
	public String saveAccount(Model model, @RequestParam("accountNo") Integer accountNumber,
			@RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("accountBalance") Integer accountBalance) {

		Account account = new Account(accountNumber, accountHolderName, accountBalance);
		model.addAttribute("account", account);
		return "showAccount";
	}

}
