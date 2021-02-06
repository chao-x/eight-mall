package bjfu.eight.mall.controller.backstage;

import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.User;
import bjfu.eight.mall.service.OrderService;
import bjfu.eight.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

//用户模块
@Controller
public class UserControllerBack {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    //更新用户信息接口
    @PostMapping("/actionmall/mgr/user/updateuser.do")
    @ResponseBody
    public HTTP updateUserBack(@RequestBody User user) {
        if (userService.updateUser(user)) {
            return HTTP.SUCCESS("用户信息修改成功", user);
        } else {
            return HTTP.ERROR("修改用户信息失败");
        }
    }

    //查找用户信息接口
    @PostMapping("/actionmall/mgr/user/finduser.do")
    @ResponseBody
    public HTTP findUserBack(@RequestBody User user) {
        List<User> users = new LinkedList<>();
        users = userService.selectUser(user.getId());
        if (users.size() == 0) {
            return HTTP.ERROR("获取用户信息失败");
        } else {
            return HTTP.SUCCESS("用户信息修改成功", users.get(0));
        }

    }

    //删除用户接口
    @PostMapping("/actionmall/mgr/user/deleteusers.do")
    @ResponseBody
    public HTTP deleteUserBack(@RequestBody User user) {
        if (orderService.getList(user.getId(),0).getOrderDetails().length!=0) {
            return HTTP.ERROR("用户存在订单无法删除");
        } else {
            userService.deleteUser(user.getId());
            return HTTP.SUCCESS();
        }
    }

    //查询用户列表接口
    @PostMapping("/actionmall/mgr/user/finduserlist.do")
    @ResponseBody
    public HTTP findAllUserBack() {
        return HTTP.SUCCESS(userService.getAllUser());
    }

    //后台管理用户登录接口
    @PostMapping("/actionmall/mgr/user/login.do")
    @ResponseBody
    public HTTP loginBack(@RequestBody User user, HttpSession session) {
        List<User> users = new LinkedList<>();
        users = userService.getUserByAccount(user.getAccount());
        if (users.size() == 0 || !users.get(0).getPassword().equals(user.getPassword())) {
            return HTTP.ERROR("密码错误！");
        } else if (users.get(0).getRole() != 1) {
            return HTTP.ERROR("不是管理员,无法登录！");
        } else {
            session.setAttribute("user",user);
            return HTTP.SUCCESS("登陆成功", users.get(0));
        }
    }
}
