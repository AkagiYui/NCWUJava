package com.dzf.framework.mybatis.mapperAgency;


public class DynamicAgencyFaction {
    //实体类
    public static <T> T getObjMapper(Class<T> t) throws Exception {
        return (T)new DynamicAgency(t.getConstructor().newInstance()).getObject();
    }
    //接口
    public static <T> T getInfMapper(Class<T> t)  {
        try {
            return (T)new DynamicAgency(t).getInterface();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
