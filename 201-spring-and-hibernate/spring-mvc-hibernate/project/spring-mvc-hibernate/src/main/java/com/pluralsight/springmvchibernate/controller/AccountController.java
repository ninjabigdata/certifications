package com.pluralsight.springmvchibernate.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

	@GetMapping("/")
	public String showHomePage() {
		return "index";
	}

	@GetMapping("/new")
	public String newAccount() {
		return "newAccount";
	}

	@GetMapping("/showAccount")
	public String createAccount() {
		return "showAccount";
	}

	@PostMapping("/saveAccount")
	public String saveAccount(Model model, HttpServletRequest request) {
		String accountNumber = request.getParameter("accountNo");
		String accountHolderName = request.getParameter("accountHolderName");
		String accountBalance = request.getParameter("accountBalance");

		model.addAttribute("accountNo", accountNumber);
		model.addAttribute("accountHolderName", accountHolderName);
		model.addAttribute("accountBalance", accountBalance);
		return "showAccount";
	}

}
