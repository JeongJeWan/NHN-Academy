package org.nanacademy.mart.test;

public class MySemaphore {
    private int counter;

    public MySemaphore(int counter) {
        this.counter = counter;
    }

    public synchronized void acquire() throws IllegalMonitorStateException, InterruptedException {
        if (--counter < 0) wait();
    }

    public synchronized void release() throws IllegalMonitorStateException {
        if (counter++ <= 0) notify();
    }
}
