package bjfu.eight.mall.mapper;

import bjfu.eight.mall.entity.po.Params;
import bjfu.eight.mall.entity.vo.AllParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ParamsMapper {
    @Select("select * from action_params where parent_id = #{parentId}")
    List<AllParams>SelectByParentID(AllParams params);

    @Select("select * from action_params where parent_id = #{id}")
    List<AllParams>SelectByID(AllParams params);
}
