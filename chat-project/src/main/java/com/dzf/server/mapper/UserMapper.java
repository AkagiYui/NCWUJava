package com.dzf.server.mapper;

import com.dzf.server.Database;
import com.dzf.server.entity.User;

import java.util.List;

/**
 * @author AkagiYui
 */

public class UserMapper {
    public static List<User> getAllUser() {
        return Database.manyEntryExecute("select * from user", User.class);
    }

    public static User getUserByNumber(String number) {
        return Database.oneEntryExecute("select * from user where number = ?", User.class, number);
    }
}
