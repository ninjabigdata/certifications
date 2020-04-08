package com.pluralsight.springmvchibernate.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String saveAccount(Model model, HttpServletRequest request) {
		int accountNumber = Integer.parseInt(request.getParameter("accountNo"));
		String accountHolderName = request.getParameter("accountHolderName");
		int accountBalance = Integer.parseInt(request.getParameter("accountBalance"));

		Account account = new Account(accountNumber, accountHolderName, accountBalance);
		model.addAttribute("account", account);
		return "showAccount";
	}

}
