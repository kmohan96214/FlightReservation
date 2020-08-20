package com.company.models;

/*
    Non - Persistant class for now, can be used in Booking class
 */

public class Passenger {
    private String name;
    private String phoneNumber;
    private Integer age;

    public Passenger(String name, String phoneNumber, Integer age) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
