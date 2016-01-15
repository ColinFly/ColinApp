package com.example.java_annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by colin on 16-1-15.
 */
public class Test {
    public static void main(String[] args) {
        Filter f1=new Filter();
        f1.setId(10);

        Filter f2=new Filter();
        f2.setName("lucy");

        Filter f3 = new Filter();
        f3.setEmail("colin@sina.com");


        Filter2 filter2=new Filter2();
        filter2.setId(2);

        Filter2 filter3=new Filter2();
        filter3.setName("colin");


        try {
            System.out.println(query(filter2));
            System.out.println(query(filter3));
//            System.out.println(query(f1));//查询id为10的用户
//            System.out.println(query(f2));//模糊查询用户名
//            System.out.println(query(f3));//查询邮箱为任意一个的用户
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String query(Object filter) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringBuilder sb=new StringBuilder();
        //1.获取到class
        Class clazz = filter.getClass();
        //获取到table 的名字
        boolean isCExist = clazz.isAnnotationPresent(Table.class);
        if (!isCExist) {
            return null;
        }
        Table table = (Table) clazz.getAnnotation(Table.class);
        String tableName = table.value();
        sb.append("select * from ").append(tableName).append(" where 1=1");
        //3.遍历所有的字段
        Field[] fileds=clazz.getDeclaredFields();
        for (Field field : fileds) {
            //4.处理每个字段对应的sql
            //4.1拿到字段名
            boolean isFExist = field.isAnnotationPresent(Column.class);
            if (!isFExist) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
//            System.out.println(columnName);
            //4.2拿到字段值
//            String filedName = field.getName();
                //第一个字母取出来大写(获取方法名以实现动态调用)
            String getMethodName = "get" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);//取第一个之后的所有
//            System.out.println(getMethodName);
            Method getMethod = clazz.getMethod(getMethodName);
//            System.out.println(getMethod);
            //其实就是循环调用传入类的get方法得到的值(方法的invoke要一一对应)所以getMethod.invoke(filter)
            Object filedValue= getMethod.invoke(filter);//调用类中的方法，得到值

            //4.3拼装sql
            if (filedValue == null||(filedValue instanceof Integer &&(Integer)filedValue==0)) {
                continue;
            }
            sb.append(" and ").append(columnName);
            if (filedValue instanceof String) {
                if (((String) filedValue).contains(",")) {//将字符串按“,”分割得到一个数组(注意强制转型)
                    String[] values = ((String) filedValue).split(",");
                    sb.append(" in (");
                    for (String v : values) {
                        sb.append("'").append(v).append("'").append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(")");
                } else {
                    sb.append("=").append("'").append(filedValue).append("'");
                }
            }else if (filedValue instanceof Integer) {
                sb.append("=").append(filedValue);
            }
        }
        return sb.toString();
    }
}
