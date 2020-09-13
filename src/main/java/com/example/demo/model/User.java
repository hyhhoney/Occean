package com.example.demo.model;

public class User {
    private Integer id;
    private String name;
    private String acounntid;
    private String token;
    private Long gmtcreat;
    private Long gmtmodified;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcounntid() {
        return acounntid;
    }

    public void setAcounntid(String acounntid) {
        this.acounntid = acounntid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGmtcreat() {
        return gmtcreat;
    }

    public void setGmtcreat(Long gmtcreat) {
        this.gmtcreat = gmtcreat;
    }

    public Long getGmtmodified() {
        return gmtmodified;
    }

    public void setGmtmodified(Long gmtmodified) {
        this.gmtmodified = gmtmodified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", acounntid='" + acounntid + '\'' +
                ", token='" + token + '\'' +
                ", gmtcreat=" + gmtcreat +
                ", gmtmodified=" + gmtmodified +
                '}';
    }
}
