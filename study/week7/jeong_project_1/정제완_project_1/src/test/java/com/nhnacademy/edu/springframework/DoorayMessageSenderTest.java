package com.nhnacademy.edu.springframework;

import com.nhn.dooray.client.DoorayHook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class DoorayMessageSenderTest {

    @InjectMocks
    private DoorayMessageSender doorayMessageSender;

    @Mock
    private DoorayHookSender doorayHookSender;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDoorayMessageSender() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        doorayMessageSender = context.getBean("doorayMessageSender", DoorayMessageSender.class);
        when(doorayHookSender.send(any(DoorayHook.class))).thenReturn(false);
        boolean actual = doorayMessageSender.sendMessage(new User("정제완"), "project_1");
        Assertions.assertTrue(actual);
    }
}