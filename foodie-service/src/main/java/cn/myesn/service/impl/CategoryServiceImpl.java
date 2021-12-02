package cn.myesn.service.impl;

import cn.myesn.mapper.CategoryMapper;
import cn.myesn.mapper.CategoryMapperCustom;
import cn.myesn.pojo.Category;
import cn.myesn.pojo.vo.CategoryVo;
import cn.myesn.pojo.vo.HomeItemsVo;
import cn.myesn.service.CategoryService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryMapperCustom categoryMapperCustom;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryMapperCustom categoryMapperCustom) {
        this.categoryMapper = categoryMapper;
        this.categoryMapperCustom = categoryMapperCustom;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> getRootCategories() {
        final Example example = new Example(Category.class);
        final Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 1);

        return categoryMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVo> getSubCategories(Integer rootCategoryId) {
        return categoryMapperCustom.getSubCategories(rootCategoryId);
    }

    @Override
    public HomeItemsVo getHomeSixItems(Integer rootCategoryId) {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("rootCategoryId", rootCategoryId);
        }};
        return categoryMapperCustom.getHomeSixItems(map);
    }
}
