package com.stevencode.puppies.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@IdClass(PostLikeId.class)
@Table(name = "POST_LIKE")
public class PostLike {

    @Id
    @ManyToOne
    @JoinColumn(name = "POST_ID", referencedColumnName = "id")
    private Post postId;

    @Id
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User userId;

}
