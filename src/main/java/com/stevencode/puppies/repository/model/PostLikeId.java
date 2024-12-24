package com.stevencode.puppies.repository.model;

import java.io.Serializable;

public class PostLikeId implements Serializable {

    private Integer postId;
    private Integer userId;

    public PostLikeId() {
    }

    public PostLikeId(Integer postId, Integer userId) {
        this.postId = postId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostLikeId that = (PostLikeId) o;
        return postId.equals(that.postId) && userId.equals(that.userId);
    }

}
