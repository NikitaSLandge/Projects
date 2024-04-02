package com.example.Farm_management.web.rest;

import com.example.Farm_management.repository.UserRepository;
import com.example.Farm_management.Service.UserService;
import com.example.Farm_management.web.exception.BadRequestAlertException;
import com.example.Farm_management.Service.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserResource {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserResource(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) throws BadRequestAlertException {
        if (userDto.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an Id");
        }
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new BadRequestAlertException("Username already exists");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new BadRequestAlertException("Email address already exists");
        }
        if (userRepository.existsByPhone(userDto.getPhone())) {
            throw new BadRequestAlertException("Phone number already exists");
        }
        UserDto userDto1 = userService.saveUser(userDto);

        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUser(UserDto userDto) {
        List<UserDto> allUsers = userService.getAllUsers(userDto);
        if (allUsers.isEmpty()) {
            throw new BadRequestAlertException("No users are present in the database");
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<UserDto>> getUserById( @PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new BadRequestAlertException("user not found");
        }
        Optional<UserDto> getuser = userService.getUserbyid(id);
        return new ResponseEntity<>(getuser, HttpStatus.OK);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        if (!userRepository.existsById(id)) {
            throw new BadRequestAlertException("user not found");
        }
        UserDto result = userService.updateUser(userDto);
        return ResponseEntity.ok().body(result);

    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUsers(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new BadRequestAlertException("user not found");
        }
        userService.deleteUserById(id);
        String successMessage = "User with ID " + id + " has been deleted successfully.";
        return ResponseEntity.ok(successMessage);
    }

}

