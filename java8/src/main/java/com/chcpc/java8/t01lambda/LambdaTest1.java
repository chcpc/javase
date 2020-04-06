package com.chcpc.java8.t01lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Lambda表达式的使用
 * 1. 举例：(o1, o2) -> Integer.compare(o1, o2);
 * 2. 格式：
 *      -> ：lambda操作符 或箭头操作符
 *      ->左侧：lambda形参列表（其实就是接口中的抽象方法的形参列表）
 *      ->右侧：lambda体（其实就是重写的抽象方法体）
 * 3.Lambda表达式的使用：（分六种情况）
 *  总结：
 *      ->左侧：lambda形参列表的参数类型可以省略（类型腿短）；如果lambda形参列表只有一个参数，其一对()也可以省略。
 *      ->右侧：lambda体应该使用一对{}包裹；如果Lambda体只有一条执行语句（可能是return语句），可以省略这一对{}和return
 * 4.Lambda表达式的本质：作为函数式接口的实例
 * 5.如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口
 * 我们可以在一个接口上使用@Functionallnterface注解，这样做可以检查它是否是一个函数式接口。同时javadoc也会包含一条声明，说明这个接口是一个函数式接口。
 *
 */
public class LambdaTest1 {
    // 1.语法格式一：无参，无返回值
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();
        System.out.println("******************");

        Runnable r2 = () -> System.out.println("我爱北京天安门");
        r2.run();
    }

    // 2.语法格式二：Lambda 需要参数，但是没有返回值
    @Test
    public void test2(){
        Consumer<String> con = (String s) -> {
            System.out.println(s);
        };
        con.accept("同意1");
    }

    // 3.语法格式三：数据类型可以省略，因为可由编译器推断得出，称为类型推断
    @Test
    public void test3(){
        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
        int compare2 = com2.compare(1, 2);
        System.out.println(compare2);
    }

    // 4.语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省略
    @Test
    public void test4(){
        Consumer<String> con = s -> {
            System.out.println(s);
        };
        con.accept("同意2");
    }

    // 5.语法格式五：Lambda 需要两个或以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void test5(){
        // Lambda表达式的写法
        Comparator<Integer> com5 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        int compare5 = com5.compare(21, 22);
        System.out.println(compare5);
    }
    // 语法格式六：当Lambda 体只有一条语句时，return与大括号若有，都可以省略
    @Test
    public void test6(){
        // Lambda表达式的写法
        Comparator<Integer> com6 = (o1, o2) ->  o1.compareTo(o2);
        int compare6 = com6.compare(21, 22);
        System.out.println(compare6);
    }
}
