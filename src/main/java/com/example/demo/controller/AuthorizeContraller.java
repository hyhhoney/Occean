package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.dto.AccessTakenDTO;

import com.example.demo.dto.GithubUser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.provider.GithubProvider;

import java.io.IOException;


@Controller
public class AuthorizeContraller {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecrect;
    @Value("${github.redirect.url}")
    private String clientUrl;


    @GetMapping("callback")
    public String callback(@RequestParam(name="code") String code,@RequestParam(name="state") String state){
        AccessTakenDTO accessTakenDTO = new AccessTakenDTO();

        accessTakenDTO.setClient_id(clientId);
        accessTakenDTO.setCode(code);
        accessTakenDTO.setState(state);
        accessTakenDTO.setRedirect_uri(clientUrl);
        accessTakenDTO.setClient_secret(clientSecrect);
        String str=githubProvider.getAccesToken(accessTakenDTO);
        GithubUser githubUser=githubProvider.getUser(str);
        System.out.println(githubUser.getId());


        /*OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + str)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);

            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            System.out.println(githubUser.getId());

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return "Index";
    }
}
