package com.chcpc.java8;

public interface StorageEnumDefine {

    enum HealthStatusEnum {
        UNKNOWN(0),
        NORMAL(1),
        FAULT(2);

        private final int id;

        HealthStatusEnum(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public static HealthStatusEnum getById(int id) {
            for (HealthStatusEnum healthStatusEnum : HealthStatusEnum.values()) {
                if (healthStatusEnum.id == id) {
                    return healthStatusEnum;
                }
            }
            return UNKNOWN;
        }
    }

    enum RunningStatusEnum {
        UNKNOWN(0),
        ONLINE(1),
        OFFLINE(2);

        private final int id;

        RunningStatusEnum(int id) {
            this.id = id;
        }
    }

    enum ObjectTypeEnum {
        UNKNOWN(0),
        STORAGE_DOMAIN(1),
        STORAGE_POOL(2),
        LUN(3),
        FILE_SYSTEM(4);

        private final int id;

        ObjectTypeEnum(int id) {
            this.id = id;
        }
    }

    enum EnumTest {
        MON(1) {
            @Override
            public EnumTest getNext() {
                return EnumTest.TUR;
            }
        },
        TUR(2) {
            @Override
            public EnumTest getNext() {
                return EnumTest.MON;
            }
        };

        EnumTest(int value) {
            this.value = value;
        }

        private final int value;

        public int getValue() {
            return this.value;
        }

        public abstract EnumTest getNext();

        public static EnumTest valueOf(int value) {
            for (EnumTest enumTest : EnumTest.values()) {
                if (enumTest.value == value) {
                    return enumTest;
                }
            }
            throw new IllegalArgumentException(
                    "No enum constant " + "TestEnum" + "." + value);
        }
    }
    double apply(double x, double y);
    enum Enums implements StorageEnumDefine {
        MON{
            public double apply(double x, double y) {
                return 0;
            }
        },
        TUR{
            public double apply(double x, double y) {
            return 0;
            }
        };
    }
}
