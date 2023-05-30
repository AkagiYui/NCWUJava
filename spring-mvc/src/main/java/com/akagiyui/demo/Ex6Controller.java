package com.akagiyui.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author AkagiYui
 */
@Controller
public class Ex6Controller {
	@GetMapping("/demo")
	public String welcome(Model model)	{
		model.addAttribute("name", "A silent man");
		return "welcome";
	}

	@GetMapping("/")
	public String index()	{
		return "index";
	}

	@GetMapping("/user")
	@ResponseBody
	public User test() {
		User user = new User();
		user.setUsername("zhangsan");
		user.setAge(18);
		return user;
	}

	@GetMapping("/demo2")
	public String welcome2(Model model, @RequestParam(name = "name") String name) {
		model.addAttribute("name", name);
		return "welcome";
	}

	@GetMapping("/demo3")
	public String welcome3(Model model, @RequestHeader(name = "User-Agent") String userAgent) {
		model.addAttribute("name", userAgent);
		return "welcome";
	}

	@GetMapping("/mycookie")
	@ResponseBody
	public String getCookieValue(@CookieValue(name = "mycookie", defaultValue = "default") String cookieValue) {
		return "The value of mycookie is: " + cookieValue;
	}

	@GetMapping("/hello")
	@ResponseBody
	public User helloWorld() {
		User user = new User();
		user.setUsername("ddi");
		user.setAge(6);
		return user;
	}

	@PostMapping("/return")
	@ResponseBody
	public User returnUser(@RequestBody User user) {
		user.setAge(user.getAge() + 1);
		return user;
	}
}
