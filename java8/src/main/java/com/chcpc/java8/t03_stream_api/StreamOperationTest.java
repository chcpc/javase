package com.chcpc.java8.t03_stream_api;

import com.chcpc.java8.t02_method_references.Employee;
import com.chcpc.java8.t02_method_references.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream的中间操作
 */
public class StreamOperationTest {
    // 筛选与切片
    @Test
    public void test1(){
        List<Employee> list = EmployeeData.getEmployees();
        // filter(Predicate.p) — 接收Lambda从流中排除某些元素。
        Stream<Employee> stream = list.stream();
        // 查询员工表中薪资大于7000的员工信息
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);
        System.out.println("***************");
        // 1imit(n) — 截断流，使其元素不超过给定数量。
        list.stream().limit(3).forEach(System.out::println);
        System.out.println("***************");
        // skip(n) — 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
        list.stream().skip(3).forEach(System.out::println);
        System.out.println("***************");
        // distinct() — 筛选，通过流所生成元素的hashCode（）和equals（）去除重复元素
        list.add(new Employee(1010, "刘强东", 33, 3000.82));
        list.add(new Employee(1010, "刘强东", 33, 3000.82));
        list.add(new Employee(1010, "刘强东", 33, 3000.82));
        list.stream().distinct().forEach(System.out::println);
    }

    // 映射
    @Test
    public void test2(){
        // map(Function f) 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        System.out.println("***************");
        // 练习1：获取员工姓名长度大于3的员工姓名
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> nameStream = employees.stream().map(Employee::getName);
        nameStream.filter(name -> name.length() > 3).forEach(System.out::println);
        System.out.println("***************");
        // 练习2：
        Stream<Stream<Character>> streamStream = list.stream().map(StreamOperationTest::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });
        System.out.println("***************");
        // flatMap(Function f) 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        Stream<Character> characterStream = list.stream().flatMap(StreamOperationTest::fromStringToStream);
        characterStream.forEach(System.out::println);
        // map ToDouble(ToDoubleFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream。

        // map Tolnt(TolntFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的IntStream。

        // map ToLong(ToLongFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的LongStream。


    }
    // 将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    // 排序
    @Test
    public void test4(){
        // sorted()-自然排序
        List<Integer> list = Arrays.asList(12,34,45,65,32,-54,3);
        list.stream().sorted().forEach(System.out::println);
        System.out.println("***************");
        // sorted(Comparator com)—定制排序
        // 如果自然排序会抛出异常，原因是没有实现Comparable接口
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1,e2) -> {
            int ageValue = Integer.compare(e1.getAge(),e2.getAge());
            return ageValue != 0 ? ageValue : Double.compare(e1.getSalary(),e2.getSalary());
                }).forEach(System.out::println);
    }
}
