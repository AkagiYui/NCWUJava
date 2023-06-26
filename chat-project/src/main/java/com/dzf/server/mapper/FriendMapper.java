package com.dzf.server.mapper;

import com.dzf.server.Database;
import com.dzf.server.entity.Friend;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author AkagiYui
 */

public class FriendMapper {
    public static List<String> getFriendNumberByNumber(String number) {
        Set<String> numbers = new HashSet<>();
        List<Friend> friends = Database.manyEntryExecute("select * from FRIEND where number1 = ? or number2 = ?", Friend.class, number, number);
        friends.forEach(f -> {
            if (f.getNumber1().equals(number)) {
                numbers.add(f.getNumber2());
            } else {
                numbers.add(f.getNumber1());
            }
        });
        return List.copyOf(numbers);
    }

    public static boolean isFriend(String number1, String number2) {
        Map<String, Object> map = Database.oneMapExecute("SELECT COUNT(*) FROM friend WHERE (number1 = ? AND number2 = ?) OR (number1 = ? AND number2 = ?)", number1, number2, number2, number1);
        return map.containsKey("COUNT(*)") && (Long) map.get("COUNT(*)") > 0;
    }

    public static boolean addFriend(String number1, String number2) {
        if (isFriend(number1, number2)) {
            return true;
        }
        return Database.jdbcExecute("insert into friend values (?, ?)", number1, number2) == 1;
    }

    public static boolean delFriend(String number1, String number2) {
        if (!isFriend(number1, number2)) {
            return true;
        }
        return Database.jdbcExecute("delete from friend where (number1 = ? and number2 = ?) or (number1 = ? and number2 = ?)", number1, number2, number2, number1) >= 1;
    }
}
