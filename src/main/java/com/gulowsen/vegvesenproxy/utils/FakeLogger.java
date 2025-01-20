package com.gulowsen.vegvesenproxy.utils;

public class FakeLogger {

    public static void logEntry(String e) {
        System.out.println(e);
    }

    public static void logError(Throwable e) {
        e.printStackTrace();
    }

}
