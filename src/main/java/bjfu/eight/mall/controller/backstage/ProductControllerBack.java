package bjfu.eight.mall.controller.backstage;

import bjfu.eight.mall.common.FileUtil;
import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.File;
import bjfu.eight.mall.entity.po.Products;
import bjfu.eight.mall.entity.po.User;
import bjfu.eight.mall.entity.vo.ImageURL;
import bjfu.eight.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

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

    @ResponseBody
    @RequestMapping("/mgr/product/upload.do")
    public HTTP uploadImage(String filename, MultipartFile stream, HttpServletRequest request){
        if(stream == null){
            return HTTP.ERROR("参数错误");
        }
        if(filename == null){
            filename = stream.getOriginalFilename();
        }
        if(stream.isEmpty()){
            return HTTP.ERROR("不能上传空文件");
        }
        try {
            int id = productService.uploadFile(filename, stream.getBytes());
            if(id == 0){
                return HTTP.ERROR("上传图片失败");
            }
            String url = "/upload/" + id;
            ImageURL imageURL = new ImageURL();
            imageURL.setAssessId(id);
            imageURL.setUrl(url);
            return HTTP.SUCCESS(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
            return HTTP.ERROR("上传图片出现异常");
        }
    }

    @RequestMapping("/upload/{id}")
    public void downloadImage(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response){
        File file = productService.downloadFile(id);
        try {
            OutputStream os = response.getOutputStream();
            //如果文件不存在或不是合理的文件名，则直接返回
            if(file == null ){
                os.close();
                return;
            }
            //否则以流的形式返回文件
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getFilename());
            os.write(file.getStream());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
