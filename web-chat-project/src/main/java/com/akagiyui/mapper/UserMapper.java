package com.akagiyui.mapper;

import com.akagiyui.entity.User;
import com.dzf.framework.mybatis.annotation.Mapper;
import com.dzf.framework.mybatis.annotation.Param;
import com.dzf.framework.mybatis.annotation.sql.Select;

import java.util.List;

/**
 * User 数据库操作
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
    User selectUser(@Param("id") String number);

    /**
     * 根据学号和密码查询用户
     * @param number 学号
     * @param password 密码
     * @return 用户
     */
    @Select("select * from user where number = #{id} and password = #{pwd}")
    User selectUser(@Param("id") String number, @Param("pwd") String password);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Select("select * from user")
    List<User> selectUser();
}
