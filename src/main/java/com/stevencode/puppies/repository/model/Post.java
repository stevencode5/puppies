package com.stevencode.puppies.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TEXT_CONTENT")
    private String textContent;

    @Lob
    @Column(name = "IMAGE")
    private byte[] image;

    @CreationTimestamp
    @Column(name = "CREATED_TIMESTAMP")
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(name = "MODIFIED_TIMESTAMP")
    private LocalDateTime modifiedTime;

}
