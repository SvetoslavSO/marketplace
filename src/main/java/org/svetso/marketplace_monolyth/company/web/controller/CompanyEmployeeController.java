package org.svetso.marketplace_monolyth.company.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.svetso.marketplace_monolyth.company.application.dto.command.AddMemberCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.RemoveCompanyMemberCommand;
import org.svetso.marketplace_monolyth.company.application.dto.command.UpdateCompanyMemberCommand;
import org.svetso.marketplace_monolyth.company.application.port.in.AddMemberToCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.in.RemoveMemberFromCompanyUseCase;
import org.svetso.marketplace_monolyth.company.application.port.in.UpdateCompanyMemberUseCase;
import org.svetso.marketplace_monolyth.company.web.dto.mapper.CompanyEmployeeMapper;
import org.svetso.marketplace_monolyth.company.web.dto.request.AddMemberRequest;
import org.svetso.marketplace_monolyth.company.web.dto.request.UpdateMemberRequest;
import org.svetso.marketplace_monolyth.company.web.dto.response.CompanyMemberResponse;
import org.svetso.marketplace_monolyth.security.AuthContext;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies/{companyId}/members")
public class CompanyEmployeeController {
    private final AddMemberToCompanyUseCase addMemberToCompanyUseCase;
    private final UpdateCompanyMemberUseCase updateCompanyMemberUseCase;
    private final RemoveMemberFromCompanyUseCase removeMemberFromCompanyUseCase;
    private final AuthContext authContext;
    private final CompanyEmployeeMapper companyEmployeeMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addMember(
            @PathVariable Long companyId,
            @Valid @RequestBody AddMemberRequest request
    ) {
        Long requesterId = authContext.getCurrentUser().userId();
        addMemberToCompanyUseCase.execute(
                new AddMemberCommand(
                        requesterId,
                        companyId,
                        request.userId(),
                        request.role()
                )
        );
    }
    @PutMapping("/{memberUserId}")
    public CompanyMemberResponse updateMember(
            @PathVariable Long companyId,
            @PathVariable Long memberUserId,
            @Valid @RequestBody UpdateMemberRequest request
    ) {
        Long requesterId = authContext.getCurrentUser().userId();
        return companyEmployeeMapper.companyMemberDtoToResponse(
                updateCompanyMemberUseCase.execute(
                        new UpdateCompanyMemberCommand(
                                requesterId,
                                memberUserId,
                                companyId,
                                request.role()
                        )
                )
        );
    }
    @DeleteMapping("/{memberUserId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMember(
            @PathVariable Long companyId,
            @PathVariable Long memberUserId
    ) {
        Long requesterId = authContext.getCurrentUser().userId();
        removeMemberFromCompanyUseCase.execute(
                new RemoveCompanyMemberCommand(
                        requesterId,
                        memberUserId,
                        companyId
                )
        );
    }
}