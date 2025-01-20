package com.gulowsen.vegvesenproxy.errorhandling.exception;

public class ExternalAPIException extends Throwable {
    public ExternalAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExternalAPIException(String message) {
        super(message);
    }
}
