package com.stevencode.puppies.rest;

import com.stevencode.puppies.rest.exception.ImageProcessException;
import com.stevencode.puppies.service.PostService;
import com.stevencode.puppies.service.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    // Create a post: They should have an image, some text content, and a date
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<PostDTO> createPost(@RequestParam("image") MultipartFile image,
                                              @RequestParam("userId") Integer userId,
                                              @RequestParam("textContent") String textContent) {
        log.info("Receiving request to create post");
        log.info("Text content: {}", textContent);

        byte[] imageByte = validateFile(image);

        PostDTO newPost = postService.createPost(textContent, userId, imageByte);
        return ResponseEntity.ok().body(newPost);
    }

    // Like a post
    @PostMapping(value = "/like")
    public ResponseEntity<Void> likePost(@RequestParam("postId") Integer postId,
                                         @RequestParam("userId") Integer userId) {
        log.info("Receiving request to like post");
        postService.likePost(postId, userId);
        return ResponseEntity.ok().build();
    }

    private byte[] validateFile(MultipartFile image) {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("No file uploaded");
        }

        // Check if the file is an image
        String contentType = image.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Uploaded file is not an image");
        }

        try {
            return image.getBytes();
        } catch (IOException e) {
            throw new ImageProcessException("Error processing image " + e.getMessage());
        }
    }

    // Fetch details of an individual post
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Integer id) {
        log.info("Receiving request to get post by Id {}", id);
        PostDTO post = postService.getPostDetails(id);
        return ResponseEntity.ok().body(post);
    }

    // Fetch a list of posts the user made
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable("userId") Integer userId) {
        log.info("Receiving request to get post by user {}", userId);
        List<PostDTO> posts = postService.getPostByUser(userId);
        return ResponseEntity.ok().body(posts);
    }

}
