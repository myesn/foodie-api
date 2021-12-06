package cn.myesn.controller;

import cn.myesn.enums.YesOrNoEnum;
import cn.myesn.exception.ServiceException;
import cn.myesn.pojo.*;
import cn.myesn.pojo.vo.ItemInfoVo;
import cn.myesn.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "商品", tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("items")
public class ItemsController {
    private final ItemService itemService;

    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("info/{itemId}")
    public ResponseEntity<?> carousel(
            @ApiParam(name="itemId", value = "商品ID", required = true)
            @PathVariable String itemId
    ) {
        if(StringUtils.isBlank(itemId)){
            throw new ServiceException("商品ID不能为空");
        }

        Items item = itemService.findById(itemId);
        List<ItemsImg> itemsImgs = itemService.getItemImages(itemId);
        List<ItemsSpec> itemsSpecs = itemService.getItemSpecs(itemId);
        ItemsParam itemsParam = itemService.getItemParam(itemId);
        ItemInfoVo result = new ItemInfoVo()
                .setItem(item)
                .setItemImgs(itemsImgs)
                .setItemSpecs(itemsSpecs)
                .setItemParam(itemsParam);

        return ResponseEntity.ok(result);
    }
}
