package com.example.blogex.domain.posttag.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HashtagSearchRequest {
    private List<String> tags;
}
