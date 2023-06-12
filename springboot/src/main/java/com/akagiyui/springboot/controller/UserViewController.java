package com.akagiyui.springboot.controller;

import com.akagiyui.springboot.domain.User;
import com.akagiyui.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author AkagiYui
 */
@Controller
@RequestMapping("/users2")
public class UserViewController {

	@Autowired
	UserService userService;

	/**
	 * 获取一个用户
	 */
	@GetMapping("/{id}")
	public String getOne(@PathVariable int id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		return "user_view";
	}

	/**
	 * 获取用户列表
	 */
	@GetMapping("/")
	public String getUserList(Model model) {
		List<User> userList = userService.listUser();
		model.addAttribute("userList", userList);
		return "user_view";
	}

	/**
	 * 创建一个用户
	 */
	@PostMapping("/")
	public String postUser(@ModelAttribute User user) {
		return userService.insertUser(user) ? "redirect:/users2/" : "fail";
	}

	/**
	 * 更新一个用户
	 */
	@PostMapping("/{id}/update")
	public String putUser(@PathVariable Long id, @ModelAttribute User user) {
		User u = userService.getUser(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		return userService.updateUser(u) ? "redirect:/users2/" + id : "fail";
	}

	/**
	 * 删除一个用户
	 */
	@PostMapping("/{id}/delete")
	public String deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id) ? "redirect:/users2/" : "fail";
	}
}
