package com.pluralsight.springmvchibernate.controller;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public String saveAccount(@Valid @ModelAttribute("account") Account account, BindingResult result) {
		if (result.hasErrors()) {
			return "newAccount";
		} else {
			return "showAccount";
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		webDataBinder.registerCustomEditor(String.class, editor);
	}

}
