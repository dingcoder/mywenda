package pers.mywenda.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.mywenda.model.User;

@Mapper
public interface UserDAO {

    @Insert({"insert into user(name,password,salt,head_url) values(#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select * from user where name = #{name}"})
    User selectByName(String username);
}
