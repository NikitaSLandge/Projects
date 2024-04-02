package com.example.Farm_management.Service.mapper;

import com.example.Farm_management.domain.User;
import com.example.Farm_management.Service.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface Usermapper extends Entitymapper<UserDto, User> {


        UserDto toDto(User user);

        List<UserDto> toDto(List<User> user);

        User toEntity(UserDto userDto);

//    UsersDTO toDto(Users s);
//   Users toEntity(UsersDTO dto);
    }




