package com.chcpc.java8.t04optional;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    /**
     * Optional.of(T t)：创建一个Optional实例，t必须非空
     * Optional.empty()：创建一个空的Optional实例
     * Optional.ofNullable(T t)：t可以为null
     */
    @Test
    public void test1(){
        Girl girl = new Girl();
//        girl = null;
        Optional<Girl> girl1 = Optional.of(girl);
        System.out.println(girl1);
        System.out.println("***************");
        Optional<Girl> girl2 = Optional.ofNullable(null);
        System.out.println(girl2);
        System.out.println("***************");
    }
    private String getGirlName1(Boy boy){
        return boy.getGirl().getName();
    }
    @Test
    public void test2(){
        Boy boy = new Boy();
        String girlName = getGirlName1(boy);
        System.out.println(girlName);
    }
    // 优化以后的getGirlName();
    private String getGirlName2(Boy boy){
        if(boy != null){
            Girl girl = boy.getGirl();
            if(girl !=  null){
                return girl.getName();
            }
        }
        return null;
    }
    @Test
    public void test3(){
        Boy boy = new Boy();
        String girlName = getGirlName2(boy);
        System.out.println(girlName);
    }
    // 使用Optional类以后的getGirlName();
    private String getGirlName3(Boy boy){
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        Boy newBoy = boyOptional.orElse(new Boy(new Girl("赵丽颖")));
        Girl girl = newBoy.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        Girl girl1 = girlOptional.orElse(new Girl("彭迪热巴"));
        return girl1.getName();
    }
    @Test
    public void test4(){
        Boy boy = new Boy();
        String girlName = getGirlName3(boy);
        System.out.println(girlName);
    }
}
