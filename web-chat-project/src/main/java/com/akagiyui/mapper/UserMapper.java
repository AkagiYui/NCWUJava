package com.akagiyui.mapper;

import com.akagiyui.entity.User;
import com.dzf.framework.mybatis.annotation.Mapper;
import com.dzf.framework.mybatis.annotation.sql.Select;

import java.util.List;

/**
 * UserMapper
 *
 * @author AkagiYui
 */
@Mapper
public interface UserMapper {

    /**
     * 根据学号查询用户
     * @param number 学号
     * @return 用户
     */
    @Select("select * from user where number = #{id}")
    User selectUser(String number);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Select("select * from user")
    List<User> selectUser();
}
