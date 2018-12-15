package pers.mywenda.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import pers.mywenda.model.LoginTicket;

@Mapper
public interface LoginTicketDao {
    @Insert({"insert into login_ticket(user_id,expired,status,ticket) values(#{userId,#{expired},#{status},#{ticket})"})
    int addTicket(LoginTicket loginTicket);
}
