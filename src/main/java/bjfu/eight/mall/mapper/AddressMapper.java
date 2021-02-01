package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Address;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AddressMapper {

    @Select("select * from action_address where id=#{addrid}")
    public Address getByAddrid(int addrid);

    @Select("select * from action_address where user_id=#{userId} and del_state <> 1")
    public Address[] getByUserid(int userId);

    @Update("update action_address set default_addr=#{default_addr} where id=#{id}")
    public int updateDefault(int id,int default_addr);

    @Update("update action_address set del_state=#{del_state} where id=#{id}")
    public int updateDelstate(int id,int del_state);

    @Insert("insert into action_address(user_id,name,phone,mobile,province,city,district,addr,zip,default_addr,del_state)" +
            " values(#{userId},#{name},#{phone},#{mobile},#{province},#{city},#{district},#{addr},#{zip},#{defaultAddr},#{delState})")
    @SelectKey(before = false,keyColumn = "id",keyProperty = "id",
            statement = "select last_insert_id()",resultType = Integer.class)
    public int insertAddress(Address address);
}
