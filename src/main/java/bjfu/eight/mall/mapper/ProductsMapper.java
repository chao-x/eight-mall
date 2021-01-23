package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Products;
import bjfu.eight.mall.entity.vo.FindProducts;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select * from action_products where id = #{id}")
    List<Products> findProductsByIDAll(int id);

    @Select("select * from action_products")
    List<Products> findProductsAll();

    @Select("select * from action_products where name like '%${name}%' and product_id = #{productTypeId} and parts_id = #{partsId} and status <> 3 limit #{pageNum} , #{pageSize}")
    List<Products> findProductsBySort(FindProducts findProducts);

    @Update("update action_products set status=#{status},hot=#{hot} where id=#{id}")
        Integer updateById(Products products);

@Insert("insert into action_product(name,product_id,parts_id,detail,spec_param,price,stock,sub_images) values(#{name},#{product_id},#{parts_id},#{detail},#{spec_param},#{price},#{stock},#{sub_images})")
    Integer insertProduct(Products products);

@Update("update action_product set name=#{name},product_id=#{product_id},parts_id=#{parts_id},detail=#{detail},spec_param=#{spec_param},price=#{price},stock=#{price},sub_images=#{price}")
    Integer updateProduct(Products products);
            }
