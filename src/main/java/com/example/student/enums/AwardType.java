package com.example.student.enums;

import java.util.Random;

public enum AwardType{
    GOLD,SILVER,BRONZE;

    public static AwardType fromOrdinal(int index) {
        return AwardType.values()[index];
    }
    public static AwardType getRandomType() {
        return fromOrdinal(new Random().nextInt(AwardType.values().length));
    }
}