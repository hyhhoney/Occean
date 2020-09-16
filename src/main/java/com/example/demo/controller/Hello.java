package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Hello {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping ("")
    public String index(HttpServletRequest request){

        Cookie[] cookies=request.getCookies();

        for(Cookie i:cookies){

            if(i.getName().equals("token")){

                String token = i.getValue();
                User user = userMapper.findByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);


                }
                break;
            }
        }

        return "Index";

    }
}
