package com.chcpc.java8.t01lambda;

/**
 * 二、函数式接口
 * 1.只包含一个抽象方法的接口，称为函数式接口。
 * 2.你可以通过Lambda表达式来创建该接口的对象。（若Lambda表达式抛出一个受检异常（即：非运行时异常），那么该异常需要在目标接口的抽象方法上进行声明）。
 * 3.我们可以在一个接口上使用@Functionallnterface注解，这样做可以检查它是否是一个函数式接口。同时javadoc也会包含一条声明，说明这个接口是一个函数式接口。
 * 4.在java.util.function包下定义了Java8的丰富的函数式接口
 *
 * java内置四大核心函数式接口
 *         函数式接口               参数类型    返回类型                                    用途
 *  1.消费型接口 Consumer<T>           T          void               对类型为T的对象应用操作，包含方法：void accept(T t)
 *  2.供给型接口 Supplier<T>           无          T                          返回类型为T的对象，包含方法：T get()
 *  3.函数型接口 Function<T,R>         T           R       对类型为T的对象应用操作，并返回结果。结果是R类型的对象。包含方法：R apply(T t)
 *  4.断定型接口 Predicate<T>          T        boolean    确定类型为T的对象是否满足某约束，并返回boolean值。包含方法：boolean test(T t)
 */
@FunctionalInterface
public interface MyInterface {
    void method();
}
