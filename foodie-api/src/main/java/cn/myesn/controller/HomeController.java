package cn.myesn.controller;

import cn.myesn.enums.YesOrNoEnum;
import cn.myesn.pojo.Carousel;
import cn.myesn.pojo.Category;
import cn.myesn.service.CarouselService;
import cn.myesn.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation(value = "获取商品分类", notes = "获取商品分类", httpMethod = "GET")
    @GetMapping("category")
    public ResponseEntity<?> category() {
        final List<Category> categories = categoryService.getRootCategories();
        return ResponseEntity.ok(categories);
    }
}
