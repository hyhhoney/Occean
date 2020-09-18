package com.example.demo.controller;

import com.example.demo.dto.PageDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileContraller {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(@RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "5") Integer size,
                          @PathVariable(name = "action") String action, Model model, HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
            return "redirect:/";
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

        if("question".equals(action)){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的提问");
        }
        if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");
        }
        PageDto pageDto= questionService.list2(user.getAcounnt_id(), page, size);
        model.addAttribute(pageDto);

        return "profile";
    }



}
