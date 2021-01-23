package bjfu.eight.mall.controller.web;


import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.Order;
import bjfu.eight.mall.entity.po.User;
import bjfu.eight.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/actionmall/order/confirmreceipt.do")
    @ResponseBody
    public HTTP confrimReceipt(@RequestBody Order order){
        if(orderService.confirmReceipt(order.getOrderNo())!=0){
            return HTTP.SUCCESS("订单已确认收货！");
        }
        else return HTTP.ERROR("失败！");
    }

    @PostMapping("/actionmall/order/getdetail.do")
    @ResponseBody
    public HTTP getOrderDetail(@RequestBody HttpSession session,Order order){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
               return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(orderService.getdetail(order.getOrderNo()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }
}
