package org.svetso.marketplace_monolyth.company.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/companies")
@Slf4j
public class CompanyController {
    private final CreateCompanyUseCase createCompanyUseCase;
    private final UpdateCompanyUseCase updateCompanyUseCase;
    private final DeleteCompanyUseCase deleteCompanyUseCase;
    private final ListCompaniesUseCase listCompaniesUseCase;
    private final GetCompanyByIdUseCase getCompanyByIdUseCase;
    private final CompanyMapper companyMapper;
    private final AuthContext authContext;

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@Valid @RequestBody CreateCompanyRequest request) {
        CompanyResponse response = companyMapper.dtoToResponse(
                createCompanyUseCase.execute(
                        new CreateCompanyCommand(
                                request.name(),
                                request.email(),
                                request.phone(),
                                authContext.getCurrentUser().userId()
                        )
                )
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyResponse> update(
            @PathVariable Long companyId,
            @Valid @RequestBody UpdateCompanyRequest request
    ) {
        CompanyResponse response = companyMapper.dtoToResponse(
                updateCompanyUseCase.execute(
                        companyMapper.updateRequestToCommand(request, companyId, authContext.getCurrentUser().userId())
                )
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long companyId) {
        deleteCompanyUseCase.execute(
                new DeleteCompanyCommand(authContext.getCurrentUser().userId(), companyId)
        );
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable Long companyId) {
        return ResponseEntity.ok(
                companyMapper.dtoToResponse(getCompanyByIdUseCase.execute(companyId))
        );
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> list() {
        return ResponseEntity.ok(
                listCompaniesUseCase.execute()
                        .stream()
                        .map(companyMapper::dtoToResponse)
                        .toList()
        );
    }
}