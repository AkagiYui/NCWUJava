package com.akagiyui.jdbc;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PageUtil<T> {
    private static int currentPage=1;//当前页 默认第一页
    private static int showNumber=2;//展示条数 默认值
    private static int totalPage;//总页数
    private static int totalNumber;//数据总条数
    //展示数据 List<Map>
    private List<Map<String,Object>> listMap;
    //展示数据 List<T>
    private List<T> listEntry;

    //分页查询 返回封装的PagingUtil 展示条数是固定的
    //可变参数是针对limit前的占位符
    public static PageUtil getPagingPage(String sql,Class clazz,int currentPage,Object...obj){
        //声明一个对象
        PageUtil pag = new PageUtil();
        //首先判断但前页 是否符合 不符合默认第一页
        if(currentPage <=0){//修改当前页数据
            currentPage = 1;
        }
        //计算limit的起始索引位  起始位 = (当前页-1)*展示条数
        int startIndex =(currentPage-1)*showNumber;

        //给可变参数扩容 把limit的两个占位符 赋值调到后边
        Object[] obj1 = Arrays.copyOf(obj,obj.length+2);
        //把开始索引位占位符赋值
        obj1[obj1.length-2]=startIndex;
        //把展示条数赋值
        obj1[obj1.length-1]=showNumber;
        //调用封装好的PreParedStatement 返回List<T> 或 List<Map> 方法
        //判断Class参数 是否调用实体类映射
        if(clazz==null){
            //返回List<Map>
            List<Map<String,Object>> list = JDBCUtil.manyMapExecute(sql,obj1);
            //存储PagingUtil属性listMap
            pag.setListMap(list);
        }else {
            //list<T>
            List<Class> list = JDBCUtil.manyEntryExecute(sql,clazz,obj1);
            //存储PagingUtil属性listEntity
            pag.setListEntity(list);
        }

        //换算总页数
        //判断 总条数%展示条数 如果余数为0表示整除 否则就 整数+1
        //通过sql获取总条数
        sql = getSql(sql);//执行统计总条数的sql
        //执行sql
        Object totalNum = JDBCUtil.oneMapExecute(sql,obj).get("key");
        //valueOf传String类型 把 Object需要toString转成String类型
        int totalNumber = Integer.valueOf(totalNum.toString());
        //声明一个总页数
        int totalPage;
        if(totalNumber%showNumber==0){
            totalPage = totalNumber/showNumber;
        }else {
            totalPage= totalNumber/showNumber+1;
        }
        //把换算值赋给pag对象属性
        pag.setTotalPage(totalPage);
        //把当前页赋值pag对象属性
        pag.setCurrentPage(currentPage);
        //把总条数赋值
        pag.setTotalNumber(totalNumber);
        return pag;
    }
    //分页查询  展示条数是灵活的 根据页面的给定的几个展示条数改变
    public static PageUtil getPagingPage1(String sql,Class clazz,int currentPage,int showNumber,Object...obj){

        //声明一个对象
        PageUtil pag = new PageUtil();
        //首先判断但前页 是否符合 不符合默认第一页
        if(currentPage <=0){//修改当前页数据
            currentPage = 1;
        }
        //计算limit的起始索引位  起始位 = (当前页-1)*展示条数
        int startIndex =(currentPage-1)* showNumber;

        //给可变参数扩容 把limit的两个占位符 赋值调到后边
        Object[] obj1 = Arrays.copyOf(obj,obj.length+2);
        //把开始索引位占位符赋值
        obj1[obj1.length-2]=startIndex;
        //把展示条数赋值
        obj1[obj1.length-1]=showNumber;
        //调用封装好的PreParedStatement 返回List<T> 或 List<Map> 方法
        //判断Class参数 是否调用实体类映射
        if(clazz==null){
            //返回List<Map>
            List<Map<String,Object>> list = JDBCUtil.manyMapExecute(sql,obj1);
            //存储PagingUtil属性listMap
            pag.setListMap(list);
        }else {
            //list<T>
            List<Class> list = JDBCUtil.manyEntryExecute(sql,clazz,obj1);
            //存储PagingUtil属性listEntity
            pag.setListEntity(list);
        }

        //换算总页数
        //判断 总条数%展示条数 如果余数为0表示整除 否则就 整数+1
        //通过sql获取总条数
        sql = getSql(sql);//执行统计总条数的sql
        //执行sql
        Object totalNum = JDBCUtil.oneMapExecute(sql,obj).get("key");
        //valueOf传String类型 把 Object需要toString转成String类型
        int totalNumber = Integer.valueOf(totalNum.toString());
        //声明一个总页数
        int totalPage;
        if(totalNumber%showNumber==0){
            totalPage = totalNumber/showNumber;
        }else {
            totalPage= totalNumber/showNumber+1;
        }
        //把换算值赋给pag对象属性
        pag.setTotalPage(totalPage);
        //把当前页赋值pag对象属性
        pag.setCurrentPage(currentPage);
        //把总条数赋值
        pag.setTotalNumber(totalNumber);
        return pag;
    }

    //把sql拆成统计总条数的形式
    public static String getSql(String sql){
        //select count(1) from 表名 条件
        //把sql统一变成 小写形式
        int start = sql.toLowerCase().indexOf("from");
        //获取sql最外层查询的limit
        int end = sql.toLowerCase().lastIndexOf("limit");
        sql ="select count(1)\t'key'\t"+ sql.substring(start,end);
        return sql;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(int showNumber) {
        this.showNumber = showNumber;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Map<String, Object>> getListMap() {
        return listMap;
    }

    public void setListMap(List<Map<String, Object>> listMap) {
        this.listMap = listMap;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<T> getListEntity() {
        return listEntry;
    }

    public void setListEntity(List<T> listEntity) {
        this.listEntry = listEntity;
    }


}
