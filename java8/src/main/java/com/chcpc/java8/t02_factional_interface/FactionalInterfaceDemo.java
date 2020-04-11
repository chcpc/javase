package com.chcpc.java8.t02_factional_interface;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 函数式接口
 * java内置的4大函数式接口
 * 1.消费型接口 Consumer<T>    void     accept(T t02_factional_interface)
 * 2.供给型接口 Supplier<T>     T        get()
 * 3.函数型接口 Function<T,R>   R       accept(T t02_factional_interface)
 * 4.断定型接口 Predicate<T>  boolean    test(T t02_factional_interface)
 */
public class FactionalInterfaceDemo {

    public void happyTime(double money, Consumer<Double> con){
        con.accept(money);
    }

    // 原方法
    @Test
    public void test1(){
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("原方法，输出价格："+aDouble);
            }
        });
    }

    // 函数式方法
    @Test
    public void test2(){
        happyTime(500, money -> System.out.println("函数式方法，输出价格："+money));
    }

    // 例2，根据给定的规则，过滤集合中的字符串。此规则由Predicate的方法决定
    public List<String> filterString(List<String> list, Predicate<String> pre){
        ArrayList<String> filterList = new ArrayList<>();
        for(String s : list){
            if(pre.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }

    // 例2，原方法
    @Test
    public void test3(){
        List<String> list = Arrays.asList("北京","南京","天津","东京","西京","普京");
        List<String> strings = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(strings);
    }

    // 例2，函数式方法
    @Test
    public void test4(){
        List<String> list = Arrays.asList("北京","南京","天津","东京","西京","普京");
        List<String> strings = filterString(list, s -> s.contains("京"));
        System.out.println(strings);
    }

}
