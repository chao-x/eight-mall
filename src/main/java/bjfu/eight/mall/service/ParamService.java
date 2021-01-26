package bjfu.eight.mall.service;

import bjfu.eight.mall.entity.po.Params;
import bjfu.eight.mall.entity.vo.AllParams;
import bjfu.eight.mall.entity.vo.ParamList;
import bjfu.eight.mall.entity.vo.ParamMini;
import bjfu.eight.mall.mapper.ParamsMapper;
import bjfu.eight.mall.mapper.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ParamService {
    @Autowired
    ParamsMapper paramsMapper;
    @Autowired
    ProductsMapper productsMapper;

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

    public int delparam(int id){
        if(paramsMapper.findChiledren(id).size()!=0){
            return -1;
        }
        else if(productsMapper.findProductByProductID(id).size()!=0&&productsMapper.findProductByPartsId(id).size()!=0){
            return -2;
        }
        else {
            paramsMapper.delParam(id);
            return 1;
        }
    }

    public ParamList findParts(int id){
        Params[] params=paramsMapper.findChiledren(id).toArray(new Params[paramsMapper.findChiledren(id).size()]);
        ArrayList<ParamMini> paramMinis=new ArrayList<ParamMini>();
        for(Params param:params){
            ParamMini paramMini=new ParamMini();
            paramMini.setId(param.getId());
            paramMini.setName(param.getName());
            paramMinis.add(paramMini);
        }
        ParamList paramList=new ParamList();
        paramList.setParamMinis(paramMinis.toArray(new ParamMini[paramMinis.size()]));
        return paramList;
    }

    public List<AllParams> findptype(){
        AllParams allParams=new AllParams();
        allParams.setId(0);
        return paramsMapper.SelectByID(allParams);
    }

    public List<AllParams> findchildren(int id){
        AllParams allParams=new AllParams();
        allParams.setId(id);
        return paramsMapper.SelectByID(allParams);
    }

    public int updateparam(int id,String name) {
        List<Params> params = paramsMapper.getall();
        for (Params param : params) {
            if (param.getName().equals(name)) {
                return -1;
            }

        }

        paramsMapper.updateparam(name, id);
        return 1;

    }

    public int saveParam(int parentid,String name){
        List<Params> params = paramsMapper.getall();
        for (Params param : params) {
            if (param.getName().equals(name)) {
                return -1;
            }

        }

        paramsMapper.saveParam(name, parentid);
        return 1;
    }
}
