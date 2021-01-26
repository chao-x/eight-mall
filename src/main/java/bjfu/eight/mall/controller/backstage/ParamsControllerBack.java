package bjfu.eight.mall.controller.backstage;

import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.Params;
import bjfu.eight.mall.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParamsControllerBack {
    @Autowired
    private ParamService paramService;

    @PostMapping("/actionmall/mgr/param/delparam.do")
    @ResponseBody
    public HTTP delparam(@RequestBody Params params){
        int t=paramService.delparam(params.getId());
        if(t==-1){
            return HTTP.ERROR("请先删除子类型！");
        }
        else if(t==-2){
            return HTTP.ERROR("不能删除有商品的类型！");
        }
        else {
            return HTTP.SUCCESS("删除成功");
        }
    }

    @PostMapping("/actionmall/mgr/param/findpartstype.do")
    @ResponseBody
    public HTTP findparts(@RequestBody Params params){
        return HTTP.SUCCESS(paramService.findParts(params.getId()));
    }

    @PostMapping("/actionmall/mgr/param/findptype.do")
    @ResponseBody
    public HTTP findptype(){
        return HTTP.SUCCESS(paramService.findptype());
    }

    @PostMapping("/actionmall/mgr/param/findchildren.do")
    @ResponseBody
    public HTTP findchildren(@RequestBody Params params){
        return HTTP.SUCCESS(paramService.findchildren(params.getId()));
    }

    @PostMapping("/actionmall/mgr/param/updateparam.do")
    @ResponseBody
    public HTTP updateparam(@RequestBody Params params){
        int t=paramService.updateparam(params.getId(),params.getName());
        if(t==-1){
            return HTTP.ERROR("不能修改为与其他类型重名的名字！");
        }
        else if(t==1){
            return HTTP.SUCCESS("成功");
        }
        else {
            return HTTP.ERROR("失败");
        }
    }

    @PostMapping("/actionmall/mgr/param/saveparam.do")
    @ResponseBody
    public HTTP saveParam(@RequestBody Params params){
        int t=paramService.saveParam(params.getParentId(),params.getName());
        if(t==-1){
            return HTTP.ERROR("不能新增与其他类型重名的类型！");
        }
        else {
            return HTTP.SUCCESS("成功");
        }
    }
}
