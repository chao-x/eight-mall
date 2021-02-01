package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Params;
import bjfu.eight.mall.entity.vo.AllParams;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ParamsMapper {
    @Select("select * from action_params where parent_id = #{parentId}")
    List<AllParams>SelectByParentID(AllParams params);

    @Select("select * from action_params where parent_id = #{id}")
    List<AllParams>SelectByID(AllParams params);

    @Select("select * from action_params where parent_id = #{id}")
    List<Params> findChiledren(int id);

    @Delete("delete from action_params where id=#{id}")
    int delParam(int id);

    @Select("select * from action_params")
    List<Params> getall();

    @Update("update action_params set name=#{name} where id=#{id}")
    int updateparam(String name,int id);

    @Insert("insert into action_params(name,parent_id) values(#{name},#{parentid})")
    @SelectKey(before = false,keyColumn = "id",keyProperty = "id",
            statement = "select last_insert_id()",resultType = Integer.class)
    int saveParam(String name,int parentid);
}
