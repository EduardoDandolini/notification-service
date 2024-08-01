package com.challenge.magalu.service;

import com.challenge.magalu.dto.UserDTO;
import com.challenge.magalu.entity.User;
import com.challenge.magalu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public User createUser(UserDTO dto) {
        return userRepository.save(User.builder()
                .name(dto.name())
                .cpf(dto.cpf())
                .build()
        );
    }
}
