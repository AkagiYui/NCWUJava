package com.akagiyui.springboot.service;


import com.akagiyui.springboot.domain.User;

import java.util.List;

/**
 * @author AkagiYui
 */
public interface UserService {
	User getUser(long id);
	List<User> listUser();
	Boolean updateUser(User user);
	Boolean insertUser(User user);
	Boolean deleteUser(long id);
}
