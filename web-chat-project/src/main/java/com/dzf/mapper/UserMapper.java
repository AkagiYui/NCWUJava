package com.dzf.mapper;

import com.dzf.entity.User;
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

    @Select("select * from user")
    List<User> selectAll();
}
