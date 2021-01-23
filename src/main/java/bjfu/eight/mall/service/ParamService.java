package bjfu.eight.mall.service;

import bjfu.eight.mall.entity.po.Params;
import bjfu.eight.mall.entity.vo.AllParams;
import bjfu.eight.mall.mapper.ParamsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;

import java.util.LinkedList;
import java.util.List;

@Service
public class ParamService {
    @Autowired
    ParamsMapper paramsMapper;

    public List<AllParams> findAllParams(){
        AllParams all_param=new AllParams();
        all_param.setParentId(0);
        List<AllParams>list=paramsMapper.SelectByParentID(all_param);
        for(AllParams param:list){
            List<AllParams>list2=paramsMapper.SelectByID(param);
            param.setChildren(list2);
            for(AllParams params2:list2){
                List<AllParams>list3=paramsMapper.SelectByID(params2);
                params2.setChildren(list3);
            }
        }
        return list;
    }
}
