package com.chcpc.java8;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public enum TestEnum implements Serializable {
    MON(1) {
        @Override
        public TestEnum getNext() {
            return TestEnum.TUR;
        }
    },
    TUR(2) {
        @Override
        public TestEnum getNext() {
            return TestEnum.MON;
        }
    };

    TestEnum(int value) {
        this.value = value;
        System.out.println("yunx");
    }

    private final int value;

    public int getValue() {
        return this.value;
    }

    public abstract TestEnum getNext();

    public static TestEnum valueOf(int value) {
        for (TestEnum testEnum : TestEnum.values()) {
            if (testEnum.value == value) {
                return testEnum;
            }
        }
        throw new IllegalArgumentException(
                "No enum constant " + "TestEnum" + "." + value);
    }
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        throw new InvalidObjectException("can't deserialize enum");
    }

}
