package com.akagiyui.springboot;

import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author AkagiYui
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
	// 创建线程安全的Map，模拟users信息的存储
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

	static {
		users.put(1L, new User(1L, "zhangsan",  18));
		users.put(2L, new User(2L, "lisi",  28));
	}

	@GetMapping("/")
	public List<User> getUserList() {
		return new ArrayList<>(users.values());
	}

	@PostMapping("/")
	public String postUser(@RequestBody User user) {
		users.put(user.getId(), user);

		return "success";
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		// User user = null;
		// user.setAge(10);
		return users.get(id);
	}

	@PutMapping("/{id}")
	public String putUser(@PathVariable Long id, @RequestBody User user) {
		User u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return "success";
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}
}
