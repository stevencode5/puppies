package com.stevencode.puppies.repository;

import com.stevencode.puppies.repository.model.Post;
import com.stevencode.puppies.repository.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

}
