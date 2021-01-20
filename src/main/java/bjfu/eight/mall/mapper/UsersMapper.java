package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UsersMapper {
    //Integer insertUser(Users user);
    @Update("update action_users set del = 1 where id = #{id}")
    Integer deleteById(String id);

    @Select("select * from action_users where account = #{account}")
    public List<Users> selectByAccount(String account);
    @Select("select * from action_users where id = #{id}")
    public List<Users> selectById(String id);

    Integer updateUsers(Users users);

    public List<Users> getAll();
}
