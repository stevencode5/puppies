package com.stevencode.puppies.service;

import com.stevencode.puppies.repository.UserRepository;
import com.stevencode.puppies.repository.model.User;
import com.stevencode.puppies.service.dto.CreateUserDTO;
import com.stevencode.puppies.service.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDTO getUserById(Integer userId) {
        log.info("Getting user by id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found : " + userId));
        log.info("User found: {}", user.getId());
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO createUser(CreateUserDTO user) {
        log.info("Creating user {}", user);

        userRepository.findByName(user.getName()).ifPresent(existingUser -> {
            throw new IllegalArgumentException("User already exists with name: " + user.getName());
        });
        userRepository.findByEmail(user.getEmail()).ifPresent(existingUser -> {
            throw new IllegalArgumentException("User already exists with email: " + user.getEmail());
        });

        User newUser = modelMapper.map(user, User.class);
        newUser = userRepository.save(newUser);
        log.info("User created: {}", newUser.getId());
        return modelMapper.map(newUser, UserDTO.class);
    }

}
