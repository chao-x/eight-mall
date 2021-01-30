package bjfu.eight.mall.controller.backstage;


import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.*;
import bjfu.eight.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class OrderControllerBack {
    @Autowired
    private OrderService orderService;

    @PostMapping("/actionmall/mgr/order/search.do")
    @ResponseBody
    public HTTP searchorders(@RequestBody Order order, HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else if(user.getRole()!=1)
            {
                return HTTP.ERROR("不是管理员账号！");
            }
            else {
                return HTTP.SUCCESS(orderService.getdetail(order.getOrderNo()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }

    @PostMapping("/actionmall/mgr/order/findorders_nopages.do")
    @ResponseBody
    public HTTP findOrders(HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else if(user.getRole()!=1)
            {
                return HTTP.ERROR("不是管理员账号！");
            }
            else {
                return HTTP.SUCCESS(orderService.getAllList());
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }


}
