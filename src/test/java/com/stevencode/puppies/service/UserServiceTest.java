package com.stevencode.puppies.service;

import com.stevencode.puppies.repository.UserRepository;
import com.stevencode.puppies.repository.model.User;
import com.stevencode.puppies.service.dto.CreateUserDTO;
import com.stevencode.puppies.service.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;
    private CreateUserDTO createUserDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setName("John Doe");
        userDTO.setEmail("john.doe@example.com");

        createUserDTO = new CreateUserDTO();
        createUserDTO.setName("John Doe");
        createUserDTO.setEmail("john.doe@example.com");
    }

    @Test
    void getUserById() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    void createUser() {
        Mockito.when(userRepository.findByName(anyString())).thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
        Mockito.when(modelMapper.map(createUserDTO, User.class)).thenReturn(user);
        Mockito.when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO result = userService.createUser(createUserDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
    }
}