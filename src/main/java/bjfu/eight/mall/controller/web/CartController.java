package bjfu.eight.mall.controller.web;


import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.*;
import bjfu.eight.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/actionmall/cart/getcartcount.do")
    @ResponseBody
    public HTTP getCartCount(HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(cartService.getCartCount(user.getId()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }

    @PostMapping("/actionmall/cart/updatecarts.do")
    @ResponseBody
    public HTTP updateCarts(@RequestBody Cart cart,HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(cartService.updateCarts(cart.getProductId(),cart.getQuantity(),user.getId()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }

    @PostMapping("/actionmall/cart/clearcarts.do")
    @ResponseBody
    public HTTP clearCarts(HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                cartService.clearCarts(user.getId());
                return HTTP.SUCCESS("成功清空购物车");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }

    @PostMapping("/actionmall/cart/delcarts.do")
    @ResponseBody
    public HTTP delCarts(@RequestBody Cart cart,HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(cartService.delCart(cart.getProductId(),user.getId()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }

    @PostMapping("/actionmall/cart/findallcarts.do")
    @ResponseBody
    public HTTP findAllCarts(HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(cartService.findAllCarts(user.getId()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }

    @PostMapping("/actionmall/cart/savecart.do")
    @ResponseBody
    public HTTP saveCart(@RequestBody Cart cart,HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(cartService.saveCarts(cart,user.getId()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }
}
