package org.example;

public class GameController {
    volatile boolean isPaused = false;
    volatile int bunnySpeed = 150;
    volatile int robotsSpeed = 300;
    final Object pauseLock = new Object();

    public void checkPause() {
        synchronized (pauseLock) {
            while (isPaused) {
                try {
                    pauseLock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}