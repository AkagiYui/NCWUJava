package com.akagiyui.springboot.controller;

import com.akagiyui.springboot.domain.User;
import com.akagiyui.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author AkagiYui
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * 获取一个用户
	 */
	@GetMapping("/{id}")
	public User getOne(@PathVariable int id) {
		return userService.getUser(id);
	}

	/**
	 * 获取用户列表
	 */
	@GetMapping("/")
	public List<User> getUserList() {
		return userService.listUser();
	}

	/**
	 * 创建一个用户
	 */
	@PostMapping("/")
	public String postUser(@RequestBody User user) {
		return userService.insertUser(user) ? "success" : "fail";
	}

	/**
	 * 更新一个用户
	 */
	@PutMapping("/{id}")
	public String putUser(@PathVariable Long id, @RequestBody User user) {
		User u = userService.getUser(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		return userService.updateUser(u) ? "success" : "fail";
	}

	/**
	 * 删除一个用户
	 */
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id) ? "success" : "fail";
	}
}
