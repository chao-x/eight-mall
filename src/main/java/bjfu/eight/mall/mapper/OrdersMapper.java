package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {
   // int insertOrders(Orders orders);

    //int deleteById(String id);

   // public Orders getByOrderno(String orederno);

    @Select("select * from action_orders where uid = uid")
    public List<Order> getByUserid(int uid);

    //int updateOrders(Orders orders);
}
