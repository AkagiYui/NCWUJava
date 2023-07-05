package com.dzf.framework.mybatis.pojo;

import lombok.Data;

/**
 * Mapper查询对象
 * @author AkagiYui
 */
@Data
public class MapperQuery {
    /**
     * 预编译的sql语句
     */
    private String sql;
    /**
     * sql参数
     */
    private String[] params;
    /**
     * 返回值类型
     */
    private Class<?> resultType;
    /**
     * id（方法名）
     */
    private String id;
}
