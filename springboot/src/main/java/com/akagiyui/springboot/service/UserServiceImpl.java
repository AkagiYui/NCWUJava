package com.akagiyui.springboot.service;


import com.akagiyui.springboot.domain.User;
import com.akagiyui.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkagiYui
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUser(long id) {
		return userMapper.getUser(id);
	}

	@Override
	public List<User> listUser() {
		return userMapper.listUser();
	}

	@Override
	public Boolean updateUser(User user) {
		return userMapper.updateUser(user);

	}

	@Override
	public Boolean insertUser(User user) {
		return userMapper.insertUser(user);
	}

	@Override
	public Boolean deleteUser(long id) {
		return userMapper.deleteUser(id);
	}
}
