package org.svetso.marketplace_monolyth.user.mappers;

import org.springframework.stereotype.Component;
import org.svetso.marketplace_monolyth.user.dto.UserCreationDto;
import org.svetso.marketplace_monolyth.user.dto.UserDto;
import org.svetso.marketplace_monolyth.user.entity.User;

@Component
public class UserMappers {
    public UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setLastName(user.getLastName());
        userDto.setKeycloakId(user.getKeycloakId());
        userDto.setFirstName(user.getFirstName());
        return userDto;
    }

    public User userCreationDtoToUser(UserCreationDto userCreationDto) {
        User user = new User();
        user.setUsername(userCreationDto.getUsername());
        user.setKeycloakId(userCreationDto.getKeycloakId());
        user.setFirstName(userCreationDto.getFirstName());
        user.setEmail(userCreationDto.getEmail());
        user.setLastName(userCreationDto.getLastName());
        return user;
    }
}
