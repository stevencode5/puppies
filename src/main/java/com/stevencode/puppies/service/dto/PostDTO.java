package com.stevencode.puppies.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDTO {

    private Integer id;
    private String textContent;
    private byte[] image;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

}
