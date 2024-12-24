package com.stevencode.puppies.rest;

import com.stevencode.puppies.service.UserService;
import com.stevencode.puppies.service.dto.CreateUserDTO;
import com.stevencode.puppies.service.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // Create a user: They should have a name and an email
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid CreateUserDTO user) {
        log.info("Receiving request to create user");
        UserDTO newUser = userService.createUser(user);
        return ResponseEntity.ok().body(newUser);
    }

}
