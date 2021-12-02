package cn.myesn.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public final class HomeItemsVo {
    private Integer rootCategoryId;
    private String rootCategoryName;
    private String rootCategorySlogan;
    private String rootCategoryImage;
    private String rootCategoryBackgroundColor;
    private List<Item> items;

    @Data
    public static final class Item {
        private String itemId;
        private String itemName;
        private String itemUrl;
        private Date createdTime;
    }
}
