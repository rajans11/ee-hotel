package com.ee.cucumber.utils.enums;

public enum Booking {
    FIRST_NAME("FIRSTNAME"),
    LAST_NAME("LASTNAME"),
    TOTALPRICE("TOTALPRICE"),
    DEPOSIT("DEPOSIT"),
    CHECKIN("CHECKIN"),
    CHECKOUT("CHECKOUT"),
    BOOKINGS("BOOKINGS");

    private String value;

    Booking(String key) {
        this.value = key;
    }

    public String getValue() {
        return value;
    }
}