package com.stevencode.puppies.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stevencode.puppies.service.PostService;
import com.stevencode.puppies.service.dto.PostDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    @Test
    void testGetPostByUser() throws Exception {
        PostDTO postDTO1 = new PostDTO();
        postDTO1.setId(1);
        postDTO1.setTextContent("This is a text content");

        PostDTO postDTO2 = new PostDTO();
        postDTO2.setId(2);
        postDTO2.setTextContent("This is another text content");

        List<PostDTO> postDTOList = Arrays.asList(postDTO1, postDTO2);

        Mockito.when(postService.getPostByUser(Mockito.anyInt()))
                .thenReturn(postDTOList);

        mockMvc.perform(get("/api/post/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"textContent\":\"This is a text content\"},{\"id\":2,\"textContent\":\"This is another text content\"}]"));
    }

    @Test
    void testGetPostDetails() throws Exception {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(1);
        postDTO.setTextContent("This is a text content");

        Mockito.when(postService.getPostDetails(Mockito.anyInt()))
                .thenReturn(postDTO);

        mockMvc.perform(get("/api/post/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"textContent\":\"This is a text content\"}"));
    }

    @Test
    void testLikePost() throws Exception {
        Mockito.doNothing().when(postService).likePost(Mockito.anyInt(), Mockito.anyInt());

        mockMvc.perform(post("/api/post/like")
                        .param("postId", "1")
                        .param("userId", "1"))
                .andExpect(status().isOk());
    }

}