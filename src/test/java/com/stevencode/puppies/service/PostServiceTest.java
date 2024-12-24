package com.stevencode.puppies.service;

import com.stevencode.puppies.repository.PostLikeRepository;
import com.stevencode.puppies.repository.PostRepository;
import com.stevencode.puppies.repository.UserRepository;
import com.stevencode.puppies.repository.model.Post;
import com.stevencode.puppies.repository.model.User;
import com.stevencode.puppies.service.dto.PostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostLikeRepository postLikeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PostService postService;

    private User user;
    private Post post;
    private PostDTO postDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        post = new Post();
        post.setId(1);
        post.setTextContent("This is a post");
        post.setUser(user);

        postDTO = new PostDTO();
        postDTO.setId(1);
        postDTO.setTextContent("This is a post");
    }

    @Test
    void testCreatePost() {
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Mockito.when(postRepository.save(any(Post.class))).thenReturn(post);
        Mockito.when(modelMapper.map(post, PostDTO.class)).thenReturn(postDTO);

        PostDTO result = postService.createPost("This is a post", 1, null);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("This is a post", result.getTextContent());
    }

    @Test
    void testLikePost() {
        Mockito.when(postRepository.findById(anyInt())).thenReturn(Optional.of(post));
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> postService.likePost(1, 1));
    }

    @Test
    void testGetPostByUser() {
        Mockito.when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Mockito.when(postRepository.findByUser(any(User.class))).thenReturn(List.of(post));
        Mockito.when(modelMapper.map(post, PostDTO.class)).thenReturn(postDTO);

        List<PostDTO> result = postService.getPostByUser(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("This is a post", result.get(0).getTextContent());
    }

    @Test
    void testGetPostDetails() {
        Mockito.when(postRepository.findById(anyInt())).thenReturn(Optional.of(post));
        Mockito.when(modelMapper.map(post, PostDTO.class)).thenReturn(postDTO);

        PostDTO result = postService.getPostDetails(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("This is a post", result.getTextContent());
    }

}