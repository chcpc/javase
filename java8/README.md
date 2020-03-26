# Java 8 新特性

Java8（又称为jdk 1.8）是Java语言开发的一个主要版本。
Java 8是oracle公司于2014年3月发布，可以看成是自Java5以来最具革命性的版本。Java8为Java语言、编译器、类库、开发工具与JVM带来了大量新特性。

- 速度更快
- 代码更少（增加了新的语法：Lambda表达式）
- 强大的Stream APl便于并行
- 最大化减少空指针异常：Optional 
- Nashorn引擎，允许在JVM上运行JS应用

## 2、并行流与串行流

并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。相比较串行的流，**并行的流可以很大程度上提高程序的执行效率**。
Java8中将并行进行了优化，我们可以很容易的对数据进行并行操作。
Stream API 可以声明性地通过parallel()与sequential()在并行流与顺序流之间进行切换。

## 一、Lambda表达式

Lambda是一个**匿名函数**，我们可以把Lambda表达式理解为是一段可以传递的代码（将代码像数据一样进行传递）。使用它可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了提升。

1. 举例：(o1, o2) -> Integer.compare(o1, o2);
2. 格式：

- -> ：lambda操作符 或箭头操作符
- ->左侧：lambda形参列表（其实就是接口中的抽象方法的形参列表）
- ->右侧：lambda体（其实就是重写的抽象方法体）

3. Lambda表达式的使用：（分六种情况）

- 总结：
- ->左侧：lambda形参列表的参数类型可以省略（类型腿短）；如果lambda形参列表只有一个参数，其一对()也可以省略。
- ->右侧：lambda体应该使用一对{}包裹；如果Lambda体只有一条执行语句（可能是return语句），可以省略这一对{}和return

4. Lambda表达式的本质：作为函数式接口的实例



## 二、函数式(Function)接口

- 只包含一个抽象方法的接口，称为函数式接口。
- 你可以通过Lambda表达式来创建该接口的对象。（若Lambda表达式抛出一个受检异常（即：非运行时异常），那么该异常需要在目标接口的抽象方法上进行声明）
- 我们可以在一个接口上使用@Functionallnterface注解，这样做可以检查它是否是一个函数式接口。同时javadoc也会包含一条声明，说明这个接口是一个函数式接口。
- 在java.util.function包下定义了Java8的丰富的函数式接口

> Java从诞生日起就是一直倡导“一切皆对象”，在Java里面面向对象(OOP)编程是一切。但是随着python、scala等语言的兴起和新技术的挑战，Java不得不做出调整以便支持更加广泛的技术要求，也即java不但可以支持OOP还可以支持OOF（面向函数编程）在函数式编程语言当中，函数被当做一等公民对待。
>
> 在将函数作为一等公民的编程语言中，Lambda表达式的类型是函数。但是在Java8中，有所不同。在Java8中，Lambda表达式是对象，而不是函数，它们必须依附于一类特别的对象类型——函数式接口。
>
> 简单的说，在Java8中，Lambda表达式就是一个函数式接口的实例。这就是Lambda表达式和函数式接口的关系。也就是说，只要一个对象是函数式接口的实例，那么该对象就可以用Lambda表达式来表示。
>
> 所以以前用匿名实现类表示的现在都可以用Lambda表达式来写。

### java内置函数式接口

|        函数式接口        | 参数类型 | 返回类型 | 用途                                                         |
| :----------------------: | -------- | :------: | ------------------------------------------------------------ |
|  消费型接口 Consumer<T>  | T        |   void   | 对类型为T的对象应用操作，包含方法：void accept(T t)          |
|  供给型接口 Supplier<T>  | 无       |    T     | 返回类型为T的对象，包含方法：T get()                         |
| 函数型接口 Function<T,R> | T        |    R     | 对类型为T的对象应用操作，并返回结果。结果是R类型的对象。包含方法：R apply(T t) |
| 断定型接口 Predicate<T>  | T        | boolean  | 确定类型为T的对象是否满足某约束，并返回boolean值。包含方法：boolean test(T t) |

### 其他接口

|                        函数式接口                        |                   |                   |                             用途                             |
| :------------------------------------------------------: | :---------------: | :---------------: | :----------------------------------------------------------: |
|                   BiFunction<T，U，R>                    |        T,U        |         R         | 对类型为T,U参数应用操作，返回R类型的结果。包含方法为：R apply(T t,U u) |
|            UnaryOperator<T>（Function子接口）            |         T         |         T         | 对类型为T的对象进行一元运算，并返回T类型的结果。包含方法为：T apply(T1 t1) |
|          BinaryOperator<T>（BiFunction子接口）           |        T,T        |         T         | 对类型为T的对象进行二元运算，并返回T类型的结果。包含方法为：T apply(T1 t1,T2 t2) |
|                   BiConsumer<T，U>T，U                   |                   |                   |  对类型为T,U参数应用操作。包含方法为：void accept(T t,U u)   |
|                  BiPredicate<T，U>T，U                   |                   |      boolean      |              包含方法为：boolean test(T t,U u)               |
| ToIntFunction<T>、ToLongFunction<T>、ToDoubleFunction<T> |         T         | int、long、double |              分别计算int、long、double值的函数               |
|    IntFunction<R>、LongFunction<R>、DoubleFunction<R>    | int、long、double |         R         |          参数类型分别为int、long、double类型的函数           |



## 三、方法引用与构造器引用

### 1、方法引用(Method References)

- 当要传递给Lambda体的操作已经有实现的方法了，可以使用方法引用
- 方法引用可以看做是Lambda表达式深层次的表达。换句话说，方法引用就是Lambda表达式，也是函数式接口的一个实例，通过方法的名字来指向一个方法，可以认为是Lambda表达式的一个语法糖
- 要求：实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致
- 格式：使用操作符"::"将类（或对象）与方法名隔开来
- 如下三种主要使用情况
  - 对象::实例方法名
  - 类::静态方法名
  - 类::实例方法名
- 情况一和情况二的方法引用使用的要求：要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法形参列表和返回值类型相同

### 2、构造器引用

- 构造器引用
  - 和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致
  - 抽象方法的返回值类型即为构造器所属的类
- 数组引用
  - 可以把数组看做是一个特殊的类，则写法与构造器一致

## 四、强大的StreamAPI

## 五、Optional类