package com.nhnacademy.edu.springframework;

public interface MessageSender {

    boolean sendMessage(User user, String message);
}
