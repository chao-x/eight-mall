package bjfu.eight.mall.controller.web;

import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/actionmall/product/findfloors.do")
    @ResponseBody
    public HTTP findFloors(){
        return HTTP.SUCCESS(productService.findFloors());
    }

}
