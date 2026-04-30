package org.svetso.marketplace_monolyth.company.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.svetso.marketplace_monolyth.company.application.dto.command.CreateCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.DeleteCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.UpdateCompanyCommand;
import org.svetso.marketplace_monolyth.company.application.dto.response.CompanyDto;
import org.svetso.marketplace_monolyth.company.application.port.in.*;
import org.svetso.marketplace_monolyth.company.web.dto.request.CreateCompanyRequest;
import org.svetso.marketplace_monolyth.company.web.dto.request.DeleteCompanyRequest;
import org.svetso.marketplace_monolyth.security.AuthContext;

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
    private final AuthContext authContext;

    @PostMapping("/create")
    public ResponseEntity<CompanyDto> create(@Valid @RequestBody CreateCompanyRequest request) {

        log.info("Attempt to create company {}", request.name());
        CompanyDto dto = createCompanyUseCase.execute(
                new CreateCompanyCommand(
                        request.name(),
                        request.email(),
                        request.phone(),
                        authContext.getCurrentUser().userId()
                )
        );

        log.info("Company {} created", request.name());

        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<CompanyDto> update(@Valid @RequestBody UpdateCompanyCommand command) {
        CompanyDto dto = updateCompanyUseCase.execute(
                new UpdateCompanyCommand(
                        command.companyId(),
                        command.name(),
                        command.email(),
                        command.phone(),
                        authContext.getCurrentUser().userId()
                )
        );

        return ResponseEntity.ok().body(dto);
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@Valid @PathVariable Long id) {
        log.info("Attempt to get company with id {}", id);
        return ResponseEntity.ok().body(getCompanyByIdUseCase.execute(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCompanies() {
        log.info("Attempt to get all companies");
        return ResponseEntity.ok().body(listCompaniesUseCase.execute());
    }
}
