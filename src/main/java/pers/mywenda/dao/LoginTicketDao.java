package pers.mywenda.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import pers.mywenda.model.LoginTicket;

@Mapper
public interface LoginTicketDao {
    @Insert({"insert into login_ticket(user_id,expired,status,ticket) values(#{userId},#{expired},#{status}," +
            "#{ticket})"})
    int addTicket(LoginTicket loginTicket);

    @Update({"update login_ticket set status = #{i} where ticket = #{ticket}"})
    void updateStatus(String ticket, int i);
}
