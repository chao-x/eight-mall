package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.User;
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
    Integer deleteById(int id);

    @Select("select * from action_users where account = #{account}")
    public List<User> selectByAccount(String account);

    @Select("select * from action_users where id = #{id}")
    public List<User> selectById(int id);

    Integer updateUsers(User users);

    @Select("select * from action_users where del = 0")
    public List<User> getAll();
}
