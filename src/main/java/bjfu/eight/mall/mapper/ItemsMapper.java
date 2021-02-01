package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Item;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemsMapper {

    @Select("select * from action_order_items where order_id=#{orderId}")
    public Item[] getByOrderid(int orderId);

    @Insert("insert into action_order_items(uid,order_id,goods_id,goods_name,icon_url,price,quantity,total_price,created)" +
            "values(#{uid},#{orderId},#{goodsId},#{goodsName},#{iconUrl},#{price},#{quantity},#{totalPrice},#{createTime})")
    @SelectKey(before = false,keyColumn = "id",keyProperty = "id",
            statement = "select last_insert_id()",resultType = Integer.class)
    int insertItem(Item item);
}
