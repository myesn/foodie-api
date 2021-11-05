package cn.myesn.service;

import cn.myesn.pojo.Category;
import cn.myesn.pojo.vo.CategoryVo;

import java.util.List;

public interface CategoryService {
    List<Category> getRootCategories();

    List<CategoryVo> getSubCategories(Integer rootCategoryId);
}
