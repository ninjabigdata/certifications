package com.pluralsight.conference.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pluralsight.conference.model.User;

@RestController
public class UserController {

	@GetMapping("/user")
	public User getUser(@RequestParam(value = "firstname", defaultValue = "Balaji") String firstName,
			@RequestParam(value = "lastname", defaultValue = "Sridharan") String lastName,
			@RequestParam(value = "age", defaultValue = "27") int age) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAge(age);

		return user;
	}

	@PostMapping("user")
	public User postUser(User user) {
		return user;
	}

}
