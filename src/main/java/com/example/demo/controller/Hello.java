package com.example.demo.controller;

import com.example.demo.dto.PageDto;
import com.example.demo.dto.QuestionDto;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.services.QuestionService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
public class Hello {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @RequestMapping ("")
    public String index(HttpServletRequest request,Model model,@RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size){
        PageDto pageDto=questionService.list(page,size);
        model.addAttribute("pageDto",pageDto);




        Cookie[] cookies=request.getCookies();
        if(cookies==null){
            return "Index";
        }
        User user=null;

        for(Cookie i:cookies){

            if(i.getName().equals("token")){

                String token = i.getValue();
                user = userMapper.findByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);


                }
                break;
            }
        }
        System.out.println(user);

        return "Index";

    }
}
