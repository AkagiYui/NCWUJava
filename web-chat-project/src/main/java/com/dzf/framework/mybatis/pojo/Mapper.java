package com.dzf.framework.mybatis.pojo;

import lombok.Data;

/**
 * Mapper对象
 * @author AkagiYui
 */
@Data
public class Mapper {
    private String namespace;
    private MapperQuery[] queries;
}
