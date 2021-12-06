package cn.myesn.pojo.vo;

import cn.myesn.pojo.Items;
import cn.myesn.pojo.ItemsImg;
import cn.myesn.pojo.ItemsParam;
import cn.myesn.pojo.ItemsSpec;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class ItemInfoVo {
    private Items item;
    private List<ItemsImg> itemImgs;
    private List<ItemsSpec> itemSpecs;
    private ItemsParam itemParam;
}
