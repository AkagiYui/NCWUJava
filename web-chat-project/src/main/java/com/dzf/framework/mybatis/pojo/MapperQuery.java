package com.dzf.framework.mybatis.pojo;

import com.dzf.framework.mybatis.annotation.sql.One;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Mapper查询对象
 * @author AkagiYui
 */
@Data
@NoArgsConstructor
@Slf4j
public class MapperQuery {
    /**
     * 预编译的sql语句
     */
    private String sql;
    /**
     * sql参数名
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
        this.one = one;

        log.debug("构建sql: {}", sql);
        List<String> paramList = new ArrayList<>();
        Matcher matcher = Pattern.compile("#\\{([^}]+)}").matcher(sql); // 使用正则
        while (matcher.find()) {
            String val = matcher.group(1); // 匹配到的变量名
            log.debug("参数名:" + val);
            paramList.add(val);
        }
        this.params = paramList.toArray(new String[0]);

        this.sql = sql.replaceAll("#\\{([^}]+)}", "?");
    }
}
