package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemsMapper {

    @Select("select * from action_order_items where order_id=#{orderId}")
    public Item[] getByOrderid(int orderId);
}
