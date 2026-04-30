package org.svetso.marketplace_monolyth.company.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.svetso.marketplace_monolyth.company.application.dto.command.AddMemberCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.RemoveCompanyMemberCommand;
import org.svetso.marketplace_monolyth.company.application.port.in.AddMemberToCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.in.RemoveMemberFromCompanyUseCase;
import org.svetso.marketplace_monolyth.company.web.dto.request.AddMemberRequest;
import org.svetso.marketplace_monolyth.company.web.dto.request.RemoveMemberRequest;
import org.svetso.marketplace_monolyth.security.AuthContext;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company/employee")
public class CompanyEmployeeController {

    private final AddMemberToCompanyUseCase addMemberToCompany;
    private final RemoveMemberFromCompanyUseCase removeMemberFromCompanyUseCase;
    private final AuthContext authContext;

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody @Valid AddMemberRequest addMemberRequest) {
        AddMemberCommand command = new AddMemberCommand(
                authContext.getCurrentUser().userId(),
                addMemberRequest.companyId(),
                addMemberRequest.userId(),
                addMemberRequest.role()
        );
        addMemberToCompany.execute(command);

        return ResponseEntity.ok().body("User added");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeUser(@RequestBody @Valid RemoveMemberRequest request) {
        RemoveCompanyMemberCommand command = new RemoveCompanyMemberCommand(
                authContext.getCurrentUser().userId(),
                request.userId(),
                request.companyId()
        );
        removeMemberFromCompanyUseCase.execute(command);
        return ResponseEntity.ok().body("User deleted");
    }
}
