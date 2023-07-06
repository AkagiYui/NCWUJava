package com.dzf.framework.mybatis.mapperAgency;


import com.dzf.framework.mybatis.Database;
import com.dzf.framework.mybatis.Mybatis;
import com.dzf.framework.mybatis.annotation.sql.One;
import com.dzf.framework.mybatis.pojo.MapperStore;


import java.lang.reflect.*;
import java.util.Map;

/**
 * 动态代理执行注解sql
 *
 * @author AkagiYui
 */
public class DynamicAgency<T> implements InvocationHandler {
    private Object obj;//代理对象
    private Class<T> inf;//代理接口
    //获取代理接口
    private static final Map<String, MapperStore> map;
    static {
        try {
            map = Mybatis.parseMapperClass("com.akagiyui.mapper");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DynamicAgency(Object obj) {
        this.obj =  obj;
    }
    public  DynamicAgency(Class<T> inf) {
        this.inf =  inf;
    }

    public  Object getInterface(){
        return Proxy.newProxyInstance(inf.getClassLoader(),
                new Class[]{inf},this);
    }

    public  Object getObject(){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println((method.getName())+"============================");
        if(!"toString".equals(method.getName())){
            if (obj != null){//代理对象
                return method.invoke(obj,args);
            }else {
                //包名.类名.方法名
                String key = method.getDeclaringClass().getName()+"."+method.getName();
                MapperStore ms = map.get(key);
                //获取sql
                String sql = ms.getSql();
                //判断获取的sql是否是空字符串
                if(!"".equals(sql)){
                    //获取类型名
                    String typeName = method.getReturnType().getTypeName();
                    //判断
                    if("java.util.Map".equals(typeName)){
                        //返回Map
                        return Database.oneMapExecute(sql,args);
                    }else if("java.util.List".equals(typeName)){
                        Type type = method.getGenericReturnType();
                        if("java.util.List<java.util.Map<java.lang.String, java.lang.Object>>".equals(type.getTypeName())){
                            return Database.manyMapExecute(sql,args);
                        }else {
                            if(type instanceof ParameterizedType){
                                //获取实际类型参数 getActualTypeArguments()
                                Type[] ty = ((ParameterizedType) type).getActualTypeArguments();
                                return Database.manyEntryExecute(sql,Class.forName(ty[0].getTypeName()),args);
                            }
                        }
                    }else {
                        //判断是否做一对多关系
                        One one = ms.getOne();
                        if("".equals(one.select())){
                            return Database.oneEntryExecute(sql,Class.forName(typeName),args);
                        }
                        //获取一对多关系的sql
                        Class clazz = Class.forName(typeName);
                        Field[] fields = clazz.getDeclaredFields();
                        //获取对应的返回类型
                        String path = one.path();
                        //包名.类名
                        String clazzPath=path.substring(0,path.lastIndexOf("."));
                        //方法名
                        String methodPath=path.substring((path.lastIndexOf(".")+1),path.length());
                        Class oneClazz = Class.forName(clazzPath);
                        Method oneMethod = oneClazz.getMethod(methodPath);
                        return Database.getOneToManySelect(sql,clazz,one.select(),one.id(),oneClazz,oneMethod,args);
                    }
                }
                return null;
            }
        }
        return null;
    }

}
