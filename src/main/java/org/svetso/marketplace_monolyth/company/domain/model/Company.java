package org.svetso.marketplace_monolyth.company.domain.model;

import org.svetso.marketplace_monolyth.exceptions.BadRequestException;
import org.svetso.marketplace_monolyth.exceptions.ForbiddenException;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private Long id;
    private String name;
    private String email;
    private String phone;

    public Company(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void updateDetails(String name, String email, String phone) {
        if (name != null) this.name = name;
        if (email != null) this.email = email;
        if (phone != null) this.phone = phone;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
