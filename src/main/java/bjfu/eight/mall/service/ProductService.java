package bjfu.eight.mall.service;

import bjfu.eight.mall.entity.po.Products;
import bjfu.eight.mall.entity.vo.Floors;
import bjfu.eight.mall.mapper.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductsMapper productsMapper;

    public Floors findFloors(){
        int floors_length;
        Floors floors=new Floors();
        List<Integer> product_ids=productsMapper.findAllProductsID();
        if (product_ids.size()>=4){
            floors_length=4;
        }else{
            floors_length=product_ids.size();
        }
        for(int i=1;i<=floors_length;i++){
            List<Products>result=productsMapper.findProductByProductID(product_ids.get(i-1));
            switch (i){
                case 1:
                    floors.setOneFloor(result);
                    break;
                case 2:
                    floors.setTwoFloor(result);
                    break;
                case 3:
                    floors.setThreeFloor(result);
                    break;
                case 4:
                    floors.setFourFloor(result);
                    break;
            }
        }
        return floors;
    }
}
