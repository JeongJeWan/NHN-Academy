package com.nhnacademy.airport;

public class Passenger{

    private int passengerNo;
    private String passengerName;
    private int grade;
    private int age;

    public Passenger(int no, String name, int grade, int age) {
        this.passengerNo = no;
        this.passengerName = name;
        this.grade = grade;
        this.age = age;
    }

    public int getPassengerNo () {
        return this.passengerNo;
    }

    public String getPassengerName () {
        return this.passengerName;
    }

    public int getGrade () {
        return this.grade;
    }

    public int getAge () {
        return this.age;
    }
}
