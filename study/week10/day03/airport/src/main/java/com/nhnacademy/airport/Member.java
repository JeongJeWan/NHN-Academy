package com.nhnacademy.airport;

public class Member {
    private String id;
    private String name;
    private String city;

    public Member (String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
