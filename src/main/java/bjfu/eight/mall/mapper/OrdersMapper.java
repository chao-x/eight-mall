package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {
   // int insertOrders(Orders orders);

    //int deleteById(String id);

    @Select("select * from action_orders where order_no=#{orderno}")
    public Order getByOrderno(long orederno);

    @Select("select * from action_orders where uid = #{uid}")
    public List<Order> getByUserid(int uid);

    @Update("update action_orders set status=#{status} where order_no=#{orderNo}")
    public int updateStatus(int status,long orderNo);

    //int updateOrders(Orders orders);
}
