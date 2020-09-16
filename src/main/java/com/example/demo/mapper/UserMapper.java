package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,acounnt_id,token,gmt_creat,gmt_modified,avatar_url) values(#{name},#{acounntid},#{token},#{gmtcreat},#{gmtmodified},#{avatar_url})")
    void insert(User user);
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);
    @Select("select acounnt_id from user where token=#{token}")
    String getDbUserAcountid(@Param("token") String token);
    @Select("select id from user where token=#{token}")
    Integer getDbUserId(@Param("token") String token);
    @Select("select avator_url from user where token=#{token}")
    String getDbUserurl(@Param("token") String token);

}
