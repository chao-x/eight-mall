package bjfu.eight.mall.controller.web;


import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.*;
import bjfu.eight.mall.entity.vo.OneItem;
import bjfu.eight.mall.entity.vo.OrderDetail;
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
    public HTTP getOrderDetail(@RequestBody Order order,HttpSession session){
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

    @PostMapping("/actionmall/order/cancelorder.do")
    @ResponseBody
    public HTTP cancelOrder(@RequestBody Order order){
        if(orderService.cancelOrder(order.getOrderNo())!=0){
            return HTTP.SUCCESS("该订单已取消！");
        }
        else return HTTP.ERROR("失败！");
    }

    @PostMapping("/actionmall/order/getlist.do")
    @ResponseBody
    public HTTP getList(@RequestBody Order order,HttpSession session){
        try{
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(orderService.getList(user.getId(),order.getStatus()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }

    @PostMapping("/actionmall/order/createorder.do")
    @ResponseBody
    public HTTP createOrder(@RequestBody Order order,HttpSession session){
        try{
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                OrderDetail orderDetail=orderService.createOrder(order.getAddrId(),user.getId());
                if(orderDetail.getStatus()==-1){
                    return HTTP.ERROR("购物车为空，请选择要购买的商品");
                }
                else if(orderDetail.getStatus()==-2) {
                    return HTTP.ERROR("商品已下架");
                }
                else {
                    return HTTP.SUCCESS(orderDetail);
                }
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }

    @PostMapping("/actionmall/order/createoneorder.do")
    @ResponseBody
    public HTTP createOneOrder(@RequestBody OneItem oneItem, HttpSession session){
        try{
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(orderService.createOneOrder(oneItem.getAddrId(),oneItem.getProductId(),oneItem.getQuantity(),user.getId()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }
}
