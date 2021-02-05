package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.File;
import bjfu.eight.mall.entity.po.Products;
import bjfu.eight.mall.entity.vo.FindProducts;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductsMapper {
    @Select("select distinct(product_id) from action_products ")
    List<Integer> findAllProductsID();

    @Select("select * from action_products where product_id = #{id}")
    List<Products> findProductByProductID(int id);

    @Select("select * from action_products where parts_id=#{id}")
    List<Products> findProductByPartsId(int id);

    @Select("select * from action_products where product_id = #{productId}")
    List<Products> findProductByProduct_ID(Products products);

    @Select("select * from action_products where is_hot = 1 and status <> 3")
    List<Products> findHotProducts();

    @Select("select * from action_products where id = #{id} and status <> 3")
    List<Products> findProductsByID(int id);

    @Select("select * from action_products where id = #{id}")
    List<Products> findProductsByIDAll(int id);

    @Select("select * from action_products where id = #{id}")
    Products getProductById(int id);

    @Select("select * from action_products")
    List<Products> findProductsAll();

    @Select("select * from action_products where name like '%${name}%' or product_id = #{productTypeId} or parts_id = #{partsId} and status <> 3 limit #{pageNum} , #{pageSize}")
    List<Products> findProductsBySort(FindProducts findProducts);

    @Select("select * from action_products where name like '%${name}%' and product_id = #{productTypeId} or parts_id = #{partsId} and status <> 3 limit #{pageNum} , #{pageSize}")
    List<Products> findProductsBySort2(FindProducts findProducts);

    @Select("select COUNT(*) from action_products where name like '%${name}%' or product_id = #{productTypeId} or parts_id = #{partsId} and status <> 3")
    int countfindProductsBySort(FindProducts findProducts);

    @Select("select COUNT(*) from action_products where name like '%${name}%' and product_id = #{productTypeId} or parts_id = #{partsId} and status <> 3")
    int countfindProductsBySort2(FindProducts findProducts);

    @Update("update action_products set status=#{status},is_hot=#{isHot} where id=#{id}")
    Integer updateById(Products products);

    @Insert("insert into action_products(name,product_id,parts_id,detail,spec_param,price,stock,sub_images) values(#{name},#{productId},#{partsId},#{detail},#{specParam},#{price},#{stock},#{subImages})")
    Integer insertProduct(Products products);

    @Update("update action_product set name=#{name},product_id=#{productId},parts_id=#{partsId},detail=#{detail},spec_param=#{specParam},price=#{price},stock=#{stock},sub_images=#{subImages}")
    Integer updateProduct(Products products);

    @Insert("insert into file(filename, stream) values(#{filename}, #{stream})")
    @SelectKey(before = false,keyColumn = "id",keyProperty = "id",
            statement = "select last_insert_id()",resultType = Integer.class)
    int insertFile(File file);

    @Select("select * from file where id = #{id}")
    File selectFile(int id);
}
