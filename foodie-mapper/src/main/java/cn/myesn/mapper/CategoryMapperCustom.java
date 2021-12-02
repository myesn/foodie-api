package cn.myesn.mapper;

import cn.myesn.pojo.vo.CategoryVo;
import cn.myesn.pojo.vo.HomeItemsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoryMapperCustom {
    List<CategoryVo> getSubCategories(Integer rootCategoryId);

    HomeItemsVo getHomeSixItems(@Param("map") Map<String, Object> map);
}