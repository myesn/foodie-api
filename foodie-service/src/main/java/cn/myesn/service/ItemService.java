package cn.myesn.service;

import cn.myesn.pojo.Items;
import cn.myesn.pojo.ItemsImg;
import cn.myesn.pojo.ItemsParam;
import cn.myesn.pojo.ItemsSpec;

import java.util.List;

public interface ItemService {
    Items findById(String itemId);

    List<ItemsImg> getItemImages(String itemId);

    List<ItemsSpec> getItemSpecs(String itemId);

    ItemsParam getItemParam(String itemId);
}
