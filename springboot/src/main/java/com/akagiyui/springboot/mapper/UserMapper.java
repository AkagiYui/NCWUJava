package com.akagiyui.springboot.mapper;

import com.akagiyui.springboot.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author AkagiYui
 */
@Mapper
public interface UserMapper {
	@Select("SELECT * FROM user WHERE id = #{id}")
	User getUser(@Param("id") long id);
	@Select("SELECT * FROM user")
	List<User> listUser();

	@Update("UPDATE user SET name = #{name}, age = #{age} WHERE id = #{id}")
	Boolean updateUser(User user);
	@Insert("INSERT INTO user(name, age) VALUES(#{name}, #{age})")
	Boolean insertUser(User user);
	@Delete("DELETE FROM user WHERE id = #{id}")
	Boolean deleteUser(@Param("id") long id);
}
