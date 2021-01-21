package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Products;
import bjfu.eight.mall.entity.vo.FindProducts;
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

    @Select("select * from action_products where is_hot = 1 and status <> 3")
    List<Products> findHotProducts();

    @Select("select * from action_products where id = #{id} and status <> 3")
    List<Products> findProductsByID(int id);

    @Select("select * from action_products where name like '%#{name}%' and product_id = #{productTypeId} and parts_id = #{partsId} and status <> 3 limit (#{pageNum}-1)*#{pageSize} offset #{pageSize}")
    List<Products> findProductsBySort(FindProducts findProducts);

}
