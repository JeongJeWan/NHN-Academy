package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.config.MessageSenderConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MessageSenderConfig.class)) {
            MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
            messageSendService.doSendMessage();
        }
    }
}
