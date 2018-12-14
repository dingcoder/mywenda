package pers.mywenda.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.mywenda.model.Testlogin;

@Mapper
public interface TestLoginDao {
    @Select({"select * from testlogin where name = #{name}"})
    Testlogin queryUser(Testlogin testlogin);
}
