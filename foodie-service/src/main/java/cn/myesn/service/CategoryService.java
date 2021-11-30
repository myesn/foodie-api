package cn.myesn.service;

import cn.myesn.pojo.Category;
import cn.myesn.pojo.vo.CategoryVo;
import cn.myesn.pojo.vo.HomeItemsVo;

import java.util.List;

public interface CategoryService {
    List<Category> getRootCategories();

    List<CategoryVo> getSubCategories(Integer rootCategoryId);

    List<HomeItemsVo> getHomeSixItems(Integer rootCategoryId);
}
