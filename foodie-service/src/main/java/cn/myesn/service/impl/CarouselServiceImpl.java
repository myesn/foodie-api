package cn.myesn.service.impl;

import cn.myesn.mapper.CarouselMapper;
import cn.myesn.pojo.Carousel;
import cn.myesn.service.CarouselService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    private final CarouselMapper carouselMapper;

    public CarouselServiceImpl(CarouselMapper carouselMapper) {
        this.carouselMapper = carouselMapper;
    }

    @Override
    public List<Carousel> getAll(Integer isShow) {
        final Example example = new Example(Carousel.class);
        example.orderBy("sort").desc();
        final Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow", isShow);

        return carouselMapper.selectByExample(example);
    }
}
