package org.svetso.marketplace_monolyth.company.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.svetso.marketplace_monolyth.company.application.dto.command.CreateCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.DeleteCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.port.in.*;
import org.svetso.marketplace_monolyth.company.web.dto.mapper.CompanyMapper;
import org.svetso.marketplace_monolyth.company.web.dto.request.CreateCompanyRequest;
import org.svetso.marketplace_monolyth.company.web.dto.request.DeleteCompanyRequest;
import org.svetso.marketplace_monolyth.company.web.dto.request.UpdateCompanyRequest;
import org.svetso.marketplace_monolyth.company.web.dto.response.CompanyResponse;
import org.svetso.marketplace_monolyth.security.AuthContext;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
@Slf4j
public class CompanyController {

    private final CreateCompanyUseCase createCompanyUseCase;
    private final UpdateCompanyUseCase updateCompanyUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;
    private final ListCompaniesUseCase listCompaniesUseCase;
    private final GetCompanyByIdUseCase getCompanyByIdUseCase;
    private final CompanyMapper companyMapper;
    private final AuthContext authContext;

    @PostMapping("/create")
    public ResponseEntity<CompanyResponse> create(@Valid @RequestBody CreateCompanyRequest request) {

        log.info("Attempt to create company {}", request.name());
        CompanyResponse response = companyMapper.dtoToResponse(createCompanyUseCase.execute(
                new CreateCompanyCommand(
                        request.name(),
                        request.email(),
                        request.phone(),
                        authContext.getCurrentUser().userId()
                )
        ));

        log.info("Company {} created", request.name());

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestBody DeleteCompanyRequest request) {
        deleteCompanyUseCase.execute(
                new DeleteCompanyCommand(
                        authContext.getCurrentUser().userId(),
                        request.companyId()
                )
        );
        return ResponseEntity.ok().body("Company deleted");
    }

    @PutMapping("/update")
    public ResponseEntity<CompanyResponse> update(@Valid @RequestBody UpdateCompanyRequest command) {
        CompanyResponse response = companyMapper.dtoToResponse(updateCompanyUseCase.execute(
                companyMapper.updateRequestToCommand(
                        command,
                        authContext.getCurrentUser().userId()
                )
        ));

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompany(@Valid @PathVariable Long id) {
        log.info("Attempt to get company with id {}", id);
        return ResponseEntity.ok().body(companyMapper.dtoToResponse(getCompanyByIdUseCase.execute(id)));
    }

    @GetMapping("")
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        log.info("Attempt to get all companies");
        return ResponseEntity.ok().body(
                listCompaniesUseCase.execute()
                        .stream()
                        .map(companyMapper::dtoToResponse)
                        .toList()
        );
    }
}
