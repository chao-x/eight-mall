package bjfu.eight.mall.service;


import bjfu.eight.mall.entity.po.User;
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

    public List<User> selectUser(int id) {
        return usersMapper.selectById(id);
    }

    public boolean deleteUser(int id) {
        if (ordersMapper.getByUserid(id).size() > 0) {
            return false;
        }
        return usersMapper.deleteById(id) == 0;
    }

    public List<User> getAllUser() {
        return usersMapper.getAll();
    }

    public boolean updateUser(User user) {
        return usersMapper.updateUsers(user) == 0;
    }

    public List<User> getUserByAccount(String account) {
        return usersMapper.selectByAccount(account);
    }
}
