package bjfu.eight.mall.controller.web;

import bjfu.eight.mall.common.HTTP;
import bjfu.eight.mall.entity.po.Params;
import bjfu.eight.mall.entity.po.User;
import bjfu.eight.mall.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ParamController {

    @Autowired
    ParamService paramService;

    @GetMapping("/actionmall/param/findallparams.do")
    @ResponseBody
    public HTTP findAllParams() {
        return HTTP.SUCCESS(paramService.findAllParams());
    }

}
