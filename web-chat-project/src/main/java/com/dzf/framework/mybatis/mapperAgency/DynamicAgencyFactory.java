package com.dzf.framework.mybatis.mapperAgency;


/**
 * 动态代理工厂
 * @author AkagiYui
 */
public class DynamicAgencyFactory {
    /**
     * 获取实体类代理
     * @param t 实体类
     * @return 实体类代理
     * @param <T> 实体类
     * @throws Exception 异常
     */
    public static <T> T getObjMapper(Class<T> t) throws Exception {
        //noinspection unchecked
        return (T) new DynamicAgency<>(t.getConstructor().newInstance()).getObject();
    }

    /**
     * 获取接口代理
     * @param t 接口类
     * @return 接口代理
     * @param <T> 接口类
     */
    public static <T> T getInfMapper(Class<T> t) {
        try {
            //noinspection unchecked
            return (T) new DynamicAgency<>(t).getInterface();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
