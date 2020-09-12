package com.example.demo.dto;

public class GithubUser {
    private String login;
    private long id;
    private String bio;

    public String logIn() {
        return login;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
