package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Products;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductsMapper {
    @Select("select distinct(product_id) from action_products ")
    List<Integer> findAllProductsID();

    @Select("select * from action_products where product_id = #{id}")
    List<Products> findProductByProductID(int id);

}
