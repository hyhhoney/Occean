package com.example.demo.mapper;

import com.example.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(id,title,description,gmt_creat,gmt_modified,creator,tags) values(#{id},#{title},#{description},#{gmt_creat},#{gmt_modified},#{creator},#{tags})")
    void creat(Question question);
}
