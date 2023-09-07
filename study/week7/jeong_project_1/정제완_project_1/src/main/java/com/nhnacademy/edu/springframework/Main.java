package com.nhnacademy.edu.springframework;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            DoorayMessageSender doorayMessageSender = context.getBean("doorayMessageSender", DoorayMessageSender.class);
            doorayMessageSender.sendMessage(new User("정제완"), "project_1");
        }
    }
}
