package cn.myesn.mapper;

import cn.myesn.my.mapper.MyMapper;
import cn.myesn.pojo.Category;
import cn.myesn.pojo.vo.CategoryVo;
import cn.myesn.pojo.vo.HomeItemsVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoryMapperCustom {
    List<CategoryVo> getSubCategories(Integer rootCategoryId);

    List<HomeItemsVo> getHomeSixItems(Map<String, Object> map);
}