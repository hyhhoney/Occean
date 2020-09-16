package com.example.demo.controller;

import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @GetMapping(value = "/publish",params = "title")
    public String doPublish(@RequestParam("title") String title, @RequestParam("description") String description , @RequestParam("tag") String tag, HttpServletRequest request,  Model model){
        System.out.println("?");
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title==null||title==""){
            model.addAttribute("error","title不能为空");
            return "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","description不能为空");
            return "publish";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","tag不能为空");
            return "publish";
        }


        User user;
        Cookie[] cookies=request.getCookies();
        for(Cookie i:cookies) {
            if (i.getName().equals("token")) {
                String token = i.getValue();
                user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    Question question = new Question();
                    question.setId(userMapper.getDbUserId(token));
                    question.setCreator(Integer.valueOf(userMapper.getDbUserAcountid(token)));
                    question.setTitle(title);
                    question.setTags(tag);
                    question.setDescription(description);
                    question.setGmt_creat(System.currentTimeMillis());
                    question.setGmt_modified(question.getGmt_creat());
                    questionMapper.creat(question);
                    break;
                }
                else {
                        model.addAttribute("error","用户未登录");
                        return "publish";
                }
            }

        }

        return "redirect:/";

    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }


}
