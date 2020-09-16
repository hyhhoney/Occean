package com.example.demo.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String login;
    private long id;
    private String bio;
    private  String name;
    private String avatar_url;//头像url

}
