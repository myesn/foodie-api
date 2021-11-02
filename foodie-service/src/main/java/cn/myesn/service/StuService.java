package cn.myesn.service;

import cn.myesn.pojo.Stu;

public interface StuService {
    Stu find(int id);

    void save();

    void update(int id);

    void delete(int id);

    void saveParent();

    void saveChildren();
}
