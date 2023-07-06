package com.dzf.framework.mybatis.pojo;

import com.dzf.framework.mybatis.annotation.sql.One;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Mapper查询对象
 * @author AkagiYui
 */
@Data
@NoArgsConstructor
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
    private One one;

    public MapperQuery(String sql, One one) {
        this.sql = sql;
        this.one = one;
    }
}
