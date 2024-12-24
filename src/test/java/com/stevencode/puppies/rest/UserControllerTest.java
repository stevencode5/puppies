package com.stevencode.puppies.rest;

import com.stevencode.puppies.service.UserService;
import com.stevencode.puppies.service.dto.CreateUserDTO;
import com.stevencode.puppies.service.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testCreateUser() throws Exception {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setName("John Doe");
        createUserDTO.setEmail("john.doe@example.com");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setName("John Doe");
        userDTO.setEmail("john.doe@example.com");

        Mockito.when(userService.createUser(Mockito.any(CreateUserDTO.class)))
                .thenReturn(userDTO);

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"}"));
    }

}