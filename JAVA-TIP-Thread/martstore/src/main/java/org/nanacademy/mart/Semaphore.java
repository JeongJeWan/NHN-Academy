package org.nanacademy.mart;

public class Semaphore {
    int signals = 0;
    int bound   = 0;

    public Semaphore(int upperBound) {
        this.bound = upperBound;
    }

    public synchronized void acquire() throws InterruptedException {
        while (this.signals == bound) {
            wait();
        }
        this.signals++;
        this.notify();
    }

    public synchronized void release() throws InterruptedException {
        while (this.signals == 0) {
            wait();
        }
        this.signals--;
        this.notify();
    }
}
