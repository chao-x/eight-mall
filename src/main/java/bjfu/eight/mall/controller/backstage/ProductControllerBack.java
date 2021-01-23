package bjfu.eight.mall.controller.backstage;

import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.Products;
import bjfu.eight.mall.entity.po.User;
import bjfu.eight.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ProductControllerBack {
    @Autowired
    ProductService productService;

    @PostMapping("/actionmall/mgr/product/productlist.do")
    @ResponseBody
    public HTTP productListBack(@RequestBody Products products, HttpSession session) {
        if((User)session.getAttribute("user")==null){
            return HTTP.ERROR("请先登录！",2);
        }
        if(products.getId()==null){
            return HTTP.SUCCESS(productService.findProductsByIDAll(-1));
        }else{
            return HTTP.SUCCESS(productService.findProductsByIDAll(products.getId()));
        }
    }

    @PostMapping("/actionmall/mgr/product/getdetail.do")
    @ResponseBody
    public HTTP getDetailBack(@RequestBody Products products, HttpSession session) {
        if((User)session.getAttribute("user")==null){
            return HTTP.ERROR("请先登录！",2);
        }
        return HTTP.SUCCESS(productService.findProductsByProductID(products));
    }

    @PostMapping("/actionmall/mgr/product/setstatus.do")
    @ResponseBody
    public HTTP setStatusBack(@RequestBody Products products, HttpSession session) {
        if((User)session.getAttribute("user")==null){
            return HTTP.ERROR("请先登录！",2);
        }
        if(productService.updateProduct(products)){
            return HTTP.SUCCESS("修改状态成功");
        }else{
            return HTTP.ERROR("修改状态失败");
        }
    }

    @PostMapping("/actionmall/mgr/product/saveproduct.do")
    @ResponseBody
    public HTTP saveProductBack(@RequestBody Products products, HttpSession session) {
        if((User)session.getAttribute("user")==null){
            return HTTP.ERROR("请先登录！",2);
        }
        if(productService.insertProduct(products)){
            return HTTP.SUCCESS("产品新增成功！");
        }else{
            return HTTP.ERROR("产品新增失败！");
        }

    }
}
