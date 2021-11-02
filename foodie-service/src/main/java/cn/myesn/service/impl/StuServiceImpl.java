package cn.myesn.service.impl;

import cn.myesn.mapper.StuMapper;
import cn.myesn.pojo.Stu;
import cn.myesn.service.StuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuServiceImpl implements StuService {

    private final StuMapper stuMapper;

    public StuServiceImpl(StuMapper stuMapper) {
        this.stuMapper = stuMapper;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu find(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void save() {
        final Stu stu = new Stu();
        stu.setName("jack");
        stu.setAge(19);
        stuMapper.insert(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(int id) {
        final Stu stu = find(id);
        stu.setName("changed");
        stu.setAge(100);
        stuMapper.updateByPrimaryKey(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(int id) {
        stuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveParent() {
        final Stu stu = new Stu();
        stu.setName("parent");
        stu.setAge(19);
        stuMapper.insert(stu);
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void saveChildren() {
        saveChild1();
        if (true) {
            throw new IllegalArgumentException();
        }
        saveChild2();
    }

    private void saveChild1() {
        final Stu stu = new Stu();
        stu.setName("child-1");
        stu.setAge(19);
        stuMapper.insert(stu);
    }

    private void saveChild2() {
        final Stu stu = new Stu();
        stu.setName("child-2");
        stu.setAge(19);
        stuMapper.insert(stu);
    }
}
