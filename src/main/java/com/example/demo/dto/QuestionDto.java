package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;

@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private String tags;
    private Long gmt_creat;
    private Long gmt_modified;
    private Integer creator;
    private Integer comment_count;
    private Integer like_count;
    private Integer view_count;
    private User user;
}
