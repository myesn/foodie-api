package cn.myesn.service;

import cn.myesn.pojo.Carousel;

import java.util.List;

public interface CarouselService {
    List<Carousel> getAll(Integer isShow);
}
