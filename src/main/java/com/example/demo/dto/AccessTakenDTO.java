package com.example.demo.dto;

import lombok.Data;

@Data
public class AccessTakenDTO {
    String client_id;
    String redirect_uri;
    String code;
    String state;
    String client_secret;




}
