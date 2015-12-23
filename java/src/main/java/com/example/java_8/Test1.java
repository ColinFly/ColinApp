package com.example.java_8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by colin on 15-12-17.
 */
public class Test1 {
    public static void main(String[] args) {
        //参数类型是编译器猜出来的
        Arrays.asList("a", "b", "d").forEach(System.out::println);
        //也可以直接给出参数类型
        Arrays.asList( "a", "b", "d" ).forEach( ( String e ) -> System.out.println(e) );

        //Lambda可以引用类的成员变量和局部变量
        String separator = ",";
        Arrays.asList( "a", "b", "d" ).forEach((String e) -> System.out.println(e + separator));

        System.out.println(new DefaultableImpl().notRequired());
        System.out.println(new OverridableImpl().notRequired() );
        System.out.println(new OverridableImpl().test() );

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        System.out.println(names);

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println(names);

        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        System.out.println(names);

        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        System.out.println(names);

        Collections.sort(names, (String a, String b) -> a.compareTo(b));
        System.out.println(names);

       Collections.sort(names,(a,b)->b.compareTo(a));

        Converter<String, Integer> converter = (a) -> Integer.valueOf(a);
        Converter<String, Integer> converter1 = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123
    }
//    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }


    //默认方法与抽象方法不同之处在于抽象方法必须要求实现，但是默认方法则没有这个要求。
    // 相反，每个接口都必须提供一个所谓的默认实现，这样所有的接口实现者将会默认继承它
    // （如果有必要的话，可以覆盖这个默认实现）
    @FunctionalInterface
    public interface FunctionalDefaultMethods {
        void method();

        default void defaultMethod() {
        }
    }


    //Defaulable接口用关键字default声明了一个默认方法notRequired()，
    // Defaulable接口的实现者之一DefaultableImpl实现了这个接口，并且让默认方法保持原样。
    // Defaulable接口的另一个实现者OverridableImpl用自己的方法覆盖了默认方法。
    private interface Defaulable {
        // Interfaces now allow default methods, the implementer may or
        // may not implement (override) them.
        default String notRequired() {
            return "Default implementation";
        }

//        void method();
    }

    //默认方法
    private static class DefaultableImpl implements Defaulable {

    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
        //本来就可以添加
        public String test() {
            return "test";
        }
    }


}
