package bjfu.eight.mall.service;


import bjfu.eight.mall.entity.po.Users;
import bjfu.eight.mall.mapper.OrdersMapper;
import bjfu.eight.mall.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    OrdersMapper ordersMapper;

    public List<Users> selectUser(String id) {
        return usersMapper.selectById(id);
    }

    public boolean deleteUser(String id) {
        if (ordersMapper.getByUserid(id).size() > 0) {
            return false;
        }
        return usersMapper.deleteById(id) == 0;
    }

    public List<Users> getAllUser() {
        return usersMapper.getAll();
    }

    public boolean updateUser(Users user) {
        return usersMapper.updateUsers(user) == 0;
    }

    public List<Users> getUserByAccount(String account) {
        return usersMapper.selectByAccount(account);
    }
}
