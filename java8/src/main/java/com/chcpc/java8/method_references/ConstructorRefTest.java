package com.chcpc.java8.method_references;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 *  和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致
 *  抽象方法的返回值类型即为构造器所属的类
 * 二、数组引用
 *  可以把数组看做是一个特殊的类，则写法与构造器一致
 *
 */
public class ConstructorRefTest {
    // 构造器引用
    // Supplier中的 T get()
    // Employee的空参构造器：Employee
    @Test
    public void test1(){
        Supplier<Employee> sup = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        Supplier<Employee> sup1 = () -> new Employee();

        System.out.println("*****************");

        Supplier<Employee> sup2 = Employee::new;
        Employee employee = sup2.get();
    }

    // Function中的 R apply(T t)
    @Test
    public void test2(){
        Function<Integer, Employee> func1 = id -> new Employee(id);
        Employee employee = func1.apply(1001);
        System.out.println(employee);

        System.out.println("*****************");

        Function<Integer, Employee> func2 = Employee::new;
        Employee employee1 = func2.apply(1002);
        System.out.println(employee1);
    }

    // BiFunction 中的 R apply(T t, U u)
    @Test
    public void test3(){
        BiFunction<Integer, String, Employee> func1 = (id,name) -> new Employee(id,name);
        System.out.println(func1.apply(1001, "Tom"));

        System.out.println("*****************");

        BiFunction<Integer, String, Employee> func2 = Employee::new;
        System.out.println(func2.apply(1002, "Joy"));
    }


    // 数组引用
    // Function 中的 R apply(T t)
    @Test
    public void test4(){
        Function<Integer, String[]> func1 = length -> new String[length];
        String[] arr1 = func1.apply(5);
        System.out.println(Arrays.asList(arr1));

        System.out.println("*****************");

        Function<Integer, String[]> func2 = String[]::new;
        String[] arr2 = func1.apply(5);
        System.out.println(Arrays.asList(arr2));
    }
}
