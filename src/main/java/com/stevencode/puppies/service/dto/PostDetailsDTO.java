package com.stevencode.puppies.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostDetailsDTO extends PostDTO {

    private Integer likeCount;

}
