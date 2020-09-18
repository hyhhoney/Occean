package com.example.demo.model;
import  lombok.Data;
@Data
public class User {
    private Integer id;
    private String name;
    private String acounnt_id;
    private String token;
    private Long gmt_creat;
    private Long gmt_modified;
    private String avatar_url;


}
