package org.svetso.marketplace_monolyth.exceptions;

public class CompanyMemberNotFoundException extends ApiException {
    public CompanyMemberNotFoundException (String message) {
        super(message);
    }
}
