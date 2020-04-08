package com.pluralsight.springmvchibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class TestController {

	@GetMapping(value = { "/index", "", "index*", "view/*", "view" })
	public String index() {
		return "testRequestMapping";
	}

	@GetMapping("name")
	public String methodWithParams(@RequestParam(required = false, defaultValue = "Guest") String userName,
			Model model) {
		model.addAttribute("userName", userName);
		return "testRequestMapping";
	}

	@GetMapping("dynamic/{userName}")
	public String dynamicURL(@PathVariable("userName") String userName) {
		return "testRequestMapping";
	}

	@GetMapping("dynamic/{category:[0-9]{1,2}}/{userName}")
	public String dynamicURLAge(@PathVariable("userName") String userName) {
		return "testRequestMapping";
	}

	@GetMapping("*")
	public String fallBackPage() {
		return "pageNotFound";
	}

}
