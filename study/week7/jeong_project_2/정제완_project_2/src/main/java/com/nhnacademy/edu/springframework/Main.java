package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.config.MessageSenderConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MessageSenderConfig.class)) {
            DoorayMessageSender doorayMessageSender = context.getBean("doorayMessageSender", DoorayMessageSender.class);
            doorayMessageSender.sendMessage(new User("정제완"), "project_2");
        }
    }
}
