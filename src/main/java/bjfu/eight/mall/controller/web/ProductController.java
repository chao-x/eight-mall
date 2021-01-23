package bjfu.eight.mall.controller.web;

import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.Products;
import bjfu.eight.mall.entity.vo.FindProducts;
import bjfu.eight.mall.entity.vo.Num;
import bjfu.eight.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/actionmall/product/findhotproducts.do")
    @ResponseBody
    public HTTP findHotProducts(@RequestBody Num num){
        int last=num.getNum();
        if(num.getNum()>=productService.findHotProducts().size()){
            last=productService.findHotProducts().size();
        }
        return HTTP.SUCCESS(productService.findHotProducts().subList(0,last));
    }

    @PostMapping("/actionmall/product/getdetail.do")
    @ResponseBody
    public HTTP getDetail(@RequestBody Products products){
        if (products.getId() == null){
            return HTTP.ERROR("产品已经下架");
        }
        if(productService.findProductsByID(products.getId()).size()==0){
            return HTTP.ERROR("产品已经下架");
        }else{
            return HTTP.SUCCESS(productService.findProductsByID(products.getId()));
        }
    }

    @PostMapping("/actionmall/product/findproducts.do")
    @ResponseBody
    public HTTP findProducts(@RequestBody FindProducts findProducts){
        return HTTP.SUCCESS(productService.findProducts(findProducts));
    }

}
