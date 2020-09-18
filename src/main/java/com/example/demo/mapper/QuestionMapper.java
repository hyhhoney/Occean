package com.example.demo.mapper;

import com.example.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_creat,gmt_modified,creator,tags) values(#{title},#{description},#{gmt_creat},#{gmt_modified},#{creator},#{tags})")
    void creat(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size")Integer size);
    @Select("select count(1) from question")
    Integer count();
    @Select("select * from question where creator=#{creator} limit #{offset},#{size}")
    List<Question> listByCreator(@Param(value = "creator") Integer creator, @Param(value = "offset") Integer offset,  @Param(value = "size") Integer size);


    @Select("select count(1) from question where creator=#{creator}")
    Integer countBycreator(@Param(value = "creator") Integer creator);
}
