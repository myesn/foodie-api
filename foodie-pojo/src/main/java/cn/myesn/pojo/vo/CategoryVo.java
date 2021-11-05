package cn.myesn.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * 二级分类 VO
 */
@Data
public final class CategoryVo {

    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;
    private List<SubCategoryVo> subCategories;

    @Data
    public static final class SubCategoryVo{
        private Integer subId;
        private String subName;
        private String subType;
        private Integer subFatherId;
    }
}
