package cn.myesn.mapper;

import cn.myesn.my.mapper.MyMapper;
import cn.myesn.pojo.Category;
import cn.myesn.pojo.vo.CategoryVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapperCustom {
    List<CategoryVo> getSubCategories(Integer rootCategoryId);
}