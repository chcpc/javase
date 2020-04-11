package com.chcpc.java8.t04_stream_api;

import com.chcpc.java8.t03_method_references.Employee;
import com.chcpc.java8.t03_method_references.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 测试Stream的终止操作
 *
 */
public class StreamTerminationTest {
    // 1-匹配与查找
    @Test
    public void test1(){
        // allMatch(Predicate p) 检查是否匹配所有元素
        List<Employee> list = EmployeeData.getEmployees();
        boolean allMatch = list.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);
        System.out.println("***************");
        // anyMatch(Predicate p) 检查是否至少匹配一个元素
        boolean anyMatch = list.stream().anyMatch(e -> e.getSalary() > 8000);
        System.out.println(anyMatch);
        System.out.println("***************");
        // noneMatch(Predicate p) 检查是否没有匹配所有元素
        boolean noneMatch = list.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);
        System.out.println("***************");
        // findFirst() 返回第一个元素
        Optional<Employee> findFirst = list.stream().findFirst();
        System.out.println(findFirst);
        System.out.println("***************");
        // findAny() 返回当前流中的任意元素
        Optional<Employee> findAny = list.parallelStream().findAny();
        System.out.println(findAny);
        System.out.println("***************");
        // count() 返回流中元素总数
        long count = list.parallelStream().count();
        System.out.println(count);
        System.out.println("***************");
        // max(Comparator c) 返回流中最大值
        Optional<Double> max = list.stream().map(e -> e.getSalary()).max(Double::compare);
        System.out.println(max);
        System.out.println("***************");
        // min(Comparator c) 返回流中最小值
        Optional<Employee> min = list.stream().max((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary()));
        System.out.println(min);
        System.out.println("***************");
        // forEach(Consumer c)
        list.stream().forEach(System.out::println);
    }
    // 2-规约
    @Test
    public void test2(){
        // reduce(T iden, BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回T
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        System.out.println("***************");
        // reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回Optional<T>
        List<Employee> employees = EmployeeData.getEmployees();
        Optional<Double> total = employees.stream().map(Employee::getSalary).reduce((d1,d2) -> d1 + d2);
        System.out.println(total);
    }
    // 3-收集
    @Test
    public void test3(){
        // collect(Collector c) 将流转换为其他形式。接受一个Collector接口的实现，用于给Stream中元素做汇总的方法。
        // toList，返回一个List
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> list = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("***************");
        // toSet，返回一个Set
        Set<Employee> set = employees.stream().filter(e -> e.getSalary() > 9000).collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("***************");
        // toCollection，返回一个Collection
    }
}
