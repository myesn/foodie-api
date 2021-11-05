package cn.myesn.service.impl;

import cn.myesn.mapper.CategoryMapper;
import cn.myesn.pojo.Category;
import cn.myesn.service.CategoryService;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> getRootCategories() {
        final Example example = new Example(Category.class);
        final Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);

        return categoryMapper.selectByExample(example);
    }
}
