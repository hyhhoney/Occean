package com.example.demo.controller;

import com.example.demo.dto.AccessTakenDTO;

import com.example.demo.dto.GithubUser;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
public class AuthorizeContraller {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecrect;
    @Value("${github.redirect.url}")
    private String clientUrl;


    @GetMapping("callback")
    public String callback(@RequestParam(name="code") String code, @RequestParam(name="state") String state, HttpServletRequest request, HttpServletResponse response){
        AccessTakenDTO accessTakenDTO = new AccessTakenDTO();

        accessTakenDTO.setClient_id(clientId);
        accessTakenDTO.setCode(code);
        accessTakenDTO.setState(state);
        accessTakenDTO.setRedirect_uri(clientUrl);
        accessTakenDTO.setClient_secret(clientSecrect);
        String str=githubProvider.getAccesToken(accessTakenDTO);

        GithubUser githubUser=githubProvider.getUser(str);
        if(githubUser!=null){
            User user =new User();
            String tk = UUID.randomUUID().toString();
            user.setToken(tk);
            user.setName(githubUser.getName());
            user.setAcounntid(String.valueOf(githubUser.getId()));
            user.setGmtcreat(System.currentTimeMillis());
            user.setGmtmodified(user.getGmtcreat());

            userMapper.insert(user);
            response.addCookie(new Cookie("token",tk));
//            request.getSession().setAttribute("user",githubUser);

            return "redirect:/";
            //登录成功
        }else{
            return "redirect:/";//登录失败
        }




    }
}
