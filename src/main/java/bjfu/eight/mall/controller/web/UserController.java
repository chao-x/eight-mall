package bjfu.eight.mall.controller.web;

import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.User;
import bjfu.eight.mall.entity.vo.Info;
import bjfu.eight.mall.entity.vo.Password;
import bjfu.eight.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/actionmall/user/getUserByAccount.do")
    @ResponseBody
    public HTTP findUserByAccount(@RequestBody User user) {
        if (userService.getUserByAccount(user.getAccount()).size() == 0) {
            return HTTP.ERROR("用户名错误！");
        } else {
            return HTTP.SUCCESS(userService.getUserByAccount(user.getAccount()));
        }
    }


    @PostMapping("/actionmall/user/do_logout.do")
    @ResponseBody
    public HTTP logout(HttpSession session) {
        session.removeAttribute("user");
        return HTTP.SUCCESS();
    }

    @PostMapping("/actionmall/user/updateuserinfo.do")
    @ResponseBody
    public HTTP updateUser(@RequestBody User user, HttpSession session) {
        if ((User) session.getAttribute("user") == null) {
            return HTTP.ERROR("请先登录！");
        }
        user.setId((((User) session.getAttribute("user"))).getId());
        if (userService.updateUser2(user)) {
            return HTTP.SUCCESS("用户信息修改成功", userService.selectUser(user.getId()));
        } else {
            return HTTP.ERROR("修改用户信息失败");
        }
    }

    @PostMapping("/actionmall/user/updatepassword.do")
    @ResponseBody
    public HTTP updatePassword(@RequestBody Password password, HttpSession session) {
        if ((User) session.getAttribute("user") == null) {
            return HTTP.ERROR("请先登录！");
        }
        if (userService.updatePassword(password.getOldpwd(), password.getNewpwd(), ((User) session.getAttribute("user")).getId())) {
            return HTTP.SUCCESS("密码修改成功！");
        } else {
            return HTTP.SUCCESS("密码修改失败，请确认旧密码是否正确！");
        }

    }

    @PostMapping("/actionmall/user/resetpassword.do")
    @ResponseBody
    public HTTP updatePassword2(@RequestBody Password password) {
        if (userService.updatePassword2(password.getNewpwd(), password.getUserId())) {
            return HTTP.SUCCESS("密码修改成功！");
        } else {
            return HTTP.SUCCESS("密码修改失败，请确认旧密码是否正确！");
        }

    }

    @PostMapping("/actionmall/user/checkuserasw.do")
    @ResponseBody
    public HTTP checkUserAsw(@RequestBody User user) {
        User check = userService.getUserByAccount(user.getAccount()).get(0);
        if (check.getAsw().equals(user.getAsw()) && user.getQuestion().equals(check.getQuestion())) {
            return HTTP.SUCCESS(check.getPassword());
        }
        return HTTP.ERROR("问题答案错误！");

    }

    @PostMapping("/actionmall/user/getuserquestion.do")
    @ResponseBody
    public HTTP getUserQuestion(@RequestBody User user) {
        User check = userService.getUserByAccount(user.getAccount()).get(0);
        if (!check.getQuestion().equals("")) {
            return HTTP.SUCCESS(check.getQuestion());
        }
        return HTTP.ERROR("未设置密码提示问题！");

    }

    @GetMapping("/actionmall/user/getuserinfo.do")
    @ResponseBody
    public HTTP getUserInfo(HttpSession session) {
        if ((User) session.getAttribute("user") == null) {
            return HTTP.ERROR("请先登录！");
        }
        return HTTP.SUCCESS(userService.selectUser(((User) session.getAttribute("user")).getId()));
    }

    @PostMapping("/actionmall/user/do_register.do")
    @ResponseBody
    public HTTP register(@RequestBody User user) {
        if (userService.insertUser(user)) {
            return HTTP.SUCCESS("注册成功！");
        } else {
            return HTTP.ERROR("注册失败！");
        }
    }

    @PostMapping("/actionmall/user/do_login.do")
    @ResponseBody
    public HTTP login(@RequestBody User user, HttpSession session) {
        List<User> users = new LinkedList<>();
        users = userService.getUserByAccount(user.getAccount());
        if (users.size() == 0) {
            return HTTP.ERROR("不是管理员,无法登录！");
        } else if (!users.get(0).getPassword().equals(user.getPassword())) {
            return HTTP.ERROR("密码错误！");
        } else {
            session.setAttribute("user", users.get(0));
            return HTTP.SUCCESS("登陆成功", users.get(0));
        }
    }

    @PostMapping("/actionmall/user/do_check_info.do")
    @ResponseBody
    public HTTP checkInfo(@RequestBody Info info) {
        List<User> list = userService.getAllUser();
        switch (info.getType()) {
            case "account": {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getAccount().equals(info.getInfo())) {
                        return HTTP.ERROR("用户名已经存在");
                    }
                }
                return HTTP.SUCCESS("信息验证成功！");
            }
            case "email": {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getEmail().equals(info.getInfo())) {
                        return HTTP.ERROR("Email已经存在");
                    }
                }
                return HTTP.SUCCESS("信息验证成功！");

            }

            case "phone": {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getPhone().equals(info.getInfo())) {
                        return HTTP.ERROR("电话号码已经存在！");
                    }
                }
                return HTTP.SUCCESS("信息验证成功！");
            }
            default:
                return HTTP.ERROR("信息验证类别错误！");
        }
    }
}
