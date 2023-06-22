package com.akagiyui.test;

import com.akagiyui.jdbc.PageUtil;

/**
 * @author AkagiYui
 */
public class Test02 {
    /**
     *Java创建类型:
     *    类、抽象类、接口、枚举
     *集合:
     *     Collection:子接口
     *        List:接口
     *            ArrayList:底层通过数组实现
     *            LinkedList:通过双向列表实现
     *        Set:接口
     *           HashSet:key-value可以为null
     *           TreeSet:key-value不可为null
     *     Map:
     *        HashMap:
     *        TreeMap:
     *
     *     Properties:
     *         1、通过key-value存储获取
     *         2、key-value都是String类型
     *         3、可以获取.properties文件
     *
     *   EnumMap:
     *        1、key是枚举对象
     *
     */
    public static void main(String[] args) throws Exception {

        //String sql = "select age from user where id = ?";
//         Map<String,Object> map = JDBCUtil.oneMapExecute(sql,1);
//        System.out.println(map);

//        String sql = "select id,name as 'username',age from user where id = ?";
//        User user = JDBCUtil.oneEntryExecute(sql, User.class,1);
//        System.out.println(user);

        //演示 分页分装
        String sql = "select id,name,age from user limit ?,?";
        PageUtil page = PageUtil.getPagingPage(sql,null,1);
        System.out.println(page.getCurrentPage());
        System.out.println(page.getListEntity());
        System.out.println(page.getListMap());
        System.out.println(page.getShowNumber());
    }

}
