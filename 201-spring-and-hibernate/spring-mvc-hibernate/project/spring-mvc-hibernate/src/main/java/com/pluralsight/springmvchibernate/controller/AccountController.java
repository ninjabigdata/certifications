package com.pluralsight.springmvchibernate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pluralsight.springmvchibernate.model.Account;
import com.pluralsight.springmvchibernate.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;

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
	public String saveAccount(@Valid @ModelAttribute("account") Account account, BindingResult result) {
		if (result.hasErrors()) {
			return "newAccount";
		} else {
			accountService.saveAccount(account);
			return "redirect:list";
		}
	}

	@GetMapping("/list")
	public String listAccounts(Model model) {
		model.addAttribute("accounts", accountService.getAllAccounts());
		return "listAccounts";
	}

	@GetMapping("edit")
	public String updateAccount(@RequestParam("accountNo") int accountNo, Model model) {
		model.addAttribute("account", accountService.getAccount(accountNo));

		return "newAccount";
	}

	@GetMapping("delete")
	public String deleteAccount(@RequestParam("accountNo") int accountNo) {
		accountService.deleteAccount(accountNo);

		return "redirect:list";
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, editor);
	}

}
