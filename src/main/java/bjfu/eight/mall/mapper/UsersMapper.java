package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UsersMapper {
    @Insert("insert into action_users(account,password,email,phone,question,asw,role) values(#{account},#{password},#{email},#{phone},#{question},#{asw},0)")
    public Integer insertUser(User user);

    @Update("update action_users set del = 1 where id = #{id}")
    public Integer deleteById(int id);

    @Select("select * from action_users where account = #{account}")
    public List<User> selectByAccount(String account);

    @Select("select * from action_users where id = #{id}")
    public List<User> selectById(int id);

    @Update("update action_users set name=#{name},account=#{account},age=#{age},phone=#{phone},email=#{email},sex=#{sex} where id=#{id}")
    public Integer updateUsers(User users);

    @Update("update action_users set email=#{email},phone=#{phone},question=#{question},asw=#{asw},sex=#{sex},age=#{age},name=#{name} where id=#{id}")
    public Integer updateUsers2(User users);

    @Update("update action_users set password=#{newpwd} where id=#{id} and password=#{oldpwd}")
    public Integer updatePassword(String oldpwd,String newpwd,int id);

    @Update("update action_users set password=#{newpwd} where id=#{id}")
    public Integer updatePassword2(String newpwd,int id);

    @Select("select * from action_users where del = 0")
    public List<User> getAll();

    @Select("select * from action_users where account = #{account}")
    public List<User> getUserByAccount(String account);
}
