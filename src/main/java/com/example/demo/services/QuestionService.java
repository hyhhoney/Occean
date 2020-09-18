package com.example.demo.services;

import com.example.demo.dto.PageDto;
import com.example.demo.dto.QuestionDto;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    public PageDto list(Integer page, Integer size) {
        Integer totalCount = questionMapper.count();

        Integer totalPage = 0;
        if(totalCount%size==0){
            totalPage = totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        Integer offset=size*(page-1);



        List<Question> questions=questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        PageDto pageDto = new PageDto();
        for (Question question : questions) {
            User user=userMapper.findById((Integer.toString(question.getCreator())));
            QuestionDto questionDto = new QuestionDto();

            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        pageDto.setQuestionDtos(questionDtoList);
        pageDto.setPageAllthing(totalCount,page,size);

        return pageDto ;
    }

    public  PageDto  list2(String acounnt_id, Integer page, Integer size) {
        Integer creator=Integer.valueOf(acounnt_id);
        Integer totalCount = questionMapper.countBycreator(creator);

        Integer totalPage = 0;
        if(totalCount%size==0){
            totalPage = totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }
        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        Integer offset=size*(page-1);


        List<Question> questions=questionMapper.listByCreator(creator,offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        PageDto pageDto = new PageDto();
        for (Question question : questions) {
            User user=userMapper.findById((Integer.toString(question.getCreator())));
            QuestionDto questionDto = new QuestionDto();

            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        pageDto.setQuestionDtos(questionDtoList);
        pageDto.setPageAllthing(totalCount,page,size);

        return pageDto ;

    }
}
