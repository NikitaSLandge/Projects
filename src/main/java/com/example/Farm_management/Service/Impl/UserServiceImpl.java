package com.example.Farm_management.Service.Impl;

import com.example.Farm_management.domain.Authority;
import com.example.Farm_management.domain.User;
import com.example.Farm_management.repository.UserRepository;
import com.example.Farm_management.Service.UserService;
import com.example.Farm_management.Service.mapper.Usermapper;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import com.example.Farm_management.Service.dto.UserDto;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@Validated
@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final Usermapper usermapper;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, Usermapper usermapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.usermapper = usermapper;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {

        User user = usermapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (userDto.getAuthorities() != null) {
            Set<Authority> authorities = userDto.getAuthorities();
            user.setAuthorities(authorities);
        }
        User savedUser = userRepository.save(user);
        return usermapper.toDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers(UserDto userDto) {
        User user = usermapper.toEntity(userDto);
        List<User> user1 = userRepository.findAll();
        return usermapper.toDto(user1);
    }

    @Override
    public Optional<UserDto> getUserbyid(Long id) {
        return userRepository.findById(id).map(usermapper::toDto);
    }


    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = usermapper.toEntity(userDto);
        Optional<User> existingUser = userRepository.findById(userDto.getId());
        user = userRepository.save(user);
        return usermapper.toDto(user);
    }
}


