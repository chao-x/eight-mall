package bjfu.eight.mall.controller.web;


import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.Address;
import bjfu.eight.mall.entity.po.User;
import bjfu.eight.mall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/actionmall/addr/findAddressById.do")
    @ResponseBody
    public HTTP findAddrById(@RequestBody Address address){
        return HTTP.SUCCESS(addressService.getAddressById(address.getId()));
    }

    @PostMapping("/actionmall/addr/setdefault.do")
    @ResponseBody
    public HTTP setDefault(@RequestBody Address address,HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(addressService.setDefault(address.getId(),user.getId()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }

    @PostMapping("/actionmall/addr/findaddrs.do")
    @ResponseBody
    public HTTP findAddrs(HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(addressService.findaddrs(user.getId()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }

    @PostMapping("/actionmall/addr/deladdr.do")
    @ResponseBody
    public HTTP deladdr(@RequestBody Address address,HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(addressService.delAddr(address.getId(),user.getId()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }

    @PostMapping("/actionmall/addr/saveaddr.do")
    @ResponseBody
    public HTTP saveaddr(@RequestBody Address address,HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            if(user==null){
                return HTTP.ERROR("请登录后再进行操作！");
            }
            else {
                return HTTP.SUCCESS(addressService.saveAddr(address,user.getId()));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            return HTTP.ERROR(e.getMessage());
        }
    }
}
