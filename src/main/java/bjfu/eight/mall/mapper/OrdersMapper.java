package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {

    @Insert("insert into action_orders(order_no,uid,addr_id,amount,type,freight,status,created) " +
            "values(#{orderNo},#{uid},#{addrId},#{amount},#{type},#{freight},#{status},#{created})")
    @SelectKey(before = false,keyColumn = "id",keyProperty = "id",
            statement = "select last_insert_id()",resultType = Integer.class)
    int insertOrders(Order orders);

    //int deleteById(String id);

    @Select("select * from action_orders where order_no=#{orderno}")
    public Order getByOrderno(long orederno);

    @Select("select * from action_orders where uid = #{uid}")
    public List<Order> getByUserid(int uid);

    @Update("update action_orders set status=#{status} where order_no=#{orderNo}")
    public int updateStatus(int status,long orderNo);

    @Select("select * from action_orders where uid = #{uid} and status=#{status}")
    public List<Order> getByUseridAnsStatus(int uid,int status);

    @Select("select * from action_orders")
    public List<Order> getAll();
    //int updateOrders(Orders orders);
}
