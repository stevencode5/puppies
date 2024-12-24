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
public class UserDTO {

    private Integer id;
    private String name;
    private String email;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

}
