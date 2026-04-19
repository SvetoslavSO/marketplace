package org.svetso.marketplace_monolyth.exceptions;

public abstract class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
