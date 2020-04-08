package com.chcpc.java8.practice.one;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );
    // 找出2012年发生的所有交易，并按交易额排序
    @Test
    public void test1(){
        List<Transaction> collect = transactions.stream()
                .filter(e -> e.getYear() == 2012)
                .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()))
                .collect(Collectors.toList());
        for (Transaction transaction : collect) {
            System.out.println(transaction);
        }
    }
    //交易员在哪些不同的城市工作过
    @Test
    public void test2(){
        List<String> cities = transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println(cities);
    }
    //查找所有来自剑桥的交易员，并按姓名排序
    @Test
    public void test3(){
        List<Trader> cambridge = transactions.stream()
                .filter(e -> e.getTrader().getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(o -> o.getTrader().getName()))
                .map(Transaction::getTrader)
                .distinct()
                .collect(Collectors.toList());
        for (Trader trader : cambridge) {
            System.out.println(trader);
        }
    }
    //返回所有交易员的姓名字符串，并按字母顺序排序
    @Test
    public void test4(){
        List<String> collect = transactions.stream()
                .map(o -> o.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
        }
    }
    //有没有交易员在米兰工作的？
    @Test
    public void test5(){
        boolean milan = transactions.stream()
                .anyMatch(o -> o.getTrader().getCity().equals("Milan"));
        System.out.println(milan);
    }
    //打印生活在剑桥的交易员的所有交易额
    @Test
    public void test6(){
        Optional<Integer> cambridge = transactions.stream()
                .filter(o -> o.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(cambridge.orElse(0));
    }
    //所有交易中，最高的交易额是多少
    @Test
    public void test7(){
        Optional<Integer> max = transactions.stream().map(Transaction::getValue).max(Integer::compareTo);
        System.out.println(max.orElse(0));
    }
    //找到交易额最小的交易
    @Test
    public void test8(){
        Transaction transaction = transactions.stream().min(Comparator.comparingInt(Transaction::getValue)).orElse(null);
        System.out.println(transaction);
    }
}
