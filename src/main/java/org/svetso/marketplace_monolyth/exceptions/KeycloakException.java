package org.svetso.marketplace_monolyth.exceptions;

public class KeycloakException extends RuntimeException {
    public KeycloakException(String message) {
        super(message);
    }
}