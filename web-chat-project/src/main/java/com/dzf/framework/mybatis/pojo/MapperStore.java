package com.dzf.framework.mybatis.pojo;


import com.dzf.framework.mybatis.annotation.sql.One;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author AkagiYui
 */
@Data
@AllArgsConstructor
public class MapperStore {
    private String sql;
    private One one;
}
