package com.challenge.magalu.controller;

import com.challenge.magalu.dto.UserDTO;
import com.challenge.magalu.entity.User;
import com.challenge.magalu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDTO dto) {
       return service.createUser(dto);
    }
}
