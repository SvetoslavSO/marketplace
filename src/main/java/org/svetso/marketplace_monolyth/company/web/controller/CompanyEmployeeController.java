package org.svetso.marketplace_monolyth.company.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.svetso.marketplace_monolyth.company.application.port.in.AddMemberToCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.in.RemoveMemberFromCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.in.UpdateCompanyMemberUseCase;
import org.svetso.marketplace_monolyth.company.web.dto.mapper.CompanyEmployeeMapper;
import org.svetso.marketplace_monolyth.company.web.dto.request.AddMemberRequest;
import org.svetso.marketplace_monolyth.company.web.dto.request.RemoveMemberRequest;
import org.svetso.marketplace_monolyth.company.web.dto.request.UpdateMemberRequest;
import org.svetso.marketplace_monolyth.company.web.dto.response.CompanyMemberResponse;
import org.svetso.marketplace_monolyth.security.AuthContext;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company/employee")
public class CompanyEmployeeController {

    private final AddMemberToCompanyUseCase addMemberToCompany;
    private final RemoveMemberFromCompanyUseCase removeMemberFromCompanyUseCase;
    private final UpdateCompanyMemberUseCase updateCompanyMemberUseCase;
    private final CompanyEmployeeMapper companyEmployeeMapper;
    private final AuthContext authContext;

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody @Valid AddMemberRequest addMemberRequest) {

        addMemberToCompany.execute(
                companyEmployeeMapper.addMemberRequestToCommand(
                        addMemberRequest,
                        authContext.getCurrentUser().userId()
                )
        );

        return ResponseEntity.ok().body("User added");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeUser(@RequestBody @Valid RemoveMemberRequest request) {
        removeMemberFromCompanyUseCase.execute(
                companyEmployeeMapper.removeRequestToCommand(
                        request,
                        authContext.getCurrentUser().userId()
                )
        );
        return ResponseEntity.ok().body("User deleted");
    }

    @PutMapping("/update")
    public ResponseEntity<CompanyMemberResponse> updateUser(@RequestBody @Valid UpdateMemberRequest request) {
        CompanyMemberResponse response = companyEmployeeMapper.companyMemberDtoToResponse(
                updateCompanyMemberUseCase.execute(
                    companyEmployeeMapper.updateRequestToCommand(
                            request,
                            authContext.getCurrentUser().userId()
                    )
                )
        );
        return ResponseEntity.ok().body(response);
    }
}
