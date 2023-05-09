package com.akagiyui.ex3;

import org.springframework.stereotype.Repository;

/**
 * @author AkagiYui
 */

// 目标类
@Repository
public class MyUserDaoImpl implements MyUserDao {
    @Override
    public void addUser() {
        System.out.println("MyUserDaoImpl - addUser");
    }

    @Override
    @MyLogAnnotation
    public void addUserAnnotated() {
        System.out.println("MyUserDaoImpl - addUserAnnotated: @MyLogAnnotation");
    }
}
