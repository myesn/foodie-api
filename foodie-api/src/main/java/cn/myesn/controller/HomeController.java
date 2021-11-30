package cn.myesn.controller;

import cn.myesn.enums.YesOrNoEnum;
import cn.myesn.exception.ServiceException;
import cn.myesn.pojo.Carousel;
import cn.myesn.pojo.Category;
import cn.myesn.pojo.vo.CategoryVo;
import cn.myesn.pojo.vo.HomeItemsVo;
import cn.myesn.service.CarouselService;
import cn.myesn.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "首页", tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("home")
public class HomeController {
    private final CarouselService carouselService;
    private final CategoryService categoryService;

    public HomeController(CarouselService carouselService, CategoryService categoryService) {
        this.carouselService = carouselService;
        this.categoryService = categoryService;
    }

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("carousel")
    public ResponseEntity<?> carousel() {
        final List<Carousel> carousels = carouselService.getAll(YesOrNoEnum.yes.type);
        return ResponseEntity.ok(carousels);
    }

    @ApiOperation(value = "获取商品分类（一级）", notes = "获取商品分类（一级）", httpMethod = "GET")
    @GetMapping("category")
    public ResponseEntity<?> category() {
        final List<Category> categories = categoryService.getRootCategories();
        return ResponseEntity.ok(categories);
    }

    @ApiOperation(value = "获取商品分类（子分类）", notes = "获取商品分类（子分类）", httpMethod = "GET")
    @GetMapping("category/{rootCategoryId}")
    public ResponseEntity<?> category(
            @ApiParam(name = "rootCategoryId", value = "一级分类id", required = true)
            @PathVariable Integer rootCategoryId) {
        if (rootCategoryId == null) {
            throw new ServiceException("一级分类id不能为空");
        }

        final List<CategoryVo> categories = categoryService.getSubCategories(rootCategoryId);

        return ResponseEntity.ok(categories);
    }

    @ApiOperation(value = "查询每个一级分类下最新的六个商品", notes = "查询每个一级分类下最新的六个商品", httpMethod = "GET")
    @GetMapping("category/six-items/{rootCategoryId}")
    public ResponseEntity<?> categorySixItems(
            @ApiParam(name = "rootCategoryId", value = "一级分类id", required = true)
            @PathVariable Integer rootCategoryId) {
        if (rootCategoryId == null) {
            throw new ServiceException("一级分类id不能为空");
        }

        final List<HomeItemsVo> result = categoryService.getHomeSixItems(rootCategoryId);

        return ResponseEntity.ok(result);
    }
}
