package com.stevencode.puppies.service;

import com.stevencode.puppies.repository.PostLikeRepository;
import com.stevencode.puppies.repository.PostRepository;
import com.stevencode.puppies.repository.UserRepository;
import com.stevencode.puppies.repository.model.Post;
import com.stevencode.puppies.repository.model.PostLike;
import com.stevencode.puppies.repository.model.User;
import com.stevencode.puppies.service.dto.PostDTO;
import com.stevencode.puppies.service.dto.PostDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostLikeRepository portLikeRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public PostDTO createPost(String textContent, Integer userId, byte[] image) {
        log.info("Creating post");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found : " + userId));

        Post newPost = Post.builder()
                .textContent(textContent)
                .user(user)
                .image(image)
                .build();
        newPost = postRepository.save(newPost);
        log.info("Post created: {}", newPost.getId());
        return modelMapper.map(newPost, PostDTO.class);
    }

    public void likePost(Integer postId, Integer userId) {
        log.info("Liking post");

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found : " + postId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found : " + userId));

        PostLike postLike = new PostLike(post, user);

        portLikeRepository.save(postLike);
        log.info("Post liked: {}", postId);
    }

    public List<PostDTO> getPostByUser(Integer userId) {
        log.info("Getting posts by user id");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found : " + userId));

        List<Post> posts = postRepository.findByUser(user);
        log.info("Posts found: {}", posts.size());
        return posts.stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .toList();
    }

    public PostDTO getPostDetails(Integer postId) {
        log.info("Getting post details by id");

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found : " + postId));

        return modelMapper.map(post, PostDTO.class);
    }

}
