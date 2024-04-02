package com.example.Farm_management.Service;

import com.example.Farm_management.web.exception.BadRequestAlertException;
import com.example.Farm_management.Service.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.List;
import java.util.Optional;
@Validated
@Service
public interface UserService {

    UserDto saveUser(UserDto userDto) throws BadRequestAlertException;

    List<UserDto> getAllUsers(UserDto userDto);

    Optional<UserDto> getUserbyid(Long id);

    /*Optional<User> getUserByName(String name);*/

    void deleteUserById(Long id);

   UserDto updateUser(UserDto userDto);

}
