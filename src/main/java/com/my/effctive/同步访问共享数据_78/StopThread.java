package com.my.effctive.同步访问共享数据_78;

import java.util.concurrent.TimeUnit;

public class StopThread {
    private static volatile Boolean stopRequested = false;
    public static void main(String[] args)
            throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                i++;
            }
         });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(2);
        stopRequested = true;
     }
}
