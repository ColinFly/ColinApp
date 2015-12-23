package com.example.java_8;

/**
 * Created by colin on 15-12-17.
 */
public class TestPerson  {
    public static void main(String[] args) {
        //Java 8 允许你使用 :: 关键字来传递方法或者构造函数引用
        //我们只需要使用 Person::new 来获取Person类构造函数的引用，
        // Java编译器会自动根据PersonFactory.create方法的签名来选择合适的构造函数。
        PersonFactory<Person> personFactory=Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person.toString());

        //这是传统的方式-->需要一个工厂的实现类，因此如果有n个不同的person就需要多写n个不同的实现类
        PersonFactory<Person> personFactory1 = new PersonFactoryImp();
        Person person1 = personFactory1.create("hehe", "colin");
        System.out.println(person1);
    }
}
