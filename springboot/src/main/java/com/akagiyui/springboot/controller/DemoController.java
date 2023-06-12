package com.akagiyui.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author AkagiYui
 */
@Controller
public class DemoController {
	@GetMapping("/welcome")
	public String welcome(Model model)	{
		model.addAttribute("message", "A silent man");
		return "welcome";
	}

	@GetMapping("/")
	public String index()	{
		return "index";
	}
}
