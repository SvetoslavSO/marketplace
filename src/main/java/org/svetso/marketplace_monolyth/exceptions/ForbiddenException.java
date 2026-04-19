package org.svetso.marketplace_monolyth.exceptions;

public class ForbiddenException extends ApiException {
    public ForbiddenException(String message) {
        super(message);
    }
}