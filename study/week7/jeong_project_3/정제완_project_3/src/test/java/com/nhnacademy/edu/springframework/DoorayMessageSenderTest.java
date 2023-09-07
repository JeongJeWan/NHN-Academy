package com.nhnacademy.edu.springframework;

import com.nhn.dooray.client.DoorayHook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class DoorayMessageSenderTest {

    @InjectMocks
    private MessageSendService messageSendService;

    @Mock
    private DoorayHookSender doorayHookSender;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDoorayMessageSender() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.config");
        messageSendService = context.getBean("messageSendService", MessageSendService.class);
        when(doorayHookSender.send(any(DoorayHook.class))).thenReturn(false);
        boolean actual = messageSendService.doSendMessage();
        Assertions.assertTrue(actual);
    }
}