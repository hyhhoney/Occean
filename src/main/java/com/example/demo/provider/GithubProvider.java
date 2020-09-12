package com.example.demo.provider;

import com.alibaba.fastjson.JSON;
import com.example.demo.dto.AccessTakenDTO;
import com.example.demo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccesToken(AccessTakenDTO accessTakenDTO){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create( JSON.toJSONString(accessTakenDTO),mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String strin =response.body().string();
            String [] spilt = strin.split("&");
            String tokenstr = spilt[0];
            String token = tokenstr.split("=")[1];

            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
    public GithubUser getUser(String AccesToken) {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + AccesToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);

            return githubUser;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
