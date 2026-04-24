package org.svetso.marketplace_monolyth.company.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.auth.entity.Address;
import org.svetso.marketplace_monolyth.user.dto.UserDto;
import org.svetso.marketplace_monolyth.user.entity.User;
import org.svetso.marketplace_monolyth.user.repository.UserRepository;
import org.svetso.marketplace_monolyth.company.dto.AddressDto;
import org.svetso.marketplace_monolyth.company.dto.CompanyMemberDto;
import org.svetso.marketplace_monolyth.company.dto.CreateCompanyDto;
import org.svetso.marketplace_monolyth.company.dto.CompanyDto;
import org.svetso.marketplace_monolyth.company.entity.Company;
import org.svetso.marketplace_monolyth.company.entity.CompanyMember;
import org.svetso.marketplace_monolyth.company.entity.CompanyRole;
import org.svetso.marketplace_monolyth.company.mappers.CompanyMappers;
import org.svetso.marketplace_monolyth.company.repository.CompanyRepository;
import org.svetso.marketplace_monolyth.exceptions.BadRequestException;
import org.svetso.marketplace_monolyth.exceptions.DataAccessException;
import org.svetso.marketplace_monolyth.exceptions.ForbiddenException;
import org.svetso.marketplace_monolyth.exceptions.NotFoundException;
import org.svetso.marketplace_monolyth.security.AuthContext;
import org.svetso.marketplace_monolyth.security.CurrentUser;
import org.svetso.marketplace_monolyth.user.service.UserService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyAdministrationService {

    private final CompanyRepository companyRepository;
    private final AddressService addressService;
    private final UserService userService;
    private final CompanyEmployeeService companyEmployeeService;
    private final CompanyService companyService;
    private final UserRepository userRepository;
    private final AuthContext context;
    private final CompanyMappers mappers;

    public CompanyDto createCompany(CreateCompanyDto request) {
        log.info("Company creation attempt: company name: {}", request.getName());

        AddressDto addressDto = new AddressDto();
        addressDto.setPostalCode(request.getPostalCode());
        addressDto.setCountry(request.getCountry());
        addressDto.setCity(request.getCity());
        addressDto.setStreet(request.getStreet());
        Address address = addressService.getOrCreateAddress(addressDto);


        CurrentUser currentUser = context.getCurrentUser();
        UserDto user = userService.getUserByEmail(currentUser.email());
        if (user == null) {
            throw new ForbiddenException("Company create fail: user is not exist");
        }

        if (companyRepository.getCompanyByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Company email already exists");
        }

        if (companyRepository.getCompanyByPhone(request.getPhone()).isPresent()) {
            throw new BadRequestException("Company phone already exists");
        }

        try {
            companyService.createCompany(request, address);
            companyRepository.save(company);
            companyEmployeeService.addMemberToCompany(CompanyRole.OWNER, user, company);
        } catch (Exception e) {
            log.error("Error saving company", e);
            throw new DataAccessException("Db error");
        }
        log.info("Company {} created with id {}", company.getName(), company.getId());
        return mappers.companyToDto(company);
    }

    public CompanyDto updateCompany(CompanyDto companyDto) {
        Company company = companyRepository.getCompanyById(companyDto.getId())
                .orElseThrow(() -> new NotFoundException("Company update fail: company not found"));



        AddressDto addressDto = new AddressDto();
        addressDto.setPostalCode(companyDto.getPostalCode());
        addressDto.setCountry(companyDto.getCountry());
        addressDto.setCity(companyDto.getCity());
        addressDto.setStreet(companyDto.getStreet());
        Address address = addressService.getOrCreateAddress(addressDto);

        company.setAddress(address);
        company.setName(companyDto.getName());
        company.setPhone(companyDto.getPhone());
        company.setEmail(companyDto.getEmail());

        try {
            return mappers.companyToDto(companyRepository.save(company));
        } catch (Exception e) {
            throw new DataAccessException("Db error");
        }
    }

    public List<CompanyMember> getCompanyMembers(Long companyId) {
        return companyEmployeeService.getCompanyMembers(companyId);
    }

    public void addMemberToCompany(CompanyMemberDto companyMemberDto) {
        Company company = companyRepository.getCompanyById(companyMemberDto.getCompanyId())
                .orElseThrow(() -> new NotFoundException("Add member fail: Company not found"));

        CompanyRole role = companyMemberDto.getCompanyRole();

        User user = userRepository.findById(companyMemberDto.getUserId())
                .orElseThrow(() -> new NotFoundException("Add member fail: User not found"));

        companyEmployeeService.addMemberToCompany(role, user, company);
    }

    public void deleteMemberFromCompany(CompanyMemberDto companyMemberDto) {
        CompanyRole role = companyMemberDto.getCompanyRole();

        if (role == CompanyRole.OWNER) {
            throw new ForbiddenException("Delete member fail: you cannot delete owner");
        }

        Company company = companyRepository.getCompanyById(companyMemberDto.getCompanyId())
                .orElseThrow(() -> new NotFoundException("Add member fail: Company not found"));

        User user = userRepository.findById(companyMemberDto.getUserId())
                .orElseThrow(() -> new NotFoundException("Add member fail: User not found"));

        companyEmployeeService.deleteMemberFromCompany(
                companyMemberDto.getUserId(),
                companyMemberDto.getCompanyId());
    }
}
