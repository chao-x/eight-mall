package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Cart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartsMapper {

    @Select("select * from action_carts where user_id=#{uid}")
    public List<Cart> getByUid(int uid);

    @Select("select COUNT(*) from action_carts where user_id=#{uid}")
    public int countcartByUid(int uid);

    @Update("update action_carts set quantity=#{quantity} where product_id=#{productId}")
    public int updateCartAmount(int quantity,int productId);

    @Delete("delete from action_carts where user_id = #{userId}")
    int clearCart(int userId);

    @Delete("delete from action_carts where user_id = #{userId} and product_id = #{productId}")
    void deleteCart(int userId,int productId);

    @Insert("insert into action_carts(id,user_id,product_id,quantity,created,updated) values" +
            "(#{id},#{userId},#{productId},#{quantity},#{createTime},#{updateTime})")
    @SelectKey(before = false,keyColumn = "id",keyProperty = "id",
            statement = "select last_insert_id()",resultType = Integer.class)
    int insertCart(Cart cart);
}
