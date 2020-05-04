package com.chcpc.java8;

import org.junit.Test;

import static com.chcpc.java8.StorageEnumDefine.HealthStatusEnum;

public class Tests {

    @Test
    public void test1() {
        TestEnum e1 = TestEnum.MON;
        System.out.println(e1.getValue());
        System.out.println( e1.equals(TestEnum.TUR));
    }

    @Test
    public void test2() {
        HealthStatusEnum healthStatusEnum = HealthStatusEnum.getById(2);
        System.out.println(healthStatusEnum);
    }
    @Test
    public void test3() {
        Season.SPRING.show();
        Season.SUMMER.show();
        Season.FALL.show();
        Season.WINTER.show();
        System.out.println(Season.WINTER.getDeclaringClass());
        System.out.println(Season.WINTER.getClass());
    }
}
