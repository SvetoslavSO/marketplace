package org.svetso.marketplace_monolyth.exceptions;

public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(message);
    }
}