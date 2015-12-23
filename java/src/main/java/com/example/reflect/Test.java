package com.example.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by colin on 15-12-17.
 * Java的类反射所需要的类并不多，它们分别是：Field、Constructor、Method、Class、Object，下面我将对这些类做一个简单的说明。
 * Field类：提供有关类或接口的属性的信息，以及对它的动态访问权限。反射的字段可能是一个类（静态）属性或实例属性，
 * 简单的理解可以把它看成一个封装反射类的属性的类。
 * Constructor类：提供关于类的单个构造方法的信息以及对它的访问权限。这个类和Field类不同，Field类封装了反射类的属性，
 * 而Constructor类则封装了反射类的构造方法。
 * Method类：提供关于类或接口上单独某个方法的信息。所反映的方法可能是类方法或实例方法（包括抽象方法）。
 * 这个类不难理解，它是用来封装反射类方法的一个类。
 * Class类：类的实例表示正在运行的 Java 应用程序中的类和接口。枚举是一种类，注释是一种接口。
 * 每个数组属于被映射为 Class 对象的一个类，所有具有相同元素类型和维数的数组都共享该 Class 对象。
 * Object类：每个类都使用 Object 作为超类。所有对象（包括数组）都实现这个类的方法。
 */
public class Test {
    public static void main(String[] args) {
        A a = new A();
        Class clazz = a.getClass();
        getPublicFiled(clazz);
        getAllFiled(clazz);
        getPrivateFiled(clazz, a);
        getConstructor(clazz);
        getMethods(clazz);
        getInterface(clazz);


    }

    private static void getInterface(Class clazz) {
        System.out.println("反射类的接口");
        Class [] interfaces=clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println(interfaces[i].getName());
        }
        System.out.println("反射类的父类");
        System.out.println(clazz.getSuperclass().getName());

    }

    private static void getMethods(Class clazz) {
        System.out.println("反射类中的方法");
        Method[] methods = clazz.getMethods();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < methods.length; i++) {
            //获取方法的返回类型
            sb.append(methods[i].getReturnType().getName());
            //输出方法名
            sb.append(" " + methods[i].getName() + "(");
            //方法的参数
            Class[] paramsTypes = methods[i].getParameterTypes();
            for (int j = 0; j < paramsTypes.length; j++) {
                sb.append(paramsTypes[j].getName());
                sb.append(",");
            }
            String c= String.valueOf(sb.charAt(sb.length() - 1));
            if (!"(".equals(c)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(")");
            System.out.println(sb);
            sb.delete(0, sb.length());//重置sb
        }
    }

    private static void getConstructor(Class clazz) {
        System.out.println("反射类中所有的构造器");
        //获取指定类的类名
        String className = clazz.getName();
        //获取制定类的构造方法
        Constructor[] constructors = clazz.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            StringBuilder sb = new StringBuilder();
            //获取指定构造方法的参数的集合
            Class[] paramTypes = constructors[i].getParameterTypes();
            sb.append(className);
            sb.append("(");
            for (int j = 0; j < paramTypes.length; j++) {
                sb.append(paramTypes[j].getName() + ",");
            }
            String c = String.valueOf(sb.charAt(sb.length() - 1));
            if (!"(".equals(c)) {
                sb.deleteCharAt(sb.length() - 1);
            }
//            sb.toString().substring(0, sb.length() - 1);//删除最后一个逗号，不知道为什么不行。。
            sb.append(")");
            System.out.println(sb);
        }
    }

    //这里用到了两个方法，getFields()、getDeclaredFields()，
    // 它们分别是用来获取反射类中所有公有属性和反射类中所有的属性的方法。
    // 另外还有getField(String)和getDeclaredField(String)方法都是用来过去反射类中指定的属性的方法，
    // 要注意的是getField方法只能取到反射类中公有的属性，而getDeclaredField方法都能取到。

    //这里还用到了Field 类的setAccessible方法，它是用来设置是否有权限访问反射类中的私有属性的，
    // 只有设置为true时才可以访问，默认为false。另外 Field类还有set(Object AttributeName,Object value)方法，可以改变指定属性的值。

    private static void getAllFiled(Class clazz) {
        System.out.println("反射类中所有的属性");
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Class c = fields[i].getType();
            System.out.println(c);
        }
    }

    private static void getPrivateFiled(Class clazz, A a) {
        try {
            System.out.println("反射类中私有属性的值");
            Field field = clazz.getDeclaredField("gender");//必须知道这个私有属性的名字
            field.setAccessible(true);
            Integer i = (Integer) field.get(a);
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void getPublicFiled(Class clazz) {
        System.out.println("反射类中所有的公有属性");
        Field[] fields = clazz.getFields();
        for (int i = 0; i < fields.length; i++) {
            Class c = fields[i].getType();
            System.out.println(c);
        }
    }
}
