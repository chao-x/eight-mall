package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AddressMapper {

    @Select("select * from action_address where id=#{addrid}")
    public Address getByAddrid(int addrid);
}
