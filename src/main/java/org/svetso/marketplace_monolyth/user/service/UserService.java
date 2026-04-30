package org.svetso.marketplace_monolyth.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.svetso.marketplace_monolyth.exceptions.BadRequestException;
import org.svetso.marketplace_monolyth.exceptions.NotFoundException;
import org.svetso.marketplace_monolyth.user.dto.UserCreationDto;
import org.svetso.marketplace_monolyth.user.dto.UserDto;
import org.svetso.marketplace_monolyth.user.entity.User;
import org.svetso.marketplace_monolyth.user.mappers.UserMappers;
import org.svetso.marketplace_monolyth.user.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMappers mappers;

    public User  getUserByKeycloakId(String keycloakId) {
        return userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new NotFoundException("Get user by keycloak id fail: user not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Get user by email fail: user not found"));
    }

    public void isEmailExists(String email) throws BadRequestException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new BadRequestException("email already exists");
        }
    }


    public void isUsernameExists(String username) throws BadRequestException {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new BadRequestException("Username already exists");
        }
    }

    public UserDto createUser(UserCreationDto userCreationDto) {
        User user = userRepository.save(mappers.userCreationDtoToUser(userCreationDto));
        return  mappers.userToUserDto(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found by id"));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found by username"));
    }
}
