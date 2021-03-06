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
        return usersMapper.updateUsers(user) == 1;
    }

    public boolean updateUser2(User user) {
        return usersMapper.updateUsers2(user) == 1;
    }

    public boolean updatePassword(String oldpwd, String newpwd, int id) {
        return usersMapper.updatePassword(oldpwd, newpwd, id) == 1;
    }

    public boolean updatePassword2(String newpwd, int id) {
        return usersMapper.updatePassword2(newpwd, id) == 1;
    }

    public List<User> getUserByAccount(String account) {
        return usersMapper.selectByAccount(account);
    }

    public boolean insertUser(User user) {
        return usersMapper.insertUser(user) == 1;
    }
}
