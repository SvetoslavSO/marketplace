package org.svetso.marketplace_monolyth.company.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.svetso.marketplace_monolyth.company.dto.CreateCompanyRequest;
import org.svetso.marketplace_monolyth.company.dto.CreateCompanyResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    @PostMapping("/create")
    public ResponseEntity<CreateCompanyResponse> createCompany(@Valid CreateCompanyRequest request) {
        
    }
}
